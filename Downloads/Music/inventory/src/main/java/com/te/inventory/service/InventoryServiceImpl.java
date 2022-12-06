package com.te.inventory.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.inventory.dto.ProductDto;
import com.te.inventory.entity.Product;
import com.te.inventory.exception.InventoryException;
import com.te.inventory.repository.InventoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository repository;

	@Override
	public Product addProduct(ProductDto request) {
		String methodName = "addProduct";
		Product product = null;
		try {
			if (request != null) {
				product = new Product();
				BeanUtils.copyProperties(request, product);

				product = repository.save(product);
				if (product == null) {
					log.info(methodName + " null returned while saving");
					throw new InventoryException(methodName + " Failed to save Data");
				}
			}

		} catch (Exception e) {
			log.info("exception occured due to" + e);
			throw e;

		}
		return product;
	}

	@Override
	public Product updateProduct(ProductDto request) {
		String methodName = "updateProduct";
		Product product = null;
		try {
			Optional<Product> findById = repository.findById(request.getId());
			if(findById.isEmpty()) {
				throw new InventoryException("Product Not Found");
			}
			findById.get().setProductName(request.getProductName());
			findById.get().setProductDescription(request.getProductDescription());
			findById.get().setProductPrice(request.getProductPrice());
			product = repository.save(findById.get());

			if (product == null) {
				log.info(methodName + product);
				throw new InventoryException(methodName + "Failed to Update Product");
			}
		} catch (Exception e) {
			log.info("failed to update data");
			throw e;

		}
		return product;
	}

	@Override
	public Product getById(Long id) {

		String methodName = "getById";
		Product product = null;
		try {
			Optional<Product> productOpt = repository.findById(id);

			if (productOpt.isPresent()) {

				product = productOpt.get();

			} else {
				log.info(methodName + "Null Value Received in Optional");
				throw new InventoryException(methodName + " No Product Found");
			}
		} catch (Exception e) {
			log.info("exception occured due to" + e);
			throw e;
		}
		return product;
	}

	@Override
	public void deleteProduct(Long id) {
		String methodName="deleteProduct";
		Product product=null;
		try {
			
				Optional<Product> productOpt = repository.findById(id);

				if (productOpt.isPresent()) {

					product = productOpt.get();
					
					repository.delete(product);

				} else {
					log.info(methodName + "Null Value Received in Optional");
					throw new InventoryException(methodName + " No Product Found");
				}

		} catch (Exception e) {

			log.info("failed to delete the data");
			throw e;
		}

	}

}
