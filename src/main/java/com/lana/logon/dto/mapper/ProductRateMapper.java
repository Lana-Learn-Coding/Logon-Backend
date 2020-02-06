package com.lana.logon.dto.mapper;

import com.lana.logon.dto.ProductRateDto;
import com.lana.logon.model.product.rate.ProductRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface ProductRateMapper {
    ProductRateDto productRateToProductRateDto(ProductRate productRate);
}
