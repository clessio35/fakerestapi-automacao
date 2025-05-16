package fakerest.pages;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import fakerest.utils.BasePage;
import fakerest.utils.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class fakeRestPage {

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
		BasePage.takeScreenshot(response, Hooks.getScenarioName());
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
		BasePage.takeScreenshot(response, Hooks.getScenarioName());
	}

}
