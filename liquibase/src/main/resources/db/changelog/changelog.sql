--liquibase formatted sql

--changeset liquibase:1
-- comment: create tables
CREATE TABLE public.clients (
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    "name"      VARCHAR(255),
    discount_1  INTEGER,
    discount_2  INTEGER
);

CREATE TABLE public.orders (
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    client_id    BIGINT NOT NULL REFERENCES public.clients,
    "date"       TIMESTAMP(6),
    check_number VARCHAR(6)
);

CREATE TABLE public.order_positions (
    id                     BIGSERIAL PRIMARY KEY NOT NULL,
    order_id               BIGINT NOT NULL REFERENCES public.orders,
    product_id             BIGINT NOT NULL,
    "count"                INTEGER NOT NULL,
    final_price            NUMERIC NOT NULL,
    final_discount_percent INTEGER NOT NULL
);

CREATE TABLE public.products (
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    "title"     VARCHAR(255) NOT NULL,
    price       NUMERIC NOT NULL,
    description TEXT
);

CREATE TABLE public.product_discounts (
    id               BIGSERIAL PRIMARY KEY NOT NULL,
    product_id       BIGINT NOT NULL,
    percent_discount INTEGER NOT NULL
);

CREATE TABLE public.product_ratings (
     id          BIGSERIAL PRIMARY KEY NOT NULL,
     rating      INTEGER NOT NULL,
     product_id BIGINT NOT NULL REFERENCES public.products,
     client_id   BIGINT NOT NULL REFERENCES public.clients
);


--changeset liquibase:2
-- comment: init clients table
INSERT INTO public.clients ("name", discount_1, discount_2) VALUES ('client 1', null, null);
INSERT INTO public.clients ("name", discount_1, discount_2) VALUES ('client 2', 7, null);
INSERT INTO public.clients ("name", discount_1, discount_2) VALUES ('client 3', null, 3);
INSERT INTO public.clients ("name", discount_1, discount_2) VALUES ('client 4', 12, 15);
INSERT INTO public.clients ("name", discount_1, discount_2) VALUES ('client 5', 20, 25);


--changeset liquibase:3
-- comment: init products table
INSERT INTO public.products ("title", price, description) VALUES ('product 1', 1000, 'description 1');
INSERT INTO public.products ("title", price, description) VALUES ('product 2', 2000, 'description 2');
INSERT INTO public.products ("title", price, description) VALUES ('product 3', 3000, 'description 3');
INSERT INTO public.products ("title", price, description) VALUES ('product 4', 4000, 'description 4');
INSERT INTO public.products ("title", price, description) VALUES ('product 5', 5000, 'description 5');