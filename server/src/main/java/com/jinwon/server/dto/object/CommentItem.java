package com.jinwon.server.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentItem {
  private String nickName;
  private String profileImage;
  private String writeDatetime;
  private String comment;
}
