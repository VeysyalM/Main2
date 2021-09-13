package com.colin;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import com.colin.models.CartItem;
import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.CartItemRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class CartItemRepositoryTest {

	@Autowired
	CartItemRepository cartItemRepository;
	
	/*@Test
	public void testGetCartItems() {
		List<CartItem> cartItem = (List<CartItem>) cartItemRepository.findAll();
		
		assertTrue(cartItem.get(0).getQuantity() == 2);
	}*/
	
	/*@Test
	public void testSaveCartItem() {
		Category c = new Category(1, "fruit", new ArrayList<>());
		Product p = new Product(1, "apple", 5, 5, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(4, "Percy", "pepperbox", roles, true, new ArrayList<>());
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		String encryptP = b.encode(u.getPassword());
		u.setPassword(encryptP);
		CartItem cI = new CartItem(1, 2, LocalDateTime.now(), u, p);
		
		CartItem cI2 = cartItemRepository.save(cI);
		assertNotNull(cI2);
		assertTrue(cI.getQuantity() == cI2.getQuantity());
		assertTrue(cI.getProduct().getName().equals(cI2.getProduct().getName()));
	}*/
	
	/*@Test
	public void testDelteCartItem() {
		Category c = new Category(1, "fruit", new ArrayList<>());
		Product p = new Product(1, "apple", 5, 5, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(4, "Percy", "pepperbox", roles, true, new ArrayList<>());
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		String encryptP = b.encode(u.getPassword());
		u.setPassword(encryptP);
		CartItem cI = new CartItem(20, 1, LocalDateTime.now(), u, p);
		CartItem cI2 = cartItemRepository.save(cI);
		
		cartItemRepository.deleteById(cI2.getId());
		
		assertNull(cartItemRepository.findById(20l).orElse(null));
		
	}*/

	
}
