package com.inditex.cconsulting.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPriceRequest implements Serializable {

    private static final long serialVersionUID = -8100195817322499511L;

    @ApiModelProperty(value = "Apply date", name = "startDate",
            dataType = "LocalDateTime", example = "2020-06-15 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "Product id", name = "productId",
            dataType = "Long", example = "35455")
    private Long productId;

    @ApiModelProperty(value = "Brand id", name = "brandId",
            dataType = "Long", example = "1")
    private Long brandId;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
