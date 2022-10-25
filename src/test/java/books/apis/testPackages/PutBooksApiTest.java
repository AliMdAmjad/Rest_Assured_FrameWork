package books.apis.testPackages;


	import java.util.HashMap;
	import java.util.Map;
	import org.testng.annotations.Test;
	import books.apis.utils.Configuration;
	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;



	public class PutBooksApiTest {
		
		Configuration config = Configuration.getInstance();

		Map<String, Object> map = new HashMap<String , Object>();
		
		public Map<String, Object> getMap(){
			map.put("bookID", 6);
			map.put("customerName", "Mike Tyson");
			return map;
		}

		@Test
		public void putCallUpdateOrder() {
			getMap();
			RestAssured
				.given().auth()
				.oauth2(config.get("accessToken"))
				.baseUri(config.get("baseUrl"))
				.contentType(ContentType.JSON)
				.body(map)
				.log().all()
				.patch(config.get("ordersEndpoint")+"/"+ "pUCMwi-TXpCFLZNONmi0G")
				.then()
				.log().all();
		}

}
