package com.example.domain;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
//
@ToString(exclude = "customers")
public class User {
  @Id
  private String userName;
  // Rest APIでUserクラスをJSON出力する場合に、ID/Passは除外する
  @JsonIgnore
  private String encodedPassword;
  @JsonIgnore
  // 関連するEntityを遅延ロードさせる
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  private List<Customer> customers;
}
