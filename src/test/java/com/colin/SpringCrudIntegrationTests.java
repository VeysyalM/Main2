package com.colin;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.repo.CategoryRepository;
import com.colin.repo.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class SpringCrudIntegrationTests {

	@Autowired
//	private WebApplicationContext webApplicationContext;
	MockMvc mvc;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	/*@BeforeEach
	public void getContext() {
		//mvc = webAppContextSetup(webApplicationContext).build();
		
		Category category = new Category("Dairy", new ArrayList<>());
		Product product = new Product("Milk", 3, 2.99, category, new ArrayList<>());
		category.addProduct(product);
		categoryRepository.save(category);
		productRepository.save(product);
	}
	@Test
	void givenProduct_whenGetAllProducts_thenStatus200() throws Exception {
		//Category category = new Category(1, "Dairy", new ArrayList<>());
		//Product product = new Product(1, "Milk", 3, 2.99, category, new ArrayList<>());
		//category.addProduct(product);
		//categoryRepository.save(category);
		//productRepository.save(product);
		mvc.perform(MockMvcRequestBuilders.get("/api/products"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void addNewProduct() throws Exception {
		Category category = new Category(2, "Vegetable", new ArrayList<>());
		Product product = new Product(2, "Tomato", 3, 1.99, category, new ArrayList<>());
		
		mvc.perform(MockMvcRequestBuilders.post("/api/products/new")
		.content(toJsonString(new ProductCategory(product, category)))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateProduct() {
		
	}
	
	public static String toJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}*/

}
