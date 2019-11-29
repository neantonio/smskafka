package ru.alfastrah.smskafka.service;

import com.sun.xml.bind.v2.runtime.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.xml.soap.*;


@Service
public class SoapCallerImpl {

	@Autowired
	private RestTemplate restTemplate;


	@Value("${vhi.avis.api.baseurl}")
	private String avisUrl;


	@Value("${vhi.service.sms.action}")
	private String smsAction;

	@Value("${vhi.service.sms.endpoint}")
	private String smsEndpoint;

	@PostConstruct
	public void init(){
		//иначе wildfly подлолжит org.jboss.wsf.stack.cxf.saaj.SOAPConnectionImpl, который вызовет ошибку
		System.setProperty("javax.xml.soap.SOAPConnectionFactory", "com.sun.xml.messaging.saaj.client.p2p.HttpSOAPConnectionFactory");
	}


	public String sendSMSNotification(String phone, String text) {

		callSoapWebService(phone, text);

	}

	private String callSoapWebService(String phone, String text) {
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			// Send SOAP Message to SOAP Server
			SOAPMessage soapRequest = createSOAPRequest(phone,text);
			SOAPMessage soapResponse = soapConnection.call(soapRequest, smsEndpoint);
			return soapResponse.getSOAPBody().getAttributeValue();
			// Print the SOAP Response

		} catch (Exception e) {
			System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
			e.printStackTrace();
		}
	}
	private SOAPMessage createSOAPRequest(String phone, String text) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage, phone, text);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", smsAction);

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");

		return soapMessage;
	}
	private static void createSoapEnvelope(SOAPMessage soapMessage,String phone, String text) throws SOAPException {

		SOAPPart soapPart = soapMessage.getSOAPPart();
		String myNamespace = "ws";
		String myNamespaceURI = "http://schemas.alfastrah.ru/interplat4/ws-message-gateway-1.0";
		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("SendMessageAsync", myNamespace);

		SOAPElement message = soapBodyElem.addChildElement("Message", myNamespace);

		SOAPElement from = message.addChildElement("From", myNamespace);
		SOAPElement endpoints = message.addChildElement("Endpoints", myNamespace);
		SOAPElement headers = message.addChildElement("Headers", myNamespace);
		SOAPElement body = message.addChildElement("Body", myNamespace);

		from.setTextContent("avis");

		SOAPElement endpoint = endpoints.addChildElement("Endpoint", myNamespace);
		SOAPElement type = endpoint.addChildElement("Type", myNamespace);
		type.setTextContent("SMS");
		SOAPElement address = endpoint.addChildElement("Address", myNamespace);
		address.setTextContent(phone);

		SOAPElement header = headers.addChildElement("Header", myNamespace);
		header.addChildElement("Name", myNamespace);
		header.addChildElement("Value", myNamespace);

		SOAPElement textMessage = body.addChildElement("TextMessage", myNamespace);
		textMessage.setTextContent(text);

	}

}
