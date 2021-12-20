package com.belajar.movies.belajarspring.datasource.repository;

import com.belajar.movies.belajarspring.datasource.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
}
