package books.apis.testPackages;

import org.testng.annotations.Test;

import books.apis.utils.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostABookOrder {
	
	
Configuration config = Configuration.getInstance();
	
	@Test(enabled = false)
	public void negativePostBookOrder() {
		RestAssured
			.given()
			.body("{\"bookId\" : 3, \"customerName\" : \"Mike\"}")
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.post("https://simple-books-api.glitch.me/orders")
			.then()
			.statusCode(401) //unAuthorizied
			.log().all();
	}
	
	@Test(enabled = true)
	public void postBookOrder() {
		RestAssured
			.given().auth()
			.oauth2(config.get("accessToken"))
			.body("{\"bookId\" : 3, \"customerName\" : \"Mike\"}")
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.post("https://simple-books-api.glitch.me/orders")
			.then()
			.statusCode(201)
			.log().all();
		//created
		//"orderId": "pUCMwi-TXpCFLZNONmi0G"
		}

}
