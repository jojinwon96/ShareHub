package com.jinwon.server.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="user")
@Table(name="user")
public class UserEntity {
  
  @Id
  private String email;
  private String password;
  private String nickname;
  private String profile;

}
