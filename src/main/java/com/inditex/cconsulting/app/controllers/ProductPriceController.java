package com.inditex.cconsulting.app.controllers;

import com.inditex.cconsulting.app.AppUtils;
import com.inditex.cconsulting.app.Exceptions.CustomException;
import com.inditex.cconsulting.app.model.ProductPriceResponse;
import com.inditex.cconsulting.app.model.ProductPriceRequest;


import com.inditex.cconsulting.app.services.PricingService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "Product price endpoint operations")
@RequestMapping("api/v1/product-price")
public class ProductPriceController {

    private final Logger logger = LoggerFactory.getLogger(ProductPriceController.class);

    @Autowired
    private PricingService pricingService;

    @GetMapping
    @ApiOperation(value = "Find product price")
    public ResponseEntity<?> findProductPrice(ProductPriceRequest request) throws CustomException {
        logger.info("findProductPrice() -> Fetching product");

        if (request == null) {
            logger.error("findProductPrice() -> Request param is null");
            throw new CustomException("Request parameter can't be null", HttpStatus.BAD_REQUEST);
        }

        ProductPriceResponse response = pricingService.findProductPrice(AppUtils.convertDTOToEntity(request));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
