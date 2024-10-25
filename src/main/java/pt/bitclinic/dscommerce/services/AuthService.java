package pt.bitclinic.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.bitclinic.dscommerce.entities.User;
import pt.bitclinic.dscommerce.exceptions.ForbiddenException;

@Service
public class AuthService {
	
	@Autowired
	private UserService userService;
	
	public void validadeSelfOrAdmin(Long userId) {
		User me = userService.authenticated();
		
		if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
			throw new ForbiddenException("Access denied");
		}
	}

}
