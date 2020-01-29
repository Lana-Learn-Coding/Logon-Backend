package com.lana.logon.dto.mapper;

import com.lana.logon.model.product.ProductImage;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    default Collection<String> productImagesToStrings(Collection<ProductImage> productImages) {
        return productImages.stream()
                .map(ProductImage::getImage)
                .collect(Collectors.toList());
    }
}
