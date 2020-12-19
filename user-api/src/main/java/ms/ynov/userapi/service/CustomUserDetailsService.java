package ms.ynov.userapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ms.ynov.userapi.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
 
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
 
		if (mail.trim().isEmpty()) {
			throw new UsernameNotFoundException("username is empty");
		}
 
		Optional<User> user = userService.findByMail(mail);
 
		if (user == null) {
			throw new UsernameNotFoundException("User " + mail + " not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.get().getMail(), user.get().getPassword(), new ArrayList<>());
	}
	
	public UserDetails getCurrentUser()
	{
		SecurityContext ctx = SecurityContextHolder.getContext();				
		UserDetails currentUser = (UserDetails)ctx.getAuthentication().getPrincipal();
		return currentUser;
	}
}
