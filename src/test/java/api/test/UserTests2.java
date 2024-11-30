package api.test;

import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import com.github.javafaker.Faker;

import api.payload.User;
import api.endpoints.*;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	
	public Logger logger; //for logs
	
	@BeforeClass
	public void setupData() {
         
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(0);
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
		
		
	}
	
	@Test(priority =1)
	public void testPostUser() {
		
		logger.info("********* Creating user *******");
	
	 Response response = UserEndpoints2.createUser(userPayload);
	 response.then().log().all();
	 
	 Assert.assertEquals(response.getStatusCode(), 200);
	 
	 logger.info("********* user is created *********");
	 
	 
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("********* Reading user info *********");
		
		Response response = UserEndpoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
	    response.statusCode();	
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********* User info is displayed *********");
		
		//When using assertion Use get status code
		
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		//update data using Payload
		
		logger.info("********* User updated *********");
	
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		//Another way of doing assertions
		
		response.then().log().body().statusCode(200);
		
		response.then().log().body();
		
		//chai assertions which comes along with the rest assured library
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		Response responseAfterUpdate = UserEndpoints2.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("********* Deleting User *********");
		
	  Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
	  Assert.assertEquals(response.getStatusCode(), 200);
	  
	  logger.info("********* User Deleted *********");
	}
	
	
	
	
}
