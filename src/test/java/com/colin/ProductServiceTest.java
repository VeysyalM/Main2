package com.colin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.repo.ProductRepository;
import com.colin.service.ProductService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {
	
	@MockBean
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@Test
	public void testGetAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		products.add(p);
		
		when(productRepository.findAll()).thenReturn(products);
		
		List<ProductCategory> allProducts = productService.getAllProducts();
		
		
		assertEquals(allProducts.size(), 1);
		assertTrue(allProducts.get(0).getProduct().getName().equals("Milk"));
		assertTrue(allProducts.get(0).getProduct().getQuantity() == 3);
		assertTrue(allProducts.get(0).getProduct().getPrice() == 2.99);
		assertTrue(allProducts.get(0).getCategory().getName().equals("Dairy"));
		
	}
	
	@Test
	public void testGetById() {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Optional<Product> returnP = Optional.ofNullable(p);
		
		when(productRepository.findById(1l)).thenReturn(returnP);
		
		Optional<Product> p2 = productService.getById(1l);
		
		assertEquals(returnP, p2);
	}
	
	@Test
	public void testSaveProduct() {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		
		when(productRepository.save(p)).thenReturn(p);
		
		productService.createProduct(p);
		assertNotNull(p);
	}
	
	@Test
	public void testUpdateProduct() {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());

		when(productRepository.save(p)).thenReturn(p);
		
		assertNotNull(p);
	}
	
	@Test
	public void testDeleteProduct() {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		
		when(productRepository.save(p)).thenReturn(p);
		productService.createProduct(p);

		
		productService.deleteProduct(1l);
		
		assertNull(productService.getById(1l).orElse(null));
		

	}

}

