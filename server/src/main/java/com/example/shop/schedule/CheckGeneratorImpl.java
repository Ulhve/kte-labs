package com.example.shop.schedule;

import com.example.shop.dao.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CheckGeneratorImpl implements CheckGenerator {

    private static final int START_CHECK_VALUE = 100;

    private final OrderRepository checkRepository;
    private int currentCheck = START_CHECK_VALUE;

    @PostConstruct
    public void init() {
        checkRepository.findLastCheckToday(LocalDateTime.now().withHour(0))
                .ifPresent(s -> currentCheck = Integer.parseInt(s));
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshStartValue() {
        currentCheck = START_CHECK_VALUE;
    }

    @Override
    public String nextCheck() {
        currentCheck++;
        if (currentCheck < 1000) {
            return "00" + currentCheck;
        } else if (currentCheck < 10000) {
            return "0" + currentCheck;
        } else {
            return Integer.toString(currentCheck);
        }
    }
}
