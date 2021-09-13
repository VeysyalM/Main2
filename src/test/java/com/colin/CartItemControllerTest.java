package com.colin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

import com.colin.controller.CartItemController;
import com.colin.controller.ProductController;
import com.colin.models.CartItem;
import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.models.ProductQuantity;
import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.CategoryRepository;
import com.colin.repo.UserRepository;
import com.colin.service.CartItemService;
import com.colin.service.ProductService;
import com.colin.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class CartItemControllerTest {

	ObjectMapper map;
	
	@MockBean
	CartItemService cartItemService;
	
	@MockBean
	CategoryRepository categoryRepository;
	
	@MockBean
	UserDetailsServiceImpl userDetails;
	
	@MockBean
	ProductService productService;
	
	@InjectMocks
	CartItemController controllerUnderTest; 
	
	private MockMvc mvc;
	
	@BeforeEach
	public void contextLoads() {
		map = new ObjectMapper();
		
		MockitoAnnotations.openMocks(this);
		
		this.mvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
		
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());
		CartItem cI = new CartItem(3, 2, LocalDateTime.now(), u, p);
		
		cartItemService.createCartItem(cI);
		
	}
	
	/*@Test
	public void testGetAllProducts() throws Exception {
		ArrayList<CartItem> cartItems = new ArrayList<>();
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());
		CartItem cI = new CartItem(1, 2, LocalDateTime.now(), u, p);
		cartItems.add(cI);
		
		
		when(cartItemService.getUserCart(1l)).thenReturn(cartItems);
		
		mvc.perform(get("/api/cart/{userid}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$[0].product.name").value("Milk"))
				.andExpect(jsonPath("$[0].product.quantity").value(3))
				.andExpect(jsonPath("$[0].product.price").value(2.99))
				.andExpect(jsonPath("$[0].quantity").value(2));
	}*/
	
	@Test
	void addNewCartItem() throws Exception {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());
		Optional<Product> returnP = Optional.ofNullable(p);
		when(userDetails.findById(1l)).thenReturn(u);
		when(productService.getById(1l)).thenReturn(returnP);
		
		
		mvc.perform(post("/api/cart/{userid}", 1)
		.content(toJsonString(new ProductQuantity(2, 1l)))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deleteCartItem() throws Exception {
		mvc.perform(delete("/api/cart/{userid}/product/{productid}", 1, 1))
				.andExpect(status().isAccepted());
	}
	
	@Test
	public void deleteAllCartItems() throws Exception {
		mvc.perform(delete("/api/cart/{userid}", 1))
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
