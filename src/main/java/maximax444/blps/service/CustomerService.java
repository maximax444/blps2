package maximax444.blps.service;


import maximax444.blps.entity.Customer;
import maximax444.blps.entity.Role;
import maximax444.blps.exceptions.AuthException;
import maximax444.blps.exceptions.EntityAlreadyExistsException;
import maximax444.blps.repository.CustomerRepository;
import maximax444.blps.security.MyResourceNotFoundException;
import lombok.AllArgsConstructor;
import maximax444.blps.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("customerInterface")
public class CustomerService implements CustomerInterface {

	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final RolesService rolesService;

	private final ProductInterface productService;


	private final CustomerRepository customerRepository;

	@Override
	public String registration(String username, String password) {


		Role userRole = new Role();
		userRole.setName("CLIENT");

		Customer user = new Customer();
		user.setUserName(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(userRole);
		customerRepository.save(user);

		return jwtTokenProvider.createToken(username, userRole);
	}

	@Override
	public String login(String username, String password) throws AuthException {
		Customer userFromDataBase = findByName(username);

		if (passwordEncoder.matches(password, userFromDataBase.getPassword())) {
			return jwtTokenProvider.createToken(username, userFromDataBase.getRole());
		}

		throw new AuthException();
	}


	@Override
	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.getAllUsers();
	}
//
//	@Override
//	public Optional<User> findByToken(String token) {
//		Optional<Customer> customer = customerRepository.findByToken(token);
//		if (customer.isPresent()) {
//			Customer customer1 = customer.get();
//			User user = new User(customer1.getUserName(), customer1.getPassword(), true, true, true, true,
//					AuthorityUtils.createAuthorityList("USER"));
//			return Optional.of(user);
//		}
//		return Optional.empty();
//	}
//
	@Override
	public Customer findByName(String userName) {
		Optional<Customer> customer = customerRepository.findAllByUserName(userName);
		return customer.orElseThrow(() -> new MyResourceNotFoundException("Пользователь не найден"));
	}

	@Override
	public Customer getUserFromContext() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByName(userName);
	}
//
//
//
//	@Override
//	public Optional<Customer> whoIs(HttpServletRequest httpServletRequest) {
//		String token = StringUtils.isNotEmpty(httpServletRequest.getHeader(AUTHORIZATION)) ?
//				httpServletRequest.getHeader(AUTHORIZATION) : "";
//		token = StringUtils.removeStart(token, "Bearer").trim();
//		return customerRepository.findByToken(token);
//	}
//
//	@Override
//	@Transactional
//	public void deleteMe(HttpServletRequest httpServletRequest) {
//		var customerOpt = whoIs(httpServletRequest);
//		if (customerOpt.isEmpty()) {
//			throw new MyResourceNotFoundException("Ошибка авторизации");
//		}
//		var customer = customerOpt.get();
//		productService.deleteAllByOwner(customer);
//		customerRepository.delete(customer);
//	}
//
//
//	@Override
//	public void save(Customer customer) {
//		customerRepository.save(customer);
//	}
}
