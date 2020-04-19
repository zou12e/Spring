package com.sc.constant;

public interface IProducerServiceUrl {
   // String PREFIX = "http://localhost:8089";
    String PREFIX = "http://producer";
    String GETUSER = "/getUser/{id}";
    String GETUSERS = "/getUsers";
    String GETUSERBYID = "/getUserById";
    String LOGIN = "/login";

}
