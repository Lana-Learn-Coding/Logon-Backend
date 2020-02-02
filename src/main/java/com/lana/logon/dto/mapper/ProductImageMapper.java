package com.lana.logon.dto.mapper;

import com.lana.logon.model.product.ProductImage;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    default Set<String> mapToStrings(Set<ProductImage> productImages) {
        return productImages.stream()
                .map(ProductImage::getImage_url)
                .collect(Collectors.toSet());
    }
}
