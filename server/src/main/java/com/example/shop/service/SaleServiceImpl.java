package com.example.shop.service;

import com.example.shop.dao.entity.*;
import com.example.shop.dao.repository.OrderRepository;
import com.example.shop.dao.repository.ProductDiscountRepository;
import com.example.shop.dto.ClientWithProduct;
import com.example.shop.dto.ProductWithCount;
import com.example.shop.dto.ProductWithDiscount;
import com.example.shop.dto.Statistics;
import com.example.shop.exception.IncorrectFinalPriceException;
import com.example.shop.schedule.CheckGenerator;
import com.example.shop.schedule.DiscountingProductEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    public static final int MAX_PERCENT = 100;
    private final static BigDecimal DECIMAL_HUNDRED = BigDecimal.valueOf(100);
    public static final int MAX_PERCENT_DISCOUNT = 18;
    public static final int MIN_PRODUCTS_FOR_DISCOUNT_2 = 5;

    private final ClientService clientService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ProductDiscountRepository productDiscountRepository;
    private final CheckGenerator checkGenerator;
    private final Mapper mapper;

    private ProductWithDiscount currentDiscounting;

    //@PostConstruct
    public void init() {
        ProductWithDiscount productDiscount = productDiscountRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream().findFirst()
                .map(elem -> mapper.map(elem, ProductWithDiscount.class))
                .orElse(null);
        currentDiscounting = productDiscount;
    }

    @EventListener
    public void onNewDiscountingProductEvent(DiscountingProductEvent event) {
        currentDiscounting = event.getProductDiscount();
    }

    @Override
    public Long calculateFinalPrice(long clientId, List<ProductWithCount> sales) {
        Client client = clientService.findClientById(clientId);
        return calculateProductsCostInKopecks(client, sales);
    }

    @Override
    public String order(long clientId, List<ProductWithCount> products, long finalPriceKopecks) {
        if (!Objects.equals(calculateFinalPrice(clientId, products), finalPriceKopecks)) {
            throw new IncorrectFinalPriceException(finalPriceKopecks);
        }

        List<OrderPosition> positions = products.stream()
                .map(elem -> mapper.map(elem, OrderPosition.class))
                .toList();

        String checkNumber = checkGenerator.nextCheck();
        Order order = new Order(clientService.findClientById(clientId), LocalDateTime.now(), checkNumber);
        order.setPositions(positions);
        orderRepository.save(order);
        return checkNumber;
    }

    @Override
    public Statistics getStatistics(ClientWithProduct request) {
        if (request.getClientId()!=null && request.getProductId()!=null) {
            return orderRepository.getStatistics(request.getClientId(), request.getProductId());
        }else if(request.getClientId()!=null){
            return orderRepository.getClientStatistics(request.getClientId());
        }
        return orderRepository.getProductStatistics(request.getProductId());
    }

    private Long calculateProductsCostInKopecks(Client client, List<ProductWithCount> sales) {
        List<BigDecimal> calculation = new ArrayList<>();
        for (ProductWithCount productWithCount : sales) {
            int clientDiscount = (productWithCount.getCount() >= MIN_PRODUCTS_FOR_DISCOUNT_2 && client.getDiscount2() > 0)
                    ? client.getDiscount2()
                    : client.getDiscount1();
            calculation.add(calculateProductCost(clientDiscount, productWithCount));
        }
        return calculation.stream()
                .mapToLong(price->toKopecks(price))
                .sum();
    }

    private BigDecimal calculateProductCost(int clientDiscount, ProductWithCount productWithCount) {
        long productId = productWithCount.getProductId();
        Product product = productService.findProductById(productId);
        if (Objects.equals(product.getId(), currentDiscounting.getProductId())) {
            clientDiscount += currentDiscounting.getPercentDiscount();
        }
        clientDiscount = Math.min(clientDiscount, MAX_PERCENT_DISCOUNT);

        BigDecimal sum = product.getPrice().multiply(BigDecimal.valueOf(productWithCount.getCount()));
        BigDecimal sumWithDiscount = calculatePercent(sum, MAX_PERCENT - clientDiscount);
        return sumWithDiscount;
    }

    private BigDecimal calculatePercent(BigDecimal decimal, int percent) {
        return decimal.divide(DECIMAL_HUNDRED, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(percent));
    }

    private long toKopecks(BigDecimal decimal) {
        return decimal.multiply(DECIMAL_HUNDRED).longValue();
    }

}
