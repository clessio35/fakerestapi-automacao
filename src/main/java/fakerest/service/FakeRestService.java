package fakerest.service;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.github.javafaker.Faker;

import fakerest.utils.EvidenceUtils;
import fakerest.utils.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
		return ids.stream().findFirst().orElse(-1);
	}

	public void sendRequestGetForSpecificId(String endpoint) throws IOException {
		System.out.println("Send request for specific id");
		int id = captureIdBook(endpoint);
		response = RestAssured.given().log().body()
					.contentType(ContentType.JSON)
					.when().get(endpoint + id);
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
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
	
	public JSONObject payload() {
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

	public void sendRequestPostForEndpoint(String endpoint) {
		System.out.println("Post method");
		response = RestAssured.given().log().body()
					.when().contentType(ContentType.JSON)
					.body(payload().toString()).post(endpoint);
	}

	public void validateResponsePostMethod() throws IOException {
		System.out.println("Validate Post method");
		response.then().log().body()
		.statusCode(200)
	    .body("id", Matchers.instanceOf(Integer.class))
	    .body("title", Matchers.instanceOf(String.class))
	    .body("description", Matchers.instanceOf(String.class))
	    .body("pageCount", Matchers.instanceOf(Integer.class))
	    .body("excerpt", Matchers.instanceOf(String.class))
	    .body("publishDate", Matchers.notNullValue());
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
}
