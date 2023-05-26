//HomeWork 3

package com.fazlyev.repository;

import com.fazlyev.model.Cart;
import com.fazlyev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
