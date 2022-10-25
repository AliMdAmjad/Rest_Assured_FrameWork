package books.apis.testPackages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetBookList {
	
	@Test(enabled = false)
	public void printResponse() {
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books")
		.then()
		.extract()
		.response()
		.prettyPrint();
	}
	
	@Test(enabled = false)
	public void assertStatusCode() {
		RestAssured
			.when()
			.get("https://simple-books-api.glitch.me/books")
			.then()
			.statusCode(200);
	}
	
	@Test(enabled = false)
	public void logRequestResponse() {
		RestAssured
		.given().log().all()
		.when()
		.get("https://simple-books-api.glitch.me/books")
		.then().log().all();
	}
	
	@Test(enabled = false)
	public void assertionBody() {
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books/2")
		.then()
		.log().all() // "id": 2,   "name": "Just as I Am",
		.body("id", equalTo(2))
		.body("name", containsString("Just as I Am"))
		.body("type", containsString("non-fiction"))
		.body("available", is(false));
		
	}
	
	@Test(enabled = false)
	public void assertContentType() {
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books")
		.then()
		.contentType(ContentType.JSON);
	}
	
	@Test(enabled = false)
	public void printHeaders() {
		Headers headers =
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books")
		.then()
		.extract().headers();
		
		System.out.println(headers.toString());
	}
	
	
	@Test(enabled = false)
	public void printHeader() {
		String header =
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books")
		.then()
		.extract()
		.header("Connection");
		
		System.out.println(header);
	}
	
	@Test(enabled = false)
	public void assertAHeader() {
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books")
		.then()
		.header("Content-Length", equalTo("417"));
	}
	
	@Test(enabled = true)
	public void assertFictionBooksName() {
		Response response = 
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books?type=fiction")
		.then()
		.extract().response();
		
		JsonPath path = new JsonPath(response.asInputStream());
		List<String> list = path.get("name");
		assertThat(list, hasItem(containsStringIgnoringCase("")));
		System.out.println(list);
	}
	
	@Test(enabled =false)
	public void assertShortFictionBooksName() {
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books?type=fiction")
		.then()
		.log().all()
		.body("name", hasItem(containsStringIgnoringCase("")));
	}
	
	@Test(enabled = false)
	public void jsonPathList() {
		List<String> list = 
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books?type=fiction")
		.then()
		.extract()
		.path("name");
		System.out.println(list);
	}
	
	@Test(enabled =false)
	public void jsonPathSingleEntity() {
		String entity = 
		RestAssured
		.when()
		.get("https://simple-books-api.glitch.me/books?type=fiction")
		.then()
		.extract()
		.path("name[2]");
		System.out.println(entity);
	}

}
