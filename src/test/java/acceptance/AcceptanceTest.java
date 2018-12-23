package acceptance;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Acceptance Tests
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/acceptance/features/calculator.feature"},
        format = {"pretty", "html:build/test-results/acceptanceTest/html"})
public class AcceptanceTest {
}
