package ms.ynov.userapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ms.ynov.userapi.model.User;
import ms.ynov.userapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
 
	@Transactional(readOnly = true)
	public Optional<User> findByMail(String mail) {
		Optional<User> user = null;
		try {
			user = userRepository.findByMail(mail);
		} catch (Exception e) {
			throw e;
		}
		return user;
	}
}
