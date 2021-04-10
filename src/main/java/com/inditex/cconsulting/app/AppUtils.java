package com.inditex.cconsulting.app;

import com.inditex.cconsulting.app.model.ProductPriceRequest;
import com.inditex.cconsulting.app.model.ProductPriceResponse;
import com.inditex.cconsulting.app.model.entities.ProductPrice;
import org.modelmapper.ModelMapper;

import java.time.Instant;

public class AppUtils {

    private AppUtils(){}

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductPrice convertDTOToEntity(ProductPriceRequest productPriceRequest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(productPriceRequest, ProductPrice.class);
    }

    public static ProductPriceResponse convertEntityToDto(ProductPrice productPrice) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(productPrice, ProductPriceResponse.class);
    }

    public static long timestamp () {
        Instant instant = Instant.now();
        return instant.getEpochSecond();
    }
}
