package com.example.todoList.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
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
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends RepresentationModel<User> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(unique = true)
  @NotNull
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<Task> tasks = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  @JsonBackReference
  private Set<Role> roles;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return username.equals(user.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
