package com.example.todoList.service;

import com.example.todoList.entity.Role;
import com.example.todoList.entity.User;
import com.example.todoList.exception.UserNotFoundException;
import com.example.todoList.exception.UserValidationException;
import com.example.todoList.repository.UserRepository;
import com.example.todoList.util.converter.UserToUserDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserToUserDtoConverter converter;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UserNotFoundException(String.format("user with username %s doesn't not exist", username)));
    return new org.springframework.security.core.userdetails.User
      (user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }

  public User save(User user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setRoles(user.getRoles());
      user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

      userRepository.save(user);
      return user;
    }
    throw new UserValidationException(String.format("user with username %s already exist", user.getUsername()));
  }

  public User getUserById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(String.format("user with id %s doesn't not exist", id)));
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }
}
