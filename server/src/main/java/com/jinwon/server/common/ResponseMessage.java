package com.jinwon.server.common;

public interface ResponseMessage {
  // HTTP Status 200
  String SUCCESS = "Success";

  // HTTP Status 400
  String VALIDATION_FAILED= "Valication failed";
  String DUPLICATE_EMAIL = "Duplicate email";
  String DUPLICATE_NICKNAME = "Duplicate nickname";
  String NOT_EXISTED_USER = "This user does not exist";
  String NOT_EXISTED_BOARD = "This board does not exist";

  // HTTP Status 401
  String SIGN_IN_FAIL = "Login Inforamint missmatch";
  String AUTHORIZATION_FAIL = "Authorization failed";

  // HTTP Status 403
  String NO_PERMISSTION = "Do not have permission";

  // HTTP Status 500
  String DATABASE_ERROR = "Database error";
} 