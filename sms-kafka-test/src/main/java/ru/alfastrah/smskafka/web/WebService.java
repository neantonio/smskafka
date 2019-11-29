package ru.alfastrah.smskafka.web;

import javax.jws.WebMethod;

@javax.jws.WebService(endpointInterface = "ru.alfastrah.smskafka.web.WebServiceSEI", serviceName = "HelloSoap")
public class WebService implements WebServiceSEI {

    public String testService() {
        return null;
    }
}
