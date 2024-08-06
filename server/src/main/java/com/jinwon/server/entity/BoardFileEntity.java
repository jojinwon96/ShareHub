package com.jinwon.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "baordFile")
@Table(name="tbl_boardfiles")
public class BoardFileEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int sequence;

  private int boardNumber;

  private String fileUrl;
}
