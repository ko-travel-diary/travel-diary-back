package com.traveldiary.back.dto.response;

public interface ResponseCode {

    String SUCCESS = "SU";
    String VARIDATION_FAILED = "VF";
    String DUPLICATED_ID = "DI";
    String DUPLICATED_EMAIL = "DE";
    String DUPLICATED_NICK_NAME = "DN";
    String DUPLICATED_TOUR_ATTRACTIONS = "DT";
    String DUPLICATED_RESTAURANT = "DR";
    String NO_EXIST_BOARD = "NB";
    String NO_EXIST_DATA = "ND";
    String NO_EXIST_COMMENT = "NC";
    String NO_EXIST_USER = "NU";
    String WRITTEN_COMMENT = "WC";
    String SIGN_IN_FAILED = "SF";
    String AUTHENTICATION_FAILED = "AF";
    String AUTHORIZATION_FAILED = "AF";
    String NOT_FOUND = "NF";
    String TOKEN_CREATION_FAILED = "TF";
    String MAIL_SEND_FAILED = "MF";
    String DATABASE_ERROR = "DBE";

}