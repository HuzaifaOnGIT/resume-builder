package com.te.inventory.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.inventory.dto.ProductDto;
import com.te.inventory.dto.ResponseMessage;
import com.te.inventory.entity.Product;
import com.te.inventory.service.InventoryService;
@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private static final String PRODUCT_NOT_FOUND = "Product not found";
	private static final String PRODUCT_FETCHED_SUCCESSFULLY = "product Fetched Successfully";
	private static final String PRODUCT_NOT_UPDATED = "Product not Updated";
	private static final String PRODUCT_UPDATED_SUCCESSFULLY = "Product Updated Successfully";
	private static final String PRODUCT_NOT_ADDED = "Product not added";
	private static final String PRODUCT_ADDED_SUCCESSFULLY = "Product Added Successfully";
	
	@Autowired
	private InventoryService service;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> addProduct(@RequestBody ProductDto request) {
		Product addProduct = service.addProduct(request);
		if (addProduct != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, PRODUCT_ADDED_SUCCESSFULLY, addProduct);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, PRODUCT_NOT_ADDED, addProduct);
			return new ResponseEntity<>(responseMessage, HttpStatus.METHOD_FAILURE);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseMessage> updateProduct(@RequestBody ProductDto request) {
		Product updateProduct = service.updateProduct(request);
		if (updateProduct != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, PRODUCT_UPDATED_SUCCESSFULLY, updateProduct);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, PRODUCT_NOT_UPDATED, updateProduct);
			return new ResponseEntity<>(responseMessage, HttpStatus.METHOD_FAILURE);
		}
	}

	@GetMapping("/get-product/{id}")
	public ResponseEntity<ResponseMessage> getProduct(@PathVariable Long id) {

		Product product = service.getById(id);
		if (product != null) {
			ResponseMessage responseMessage = new ResponseMessage(false, PRODUCT_FETCHED_SUCCESSFULLY, product);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(true, PRODUCT_NOT_FOUND, product);
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long id) {

		ResponseMessage responseMessage;
		try {
			service.deleteProduct(id);
		} catch (Exception e) {
			responseMessage = new ResponseMessage(false, "Product not  deleted ", null);
			e.printStackTrace();
			return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMessage = new ResponseMessage(false, "Product deleted Successfully", null);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);

	}

}
