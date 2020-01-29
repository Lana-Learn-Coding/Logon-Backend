package com.lana.logon.repository;

import com.lana.logon.model.cart.CartProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartProductRepo extends PagingAndSortingRepository<CartProduct, Integer> {
}
