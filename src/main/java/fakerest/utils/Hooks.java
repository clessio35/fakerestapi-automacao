package fakerest.utils;

import io.cucumber.java.Before;

import io.cucumber.java.Scenario;

public class Hooks {

	private static String scenarioName;

    @Before
    public void before(Scenario scenario) {
        scenarioName = scenario.getName();
        System.out.println(">>>>> Hook executado - cenário: " + scenarioName);
    }

    public static String getScenarioName() {
        return scenarioName;
    }
}
