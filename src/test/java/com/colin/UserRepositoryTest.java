package com.colin;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	/*@Test
	public void testSave() {
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(10, "Vex", "trinket", roles, true, new ArrayList<>());
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		String encryptP = b.encode(u.getPassword());
		u.setPassword(encryptP);
		User u2 = userRepository.save(u);
		assertNotNull(u2);
		assertTrue(u.getUsername().equals(u2.getUsername()));
	}*/
}
