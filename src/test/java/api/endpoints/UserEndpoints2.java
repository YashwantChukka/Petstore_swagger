
package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//This is userendpoint.java class created to perfom
//CRUD Operations
//These endpoints method will call from test case classes



public class UserEndpoints2 {
	
	static ResourceBundle getURL() {
		
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load properties file name of the properties file
		return routes;
	}
	
	
	
	
	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("post_url");
		
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
			   .post(post_url);
		
		return response;
		
		//just pre-requisite and body no validations
		//Capturing the input (i.e that's what payload does)
		
		
		
	}
	
public static Response readUser(String userName) {
	
	String get_url = getURL().getString("get_url");
		
		Response response = given()
				.pathParam("username", userName)
				
		   .when()
		       .get(get_url);
		
		return response;
		
		
		
	}

public static Response updateUser(String userName,User payload) {
	
	String update_url = getURL().getString("update_url");
	
	Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
		   .put(update_url);
	
	return response;
	
	//just pre-requisite and body no validations
	//Capturing the input
	
	
	
}

public static Response deleteUser(String userName) {
	
	String delete_url = getURL().getString("delete_url");
	
	Response response = given()
			
			.pathParam("username", userName)
			
		.when()
		   .delete(delete_url);
	
	return response;
	
	//just pre-requisite and body no validations
	//Capturing the input
	
	
	
}

}