package com.lana.logon.repository;

import com.lana.logon.model.cart.CartProduct;
import com.lana.logon.model.cart.CartProductKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CartProductRepo extends PagingAndSortingRepository<CartProduct, CartProductKey> {
    List<CartProduct> findAllByUserId(Integer id);
}
