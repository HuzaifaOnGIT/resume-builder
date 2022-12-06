package com.te.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.inventory.entity.Product;
@Repository
public interface InventoryRepository  extends JpaRepository<Product, Long>{

}
