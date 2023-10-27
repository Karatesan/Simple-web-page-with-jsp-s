package com.fdmgroup.GameBlog.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.GameBlog.model.Role;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {

	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public DataImport(UserRepository userRepository, PasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;	
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(!userRepository.findByUsername("admin").isPresent()) {
			Role roleAdmin = new Role("Admin");
			Role roleReader = new Role("Reader");
			Role roleAuthor = new Role("Author");
		
			
			User admin = new User("admin", passwordEncoder.encode("123"), "admin@blog.com", "defaultCity",  roleAdmin);
			userRepository.save(admin);
		
			User customer = new User("reader", passwordEncoder.encode("321"), "user@abc.com", "defaultCity",  roleReader);
			userRepository.save(customer);
			
			User author = new User("author", passwordEncoder.encode("321"), "user@abc.com", "defaultCity",  roleAuthor);
			userRepository.save(author);
		}
	}
}
