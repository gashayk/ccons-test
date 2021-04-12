package com.inditex.cconsulting.app;

import com.inditex.cconsulting.app.controllers.ProductPriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppApplicationTests {

    @Autowired
    private ProductPriceController productPriceController;

    @Test
    void contextLoads() {
        assertThat(productPriceController).isNotNull();
    }

}
