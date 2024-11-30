
package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//This is userendpoint.java class created to perfom
//CRUD Operations
//These endpoints method will call from test case classes



public class UserEndpoints {
	
	public static Response createUser(User payload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
			   .post(Routes.post_url);
		
		return response;
		
		//just pre-requisite and body no validations
		//Capturing the input (i.e that's what payload does)
		
		
		
	}
	
public static Response readUser(String userName) {
		
		Response response = given()
				.pathParam("username", userName)
				
		   .when()
		       .get(Routes.get_url);
		
		return response;
		
		
		
	}

public static Response updateUser(String userName,User payload) {
	
	Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
		   .put(Routes.update_url);
	
	return response;
	
	//just pre-requisite and body no validations
	//Capturing the input
	
	
	
}

public static Response deleteUser(String userName) {
	
	Response response = given()
			
			.pathParam("username", userName)
			
		.when()
		   .delete(Routes.delete_url);
	
	return response;
	
	//just pre-requisite and body no validations
	//Capturing the input
	
	
	
}

}
