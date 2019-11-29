package ru.alfastrah.smskafka.web;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceSEI {
    @WebMethod
//annotation optional and is mainly used to provide a name attribute to the public method in wsdl
    String testService();
}
