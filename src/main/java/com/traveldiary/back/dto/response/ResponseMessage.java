package com.traveldiary.back.dto.response;

// Response의 공통된 message 값
public interface ResponseMessage {
    String SUCCESS = "Success.";
    String VARIDATION_FAILED = "Varidation Failed.";
    String DUPLICATED_ID = "Duplicated Id.";
    String DUPLICATED_EMAIL = "Duplicated Email.";
    String DUPLICATED_NICK_NAME = "Duplicated Nick Name";
    String NO_EXIST_BOARD = "No Exist Board.";
    String NO_EXIST_DATA = "No Exist Data";
    String NO_EXIST_COMMENT = "No Exist Comment.";
    String NO_EXIST_USER = "No Exist User";
    String WRITTEN_COMMENT ="Written Comment.";
    String SIGN_IN_FAILED = "Sign in Failed.";
    String AUTHENTICATION_FAILED = "Authentication Failed.";
    String AUTHORIZATION_FAILED = "Authorization Failed.";
    String NOT_FOUND = "Not Found.";
    String TOKEN_CREATION_FAILED = "Token creation Failed.";
    String MAIL_SEND_FAILED = "Mail send Failed.";
    String DATABASE_ERROR = "Database Error.";
}
