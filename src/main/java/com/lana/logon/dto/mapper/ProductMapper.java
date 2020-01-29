package com.lana.logon.dto.mapper;

import com.lana.logon.dto.ProductDto;
import com.lana.logon.model.product.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
    ProductDto productToProductDTO(Product product);
}
