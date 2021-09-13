package com.colin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.colin.controller.ProductController;
import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.models.ProductQuantity;
import com.colin.repo.CategoryRepository;
import com.colin.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	ObjectMapper map;
	
	@MockBean
	ProductService productService;
	
	@MockBean
	CategoryRepository categoryRepository;
	
	@InjectMocks
	ProductController controllerUnderTest; 
	
	private MockMvc mvc;
	
	@BeforeEach
	public void contextLoads() {
		map = new ObjectMapper();
		
		MockitoAnnotations.openMocks(this);
		
		this.mvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
		
		Category c2 = new Category(3, "Fruit", new ArrayList<>());
		Product p2 = new Product(3, "Apple", 3, 1.99, c2, new ArrayList<>());
		categoryRepository.save(c2);
		productService.createProduct(p2);
	}
	
	@Test
	public void testGetAllProducts() throws Exception {
		ArrayList<ProductCategory> products = new ArrayList<>();
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		ProductCategory pc = new ProductCategory(p, c);
		products.add(pc);
		
		when(productService.getAllProducts()).thenReturn(products);
		
		mvc.perform(get("/api/products")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$[0].product.name").value("Milk"))
				.andExpect(jsonPath("$[0].product.quantity").value(3))
				.andExpect(jsonPath("$[0].product.price").value(2.99))
				.andExpect(jsonPath("$[0].category.name").value("Dairy"));
	}
	
	@Test
	void getProductById() throws Exception {
		Category category = new Category(2, "Vegetable", new ArrayList<>());
		Product product = new Product(2, "Tomato", 3, 1.99, category, new ArrayList<>());
		categoryRepository.save(category);
		productService.createProduct(product);
		Optional <Product> productReturn = Optional.ofNullable(product);
		
		when(productService.getById(2)).thenReturn(productReturn);
		
		mvc.perform(get("/api/products/{id}", 2)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("product.name").value("Tomato"))
				.andExpect(jsonPath("product.quantity").value(3))
				.andExpect(jsonPath("product.price").value(1.99))
				.andExpect(jsonPath("category.name").value("Vegetable"));
	}
	
	/*@Test
	void addNewProduct() throws Exception {
		Category category = new Category(2, "Vegetable", new ArrayList<>());
		Product product = new Product(2, "Tomato", 3, 1.99, category, new ArrayList<>());
		
		mvc.perform(post("/api/products/new")
		.content(toJsonString(new ProductCategory(product, category)))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}*/
	
	/*
	 * @Test public void updateProduct() throws Exception { Category category = new
	 * Category(2, "Vegetable", new ArrayList<>()); Product product = new Product(2,
	 * "Tomato", 3, 1.99, category, new ArrayList<>());
	 * categoryRepository.save(category); productService.createProduct(product);
	 * Optional <Product> productReturn = Optional.ofNullable(product);
	 * 
	 * Category category2 = new Category(2, "Vegetable", new ArrayList<>()); Product
	 * product2 = new Product(2, "Onion", 3, 1.99, category2, new ArrayList<>());
	 * 
	 * when(productService.getById(2)).thenReturn(productReturn);
	 * 
	 * mvc.perform(put("/api/products/edit/{id}", 2) .content(toJsonString(new
	 * ProductCategory(product2, category2)))
	 * .contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
	 * .andExpect(status().isOk()); }
	 */
	
	@Test
	public void deleteProduct() throws Exception {
		mvc.perform(delete("/api/products/delete/{id}", 3))
				.andExpect(status().isAccepted());
	}
	
	@Test
	public void deleteQuantity() throws Exception {
		Category category = new Category(2, "Vegetable", new ArrayList<>());
		Product product = new Product(2, "Tomato", 3, 1.99, category, new ArrayList<>());
		categoryRepository.save(category);
		productService.createProduct(product);
		Optional <Product> productReturn = Optional.ofNullable(product);
		Long q = 2l;
		
		when(productService.getById(2)).thenReturn(productReturn);
		
		mvc.perform(delete("/api/products/quantity")
		.content(toJsonString(new ProductQuantity(2, q)))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isAccepted());
	}
	
	public static String toJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
