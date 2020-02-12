package com.lana.logon.util.mapper;

import com.lana.logon.dto.product.rate.ProductRateDto;
import com.lana.logon.model.product.rate.ProductRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface ProductRateMapper {
    ProductRateDto productRateToProductRateDto(ProductRate productRate);
}
