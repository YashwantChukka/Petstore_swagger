package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Data_Test {
	
	@Test(priority =1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userId,String userName,String fname,String lname,String useremail,String pwd,String ph) 
	{
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndpoints.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) 
	{
		
		Response response = UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	
	//These two tests will execute but make sure while taking the input data from excel sheet
	//the user ID should be different if those ID's are already present in the database it will be an issue

}
