package com.inditex.cconsulting.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.cconsulting.app.Exceptions.CustomException;
import com.inditex.cconsulting.app.model.ProductPriceResponse;
import com.inditex.cconsulting.app.model.entities.ProductPrice;
import com.inditex.cconsulting.app.services.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PricingService pricingService;

    private ProductPrice productPrice;

    @BeforeEach
    void setUp() {
        productPrice = new ProductPrice();
        productPrice.setId(1L);
        productPrice.setBrandId(1L);
        productPrice.setPriceList(1L);
        productPrice.setProductId(35455L);
        productPrice.setCurr("EUR");
    }

    @Test
    @Order(1)
    void test_one_request_at_10_am_return_highest_priority_value() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020,
                Month.JUNE, 14, 10, 0, 0);

        LocalDateTime end = LocalDateTime.of(2020,
                Month.DECEMBER, 31, 23, 59, 59);
        BigDecimal priceBD = new BigDecimal("35.50");

        productPrice.setStartDate(start);
        productPrice.setEndDate(end);
        productPrice.setPrice(priceBD);
        productPrice.setPriority(0);

        String requestJson = objectMapper.writeValueAsString(productPrice);

        mockMvc.perform(get("/api/v1/product-price")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("request", requestJson)
                .content(objectMapper.writeValueAsString(productPrice)))
                .andExpect(status().isOk());

        ProductPriceResponse price = pricingService.findProductPrice(productPrice);
        assertThat(price.getPrice().toString()).isEqualTo("25.45");
    }

    @Test
    @Order(2)
    void test_two_request_at_16_pm_return_highest_priority_value() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020, Month.JUNE, 14, 16, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
        BigDecimal priceBD = new BigDecimal("38.95");

        productPrice.setStartDate(start);
        productPrice.setEndDate(end);
        productPrice.setPrice(priceBD);
        productPrice.setPriority(1);

        String requestJson = objectMapper.writeValueAsString(productPrice);

        mockMvc.perform(get("/api/v1/product-price")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("request", requestJson)
                .content(objectMapper.writeValueAsString(productPrice)))
                .andExpect(status().isOk());

        ProductPriceResponse price = pricingService.findProductPrice(productPrice);
        assertThat(price.getPrice().toString()).isEqualTo("30.50");
    }

    @Test
    @Order(3)
    void test_three_request_at_21_pm_return_highest_priority_value() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020, Month.JUNE, 14, 21, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
        BigDecimal priceBD = new BigDecimal("38.95");

        productPrice.setStartDate(start);
        productPrice.setEndDate(end);
        productPrice.setPrice(priceBD);
        productPrice.setPriority(1);

        String requestJson = objectMapper.writeValueAsString(productPrice);

        mockMvc.perform(get("/api/v1/product-price")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("request", requestJson)
                .content(objectMapper.writeValueAsString(productPrice)))
                .andExpect(status().isOk());

        ProductPriceResponse price = pricingService.findProductPrice(productPrice);
        assertThat(price.getPrice().toString()).isEqualTo("30.50");
    }

    @Test
    @Order(4)
    void test_four_request_at_10_am_day_15_return_highest_priority_value() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020, Month.JUNE, 15, 10, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, Month.JUNE, 15, 11, 0, 0);
        BigDecimal priceBD = new BigDecimal("30.50");

        productPrice.setStartDate(start);
        productPrice.setEndDate(end);
        productPrice.setPrice(priceBD);
        productPrice.setPriority(1);

        String requestJson = objectMapper.writeValueAsString(productPrice);

        mockMvc.perform(get("/api/v1/product-price")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("request", requestJson)
                .content(objectMapper.writeValueAsString(productPrice)))
                .andExpect(status().isOk());

        ProductPriceResponse price = pricingService.findProductPrice(productPrice);
        assertThat(price.getPrice().toString()).isEqualTo("38.95");
    }

    @Test
    @Order(5)
    void test_five_request_at_21_pm_day_16_return_highest_priority_value() throws Exception {
        LocalDateTime start = LocalDateTime.of(2020, Month.JUNE, 16, 21, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, Month.JUNE, 15, 11, 0, 0);
        BigDecimal priceBD = new BigDecimal("30.50");

        productPrice.setStartDate(start);
        productPrice.setEndDate(end);
        productPrice.setPrice(priceBD);
        productPrice.setPriority(1);

        String requestJson = objectMapper.writeValueAsString(productPrice);

        mockMvc.perform(get("/api/v1/product-price")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("request", requestJson)
                .content(objectMapper.writeValueAsString(productPrice)))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomException))
                .andExpect(result -> assertEquals("Entity not found", result.getResolvedException().getMessage()));
    }
}
