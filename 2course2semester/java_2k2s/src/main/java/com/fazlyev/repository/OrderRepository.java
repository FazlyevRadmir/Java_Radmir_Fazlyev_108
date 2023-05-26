//HomeWork 3

package com.fazlyev.repository;

import com.fazlyev.model.Customer;
import com.fazlyev.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}