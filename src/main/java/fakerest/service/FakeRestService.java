package fakerest.service;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;

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
		response.then().statusCode(200).log().body()
			.body("id", Matchers.equalTo(id));
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}

	public void validateResponseWithErrorCode(String status) throws IOException {
		int sc = Integer.parseInt(status);
		System.out.println("Validate response with error");
		response.then().log().body()
			.body("title", Matchers.equalTo("Not Found"))
			.body("status", Matchers.equalTo(sc));
		EvidenceUtils.takeScreenshot(response, Hooks.getScenarioName());
	}
}
