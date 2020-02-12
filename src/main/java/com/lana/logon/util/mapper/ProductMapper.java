package com.lana.logon.util.mapper;

import com.lana.logon.dto.product.ProductDetailDto;
import com.lana.logon.dto.product.ProductDto;
import com.lana.logon.model.product.Product;
import com.lana.logon.model.product.ProductImage;
import com.lana.logon.repository.product.ProductImageRepo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ImageMapper.class})
public abstract class ProductMapper {

    private ProductImageRepo productImageRepo;

    @Autowired
    public void initialize(ProductImageRepo productImageRepo) {
        this.productImageRepo = productImageRepo;
    }

    @Mapping(target = "image", ignore = true)
    public abstract ProductDto productToProductDto(Product product);

    @AfterMapping
    public void setProductMainImage(Product product, @MappingTarget ProductDto productDto) {
        // Set image here instead of use Mapper
        // The Mapper will call product.getImages() and
        // then pass it to the map function.
        //
        // Here we only need query the first image.
        // instead of query all images by calling product.getImages()
        productDto.setImage(
                this.productImageRepo
                        .findFirstByProductId(product.getId())
                        .map(ProductImage::getImageUrl)
                        .orElse("")
        );
    }

    public abstract ProductDetailDto productToProductDetailDto(Product product);

    public Integer productToInteger(Product product) {
        return product.getId();
    }
}
