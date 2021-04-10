package com.inditex.cconsulting.app.services;

import com.inditex.cconsulting.app.Exceptions.CustomException;
import com.inditex.cconsulting.app.model.ProductPriceResponse;
import com.inditex.cconsulting.app.model.entities.ProductPrice;

public interface PricingService {

    ProductPriceResponse findProductPrice(ProductPrice convertDTOToEntity) throws CustomException;
}
