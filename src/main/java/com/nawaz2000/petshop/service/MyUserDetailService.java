package com.nawaz2000.petshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nawaz2000.petshop.dao.UserRepo;
import com.nawaz2000.petshop.entity.User;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// return new MyUserDetails(username);
		
		System.out.println("------------>Username: " + username);
		
		Optional<User> user = userRepo.findByUsername(username);
		System.out.println("-------------> Username: " + user.get().getUsername() + " Pass: " + user.get().getPassword());
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		
		return user.map(MyUserDetails :: new).get();
		
//		System.out.println("-------------------> load");
//		
//		User user = userRepo.findByEmail(username);
//		
////		if (user == null) {
////            throw new UsernameNotFoundException("Not found: " + username);
////        }
//		
//		System.out.println("-------> Email: " + user.getEmail() + " Pass: " + user.getPassword());
//		
//        return new MyUserDetails(user);
		
	}

}
