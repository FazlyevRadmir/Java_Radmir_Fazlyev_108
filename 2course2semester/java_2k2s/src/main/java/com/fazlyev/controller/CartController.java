//HomeWork 3

package com.fazlyev.controller;

import com.fazlyev.model.Cart;
import com.fazlyev.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable(value = "id") Long cartId) {
        Optional<Cart> cart = cartService.getCartById(cartId);
        if (!cart.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cart.get());
    }

    @PostMapping("/carts")
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }
}

