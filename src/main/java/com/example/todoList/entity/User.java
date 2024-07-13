package com.example.todoList.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.engine.jdbc.Size;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(unique = true)
  @NotNull
  String username;

  String password;

  String email;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  @JsonManagedReference
  List<Task> tasks = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  @JsonBackReference
  Set<Role> roles;
}
