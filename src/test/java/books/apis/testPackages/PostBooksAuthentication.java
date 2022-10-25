package books.apis.testPackages;


import org.testng.annotations.Test;

import books.apis.utils.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class PostBooksAuthentication {

	Configuration config = Configuration.getInstance();
	
	@Test(enabled = true)
	public void createAuthentication() {
		RestAssured
		.given()
		.baseUri(config.get("baseUrl"))
		.contentType(ContentType.JSON)
		.body("{\"clientName\" : \"Postman\", \"clientEmail\" : \"mike@example.com\"}")
		.log().all()
		.post(config.get("authenticateEndpoint"))
		.prettyPrint();
	}
	
	//accessToken created
	//"accessToken": "8e808c428224e465b8b0ff1f97422acb4b6ee408255e8ca6aeac4aa9b41e7be2"
	
	@Test(enabled = false)
	public void negativeTestAuthentication() {
		RestAssured
		.given()
		.baseUri(config.get("baseUrl"))
		.contentType(ContentType.JSON)
		.body("{\"clientName\" : \"Postman\", \"clientEmail\" : \"mike@example.com\"}")
		.log().all()
		.post(config.get("authenticateEndpoint"))
		.then()
		.statusCode(409)
		.log().all();
		}



}
