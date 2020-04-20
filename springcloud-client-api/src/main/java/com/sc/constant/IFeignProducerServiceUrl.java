package com.sc.constant;

public interface IFeignProducerServiceUrl {
   // String PREFIX = "http://localhost:8089";
    String PREFIX = "http://producer";
    String GETUSER = "/ifeign/getUser/{id}";
    String GETUSERS = "/ifeign/getUsers";
    String GETUSERBYID = "/ifeign/getUserById";
    String LOGIN = "/ifeign/login";

}
