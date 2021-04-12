package com.inditex.cconsulting.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "Product price endpoint operations")
@RequestMapping("api/v1/product-price")
public class ProductPriceController {

    private final Logger logger = LoggerFactory.getLogger(ProductPriceController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PricingService pricingService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find product price")
    public ResponseEntity<?> findProductPrice(@RequestParam String request) throws CustomException {
        logger.info("findProductPrice() -> Fetching product");
        ProductPriceRequest requestObject = null;
        if (request == null) {
            logger.error("findProductPrice() -> Request param is null");
            throw new CustomException("Request parameter can't be null", HttpStatus.BAD_REQUEST);
        }
        try {
            requestObject = objectMapper.readValue(request, ProductPriceRequest.class);
        } catch (JsonProcessingException e) {
            logger.error("findProductPrice() -> JSON conversion error.");
            throw new CustomException("Unable to convert from json to object", HttpStatus.BAD_REQUEST);
        }
        ProductPriceResponse response = pricingService.findProductPrice(AppUtils.convertDTOToEntity(requestObject));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
