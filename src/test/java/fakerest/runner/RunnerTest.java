package fakerest.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"fakerest.steps", "fakerest.utils"},
    tags = "@create-invalid-book", 
    publish = true,
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class RunnerTest {

}

