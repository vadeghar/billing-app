package com.mytest.billapp.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mytest.billapp.dto.SessionUser;
import com.mytest.billapp.model.Permissions;
import com.mytest.billapp.model.Role;
import com.mytest.billapp.model.User;
import com.mytest.billapp.repsitory.RoleRepository;
import com.mytest.billapp.repsitory.UserRepository;
import com.mytest.billapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	RoleRepository roleRepository;
	
	//@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public User save(User entity) {
		/*Role userRole = roleRepository.findByRole("ADMIN");
		if(StringUtils.containsIgnoreCase(entity.getEmail(), "user"))
			userRole = roleRepository.findByRole("USER");
		entity.setRoles(new HashSet<Role>(Arrays.asList(userRole)));*/
		return userRepository.save(entity);
	}
	
	public List<User> save(List<User> entities) {
		return userRepository.save(entities);
	}
	
	public void flush() {
		userRepository.flush();
	}
	
	public void deleteById(Long id) {
		userRepository.delete(id);
	}
	
	public void delete(User entity) {
		userRepository.delete(entity);
	}
	
	public void deleteAll(List<User> entities) {
		userRepository.delete(entities);
	}
	
	public void deleteInBatch(List<User> entities) {
		userRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public User getOne(Long id) {
		return userRepository.getOne(id);
	}
	public User findById(Long id) {
		return userRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return userRepository.exists(id);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<User> findAll(Sort sort) {
		return userRepository.findAll(sort);
	}
	
	public long count() {
		return userRepository.count();
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User saveAndFlush(User entity) {
		return userRepository.saveAndFlush(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public SessionUser getUserDetails(String userName) {
		User user = findByEmail(userName);
		if(user == null)
			user = findByUserName(userName);
		Set<Role> userRoles = user.getRoles();
		Map<String, String> userPermissions = new HashMap<>();
		if(CollectionUtils.isNotEmpty(userRoles)) {
			for(Role r : userRoles) {
				if(CollectionUtils.isEmpty(r.getPermissions())) continue;
				for(Permissions p : r.getPermissions()) {
					userPermissions.put(p.getName(), p.getLink());
				}
			}
		}
		SessionUser sessionUser = null;
		if(user != null) {
			sessionUser = new SessionUser(user.getUserName(), user.getEmail(), true);
			for(Role role: user.getRoles()) {
				if(role.getRole().equals("ADMIN"))
					sessionUser.setRole("Admin");
				else
					sessionUser.setRole("User");
			}
			sessionUser.setUserPermissions(userPermissions);	
		}
		return sessionUser;
	}

}
