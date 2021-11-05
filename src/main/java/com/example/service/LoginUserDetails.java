package com.example.service;

import com.example.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;
import lombok.Data;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
  private final User user;

  public LoginUserDetails(User user) {
    super(user.getUserName(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    this.user = user;
  }
}
