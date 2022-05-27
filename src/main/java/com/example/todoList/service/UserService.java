package com.example.todoList.service;

import com.example.todoList.entity.Role;
import com.example.todoList.entity.User;
import com.example.todoList.exception.UserNotFoundException;
import com.example.todoList.repository.UserRepository;
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

  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


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

  public boolean saveUser(User user) {
    user.setRoles(user.getRoles());
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);
    return true;
  }

  public User getUserById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(String.format("user with id %s doesn't not exist", id)));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
