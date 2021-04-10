package com.inditex.cconsulting.app.services.impl;

import com.inditex.cconsulting.app.AppUtils;
import com.inditex.cconsulting.app.Exceptions.CustomException;
import com.inditex.cconsulting.app.model.ProductPriceResponse;
import com.inditex.cconsulting.app.model.entities.ProductPrice;
import com.inditex.cconsulting.app.repository.PricingRepository;
import com.inditex.cconsulting.app.services.PricingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PricingServiceImpl implements PricingService {

    private final Logger logger = LoggerFactory.getLogger(PricingServiceImpl.class);

    @Autowired
    private PricingRepository pricingRepository;

    /**
     *
     * @param productPrice object to search in database
     * @return filtered ProductPriceResponse object
     * @throws CustomException if not found any object by given criteria
     */
    @Override
    public ProductPriceResponse findProductPrice(ProductPrice productPrice) throws CustomException {
        final List<ProductPrice> foundProductPrice = pricingRepository.
                findAllByStartDateGreaterThanEqualAndBrandIdAndProductId(productPrice.getStartDate(), productPrice.getBrandId(), productPrice.getProductId());

        if (foundProductPrice.isEmpty()) {
            logger.error("Entity with following params not found date: {}, brand id {}, product id {}",
                    productPrice.getStartDate(), productPrice.getBrandId(), productPrice.getProductId());
            throw new CustomException("Entity not found", HttpStatus.NOT_FOUND);
        }

        List<ProductPriceResponse> response = foundProductPrice.stream()
                .map(AppUtils::convertEntityToDto)
                .collect(Collectors.toList());

        return filterPrice(response);
    }

    /**
     * Filters price results based on PRIORITY property in found results
     * @param priceList ProductPriceResponse object list
     * @return ProductPriceResponse
     */
    private ProductPriceResponse filterPrice(List<ProductPriceResponse> priceList) {
        Long tempVal = 0L;
        ProductPriceResponse result = new ProductPriceResponse();
        for (ProductPriceResponse ppr : priceList) {
            if (ppr.getPriceList() > tempVal) {
                tempVal = ppr.getPriceList();
                result = ppr;
            }
        }
        return result;
    }
}
