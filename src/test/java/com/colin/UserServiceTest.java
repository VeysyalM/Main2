package com.colin;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import com.colin.models.Category;
import com.colin.models.DummyUser;
import com.colin.models.Product;
import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.ProductRepository;
import com.colin.repo.UserRepository;
import com.colin.service.ProductService;
import com.colin.service.UserDetailsServiceImpl;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

	@MockBean
	UserRepository userRepository;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	/*
	 * @Test public void testSaveProduct() { Set<Role> roleSet = new HashSet<>();
	 * Role r = new Role(); r.setName("ROLE_USER"); roleSet.add(r); List<String>
	 * roles = new ArrayList<>(); roles.add("ROLE_USER"); DummyUser u = new
	 * DummyUser("Percy", "pepperbox", roles); User u2 = new User(1, "Percy",
	 * "pepperbox", roleSet, true, new ArrayList<>());
	 * 
	 * 
	 * when(userRepository.save(u2)).thenReturn(u2);
	 * 
	 * assertTrue(userService.createNewUser(u)); assertNotNull(u); }
	 */
}
