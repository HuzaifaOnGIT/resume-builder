package com.te.inventory.service;

import com.te.inventory.dto.ProductDto;
import com.te.inventory.entity.Product;

public interface InventoryService {

	public void deleteProduct(Long id);
	public Product addProduct(ProductDto request);
	public Product updateProduct(ProductDto request);
	public Product getById(Long id);
}
