package com.jinwon.server.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {
  private int boardNumber;
  private String boardType;
  private String title;
  private String content;
  private String viewCount;
  private String writeDatetime;
  private String rewriteDatetime;
  private String writerNickname;
  private String writerProfile;
}
