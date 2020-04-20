package com.sc.constant;

public interface IProducerServiceUrl {
   // String PREFIX = "http://localhost:8089";
    String PREFIX = "http://producer";
    String GETUSER = "/feign/getUser/{id}";
    String GETUSERS = "/feign/getUsers";
    String GETUSERBYID = "/feign/getUserById";
    String LOGIN = "/feign/login";

}
