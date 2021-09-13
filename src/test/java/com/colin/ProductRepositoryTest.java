package com.colin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.repo.CategoryRepository;
import com.colin.repo.ProductRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	/*
	 * @Test public void testGetProducts() { List<Product> products =
	 * (List<Product>) productRepository.findAll();
	 * 
	 * assertTrue(products.get(0).getName().equals("apple")); }
	 * 
	 * @Test public void testFindById() { Product product =
	 * productRepository.findById(1l).orElse(null);
	 * 
	 * assertNotNull(product); }
	 */
	
	/*@Test
	public void testSave() {
		Category category = new Category(2, "Vegetable", new ArrayList<>());
		Product product = new Product(2, "Tomato", 3, 1.99, category, new ArrayList<>());
		categoryRepository.save(category);
		Product p2 = productRepository.save(product);
		assertNotNull(p2);
		assertTrue(product.getName().equals(p2.getName()));
	}*/
	
	@Test
	public void testDelete() {
		Category category = new Category(2, "Vegetable", new ArrayList<>());
		Product product = new Product(2, "Onion", 3, 1.99, category, new ArrayList<>());
		categoryRepository.save(category);
		productRepository.delete(product);
	}
}
