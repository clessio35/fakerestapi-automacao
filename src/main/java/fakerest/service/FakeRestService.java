package fakerest.service;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import com.github.javafaker.Faker;

import fakerest.utils.EvidenceUtils;
import fakerest.utils.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class FakeRestService {

	private Response response;
	
	public void accessApi(String url) {
		System.out.println("Access api");
		RestAssured.baseURI = url;
	}

	public void sendRequestGetForEndpoint(String endpoint) throws IOException {
		System.out.println("REQUEST GET -> " + endpoint );
		response = RestAssured.given().log().body()
				.when().contentType(ContentType.JSON)
				.get(endpoint);
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseBookListComplete() throws IOException {
		System.out.println("VALIDATE REPONSE OF BOOK LIST");
		response.then().log().body().statusCode(200)
		.body("id", everyItem(notNullValue()))
	    .body("title", everyItem(notNullValue()))
	    .body("description", everyItem(notNullValue()))
	    .body("pageCount", everyItem(notNullValue()))
	    .body("excerpt", everyItem(notNullValue()))
	    .body("publishDate", everyItem(notNullValue()));
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}
	
	public Integer captureIdBook(String endpoint) {
		System.out.println("Capture id");
		response = RestAssured.given().log().body()
				.when().contentType(ContentType.JSON)
				.get(endpoint);
				response.then().statusCode(200);
		List<Integer> ids = new ArrayList<Integer>();
		ids = response.jsonPath().getList("id");
		System.out.println(ids.stream().findFirst().orElse(-1));
		return ids.stream().findFirst().orElse(-1);
	}

	public void sendRequestGetForSpecificId(String endpoint) throws IOException {
		System.out.println("Send request for specific id");
		int id = captureIdBook(endpoint);
		response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().get(endpoint + id);
	}

	public void validateResponseSpecificBookId() throws IOException {
		System.out.println("Validate response of book id");
		int id = response.jsonPath().getInt("id");
		response.then().statusCode(200).log().body().statusCode(200)
			.body("id", Matchers.equalTo(id));
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseWithErrorCode(String status) throws IOException {
		int sc = Integer.parseInt(status);
		System.out.println("Validate response with error");
		response.then().log().body().statusCode(200)
			.body("title", Matchers.equalTo("Not Found"))
			.body("status", Matchers.equalTo(sc));
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}
	
	public JSONObject payloadBooks() {
		Faker fake = new Faker();
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("id", fake.number().randomDigitNotZero());
		info.put("title", fake.book().title());
		info.put("description", fake.lorem().sentence(10));
		info.put("pageCount", fake.number().numberBetween(100, 1000));
		info.put("excerpt", fake.lorem().paragraph());
		JSONObject json = new JSONObject(info);
		return json;
	}
	
	public JSONObject payloadActivities() {
		Faker fake = new Faker();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String fDate = sdf.format(date);
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("id", fake.number().randomDigitNotZero());
		info.put("title", fake.book().title());
		info.put("dueDate", fDate);
		info.put("completed", true);
		JSONObject json = new JSONObject(info);
		return json;
	}
	
	public JSONObject payloadAuthors() {
		Faker fake = new Faker();
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("id", fake.number().randomDigitNotZero());
		info.put("idBook", fake.number().randomDigitNotZero());
		info.put("firstName", fake.name().firstName());
		info.put("lastName", fake.name().lastName());
		JSONObject json = new JSONObject(info);
		return json;
	}
	public JSONObject payloadUsers() {
		Faker fake = new Faker();
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("id", fake.number().randomDigitNotZero());
		info.put("userName", fake.name().firstName());
		info.put("password", fake.internet().password());
		JSONObject json = new JSONObject(info);
		return json;
	}
	public JSONObject payloadCoverPhotos() {
		Faker fake = new Faker();
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("id", fake.number().randomDigitNotZero());
		info.put("idBook", fake.number().digits(3));
		info.put("url", fake.internet().url());
		JSONObject json = new JSONObject(info);
		return json;
	}

	public void sendRequestPostForEndpoint(String endpoint) {
		System.out.println("Post method");
		if (endpoint.equalsIgnoreCase("/books")) {
			response = RestAssured.given().log().body()
					.when().contentType(ContentType.JSON)
					.body(payloadBooks().toString()).post(endpoint);
		}else if(endpoint.equalsIgnoreCase("/Activities")) {
			response = RestAssured.given().log().body()
					.when().contentType(ContentType.JSON)
					.body(payloadActivities().toString()).post(endpoint);
		}else if(endpoint.equalsIgnoreCase("/Authors")) {
			response = RestAssured.given().log().body()
					.when().contentType(ContentType.JSON)
					.body(payloadAuthors().toString()).post(endpoint);
		}else if(endpoint.equalsIgnoreCase("/Users")) {
			response = RestAssured.given().log().body()
					.when().contentType(ContentType.JSON)
					.body(payloadUsers().toString()).post(endpoint);
		}else if(endpoint.equalsIgnoreCase("/CoverPhotos")) {
			response = RestAssured.given().log().body()
					.when().contentType(ContentType.JSON)
					.body(payloadCoverPhotos().toString()).post(endpoint);
		}
	}

	public void validateResponsePostMethod(String endpoint) throws IOException {
		System.out.println("Validate Post method");
		ValidatableResponse resp = response.then().statusCode(200).log().body();
		if (endpoint.equalsIgnoreCase("/books")) {
			resp.body("id", Matchers.instanceOf(Integer.class))
		    .body("title", Matchers.instanceOf(String.class))
		    .body("description", Matchers.instanceOf(String.class))
		    .body("pageCount", Matchers.instanceOf(Integer.class))
		    .body("excerpt", Matchers.instanceOf(String.class))
		    .body("publishDate", Matchers.notNullValue())
		    .extract().response();
		}else if(endpoint.equalsIgnoreCase("/Activities")) {
			resp.body("id", Matchers.instanceOf(Integer.class))
		    .body("title", Matchers.instanceOf(String.class))
		    .body("dueDate", Matchers.instanceOf(String.class))
		    .body("completed", Matchers.instanceOf(Boolean.class))
			.extract().response();
		}else if(endpoint.equalsIgnoreCase("/Authors")) {
			resp.body("id", Matchers.instanceOf(Integer.class))
			.body("idBook", Matchers.instanceOf(Integer.class))
			.body("firstName", Matchers.instanceOf(String.class))
			.body("lastName", Matchers.instanceOf(String.class))
			.extract().response();
		}else if(endpoint.equalsIgnoreCase("/Users")) {
			resp.body("id", Matchers.instanceOf(Integer.class))
			.body("userName", Matchers.instanceOf(String.class))
			.body("password", Matchers.instanceOf(String.class))
			.extract().response();
		}else if(endpoint.equalsIgnoreCase("/CoverPhotos")) {
			resp.body("id", Matchers.instanceOf(Integer.class))
			.body("idBook", Matchers.instanceOf(Integer.class))
			.body("url", Matchers.instanceOf(String.class))
			.extract().response();
		}
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public JSONObject invalidPayload() {
		Faker fake = new Faker();
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("id", "");
		info.put("title", fake.book().title());
		info.put("description", fake.lorem().sentence(10));
		info.put("pageCount", "");
		info.put("excerpt", fake.lorem().paragraph());
		JSONObject json = new JSONObject(info);
		return json;
	}
	public void sendRequestPostWithInvalidData(String endpoint) {
		System.out.println("Post method with invalid data");
		response = RestAssured.given().log().body()
					.when().contentType(ContentType.JSON)
					.body(invalidPayload().toString()).post(endpoint);
	}
	public void validateResponseWithResponseError() throws IOException {
		System.out.println("Validate invalid Post method");
		response.then().log().body()
		.statusCode(400)
	    .body("title", Matchers.equalTo("One or more validation errors occurred."))
	    .body("status", Matchers.instanceOf(Integer.class));
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void sendRequestPutForEndpoint(String endpoint) {
		System.out.println("Send request PUT");
		int id = captureIdBook(endpoint);
		if (endpoint.equalsIgnoreCase("/books")) {
			System.out.println("PUT -> /books");
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().body(payloadBooks().toString()).put(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/Activities/")) {
			System.out.println("PUT -> /Activities");
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().body(payloadActivities().toString()).put(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/Authors/")) {
			System.out.println("PUT -> /Authors/");
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().body(payloadAuthors().toString()).put(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/Users/")) {
			System.out.println("PUT -> /Users/");
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().body(payloadUsers().toString()).put(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/Users/")) {
			System.out.println("PUT -> /Users/");
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().body(payloadUsers().toString()).put(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/CoverPhotos/")) {
			System.out.println("PUT -> /CoverPhotos/");
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().body(payloadCoverPhotos().toString()).put(endpoint + id);
		}
	}

	public void validateResponseUpdateMethod(String statusCode, String endpoint) throws IOException {
		System.out.println("Validate response with Put method");
		ValidatableResponse request = response.then().log().body()
		.statusCode(200);
		if(endpoint.equalsIgnoreCase("/books")){
			request.body("id", Matchers.instanceOf(Integer.class))
			.body("title", Matchers.instanceOf(String.class))
			.body("description", Matchers.instanceOf(String.class))
			.body("pageCount", Matchers.instanceOf(Integer.class))
			.body("excerpt", Matchers.instanceOf(String.class))
			.body("publishDate", Matchers.notNullValue());
		}else if(endpoint.equalsIgnoreCase("/Activities")) {
			request.body("id", Matchers.instanceOf(Integer.class))
		    .body("title", Matchers.instanceOf(String.class))
		    .body("dueDate", Matchers.instanceOf(String.class))
		    .body("completed", Matchers.instanceOf(Boolean.class));
		}else if(endpoint.equalsIgnoreCase("/Authors")) {
			request.body("id", Matchers.instanceOf(Integer.class))
			.body("idBook", Matchers.instanceOf(Integer.class))
			.body("firstName", Matchers.instanceOf(String.class))
			.body("lastName", Matchers.instanceOf(String.class))
			.extract().response();
		}else if(endpoint.equalsIgnoreCase("/Users")) {
			request.body("id", Matchers.instanceOf(Integer.class))
			.body("userName", Matchers.instanceOf(String.class))
			.body("password", Matchers.instanceOf(String.class))
			.extract().response();
		}else if(endpoint.equalsIgnoreCase("/CoverPhotos")) {
			request.body("id", Matchers.instanceOf(Integer.class))
			.body("idBook", Matchers.instanceOf(Integer.class))
			.body("url", Matchers.instanceOf(String.class))
			.extract().response();
		}
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void sendRequestDeleteWithEndpoint(String endpoint) {
		System.out.println("Send request Delete");
		if(endpoint.equalsIgnoreCase("/Books")) {
			int id = captureIdBook(endpoint);
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().delete(endpoint + id);			
		}else if(endpoint.equalsIgnoreCase("/Activities/")) {
			int id = captureIdBook(endpoint);
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().delete(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/Authors/")) {
			int id = captureIdBook(endpoint);
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().delete(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/Users/")) {
			int id = captureIdBook(endpoint);
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().delete(endpoint + id);
		}else if(endpoint.equalsIgnoreCase("/CoverPhotos/")) {
			int id = captureIdBook(endpoint);
			response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().delete(endpoint + id);
		}
	}

	public void validateResponseDeleteMethod(String statusCode) throws IOException {
		System.out.println("Validate status code delete method");
		response.then().log().body()
			.statusCode(200);
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseWithCompleteListActivities() throws IOException {
		System.out.println("Validate List complete");
		response.then().log().body().statusCode(200);
		List<Map<String, Object>> list = response.jsonPath().getList("$");
		for (Map<String, Object> item : list) {
			Assert.assertTrue(item.get("id").getClass().equals(Integer.class));
			Assert.assertTrue(item.get("title").getClass().equals(String.class));
			Assert.assertTrue(item.get("dueDate").getClass().equals(String.class));
			Assert.assertTrue(item.get("completed").getClass().equals(Boolean.class));
	    }
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseAuthorsCompleteList() throws IOException {
		System.out.println("Validate complete list");
		response.then().statusCode(200).log().body().extract().response();
		List<Map<String, Object>> authors = response.jsonPath().getList("$");
		for(Map<String, Object> author : authors) {
			int id = (int) author.get("id");
			int idBook = (int) author.get("idBook");
			String firstName = (String) author.get("firstName");
			String lastName = (String) author.get("lastName");
			Assert.assertNotNull(id);
			Assert.assertNotNull(idBook);
			Assert.assertNotNull(firstName, "firstName is null or empty");
			Assert.assertNotNull(lastName, "lastName  is null or empty");
			System.out.println("validate!");
		}
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseSpecificAuthor() throws IOException {
		System.out.println("Validate Specific author");
		response.then().statusCode(200).log().body()
			.body("id", Matchers.instanceOf(Integer.class))
			.body("idBook", Matchers.instanceOf(Integer.class))
			.body("firstName", Matchers.instanceOf(String.class))
			.body("lastName", Matchers.instanceOf(String.class))
			.extract().response();
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseWithBookAuthors() throws IOException {
		System.out.println("Validate response with book authors");
		response.then().statusCode(200).log().body()
			.extract().response();
		List<Map<String, Object>> authors = response.jsonPath().getList("$");
		for(Map<String, Object> author : authors) {
			int id = (int) author.get("id");
			int idBook = (int) author.get("idBook");
			String firstName = (String) author.get("firstName");
			String lastName = (String) author.get("lastName");
			Assert.assertNotNull(id);
			Assert.assertNotNull(idBook);
			Assert.assertNotNull(firstName, "firstName is null or empty");
			Assert.assertNotNull(lastName, "lastName  is null or empty");
		}
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseCompleteUserList() throws IOException {
		System.out.println("Validate Complete UserList");
		response.then().statusCode(200).log().body().extract();
		List<Map<String, Object>> userList = response.jsonPath().getList("$");
		for(Map<String, Object> user : userList) {
			int id = (Integer) user.get("id");
			String userName = (String) user.get("userName");
			String password = (String) user.get("password");
			 Assert.assertNotNull("User ID is null", id);
		        Assert.assertNotNull("Username is null for user with ID: " + id, userName);
		        Assert.assertNotNull("Password is null for user with ID: " + id, password);
		}
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseSpecificUser() throws IOException {
		System.out.println("Validate Specific user");
		response.then().statusCode(200).log().body()
			.body("id", Matchers.instanceOf(Integer.class))
			.body("userName", Matchers.instanceOf(String.class))
			.body("password", Matchers.instanceOf(String.class))
			.extract();
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseWithCompleteCapasList() throws IOException {
		System.out.println("Validate complete capas list");
		response.then().statusCode(200).log().body().extract();
		List<Map<String, Object>> photos = response.jsonPath().getList("$");
		for(Map<String, Object> photo : photos) {
			int id = (int) photo.get("id");
			int idBoot = (int) photo.get("idBook");
			String url = (String) photo.get("url");
			Assert.assertNotNull("is null or empty", id);
			Assert.assertNotNull("is null or empty", idBoot);
			Assert.assertNotNull("url is null or empty", url);
		}
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseSpecificPhoto() throws IOException {
		System.out.println("Validate Specific photo");
		response.then().statusCode(200).log().body()
		.body("id", Matchers.instanceOf(Integer.class))
		.body("idBook", Matchers.instanceOf(Integer.class))
		.body("url", Matchers.instanceOf(String.class))
		.extract();
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseCoverPhoto() throws IOException {
		System.out.println("Validate cover photo");
		response.then().statusCode(200).log().body()
		.body("[0].id", Matchers.instanceOf(Integer.class))
		.body("[0].idBook", Matchers.instanceOf(Integer.class))
		.body("[0].url", Matchers.instanceOf(String.class))
		.extract();
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}


	
}
