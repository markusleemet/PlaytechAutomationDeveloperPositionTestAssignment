package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestShowingErrorMessage extends SeleniumTest {
    public TestShowingErrorMessage() {
        super(
                2,
                "Test if message is displayed next to every required field if user hasn't filled these and tries to submit form.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Press button 'Saada ära'", "Check that all required fields have following messages next to them: 'See on kohustuslik küsimus'"))),
                new FormEntity(null, "", "", "", "", ""),
                "All required field that are marked with * have following message next to them 'See on kohustuslik küsimus'."
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        WebElement submitButton = getSubmitButton();
        submitButton.click();


        // Get all question containers
        List<WebElement> allQuestionContainers = driver.findElementsByClassName("freebirdFormviewerViewNumberedItemContainer");

        // Actual result may change later on
        actualResult = "All required field that are marked with * have following message next to them 'See on kohustuslik küsimus'.";
        for (WebElement questionContainer : allQuestionContainers) {
            // Check if container has asterisk element
            List<WebElement> asterisks = questionContainer.findElements(By.className("freebirdFormviewerComponentsQuestionBaseRequiredAsterisk"));
            if (asterisks.size() == 1) {
                try {
                    questionContainer.findElement(By.xpath("(.//div[text()='See on kohustuslik küsimus'])"));
                } catch (NoSuchElementException noSuchElementException) {
                    actualResult = "Some of the required fields don't have error message 'See on kohustuslik küsimus' next to them.";
                    break;
                }
            }
        }
        endTestAndCreateLog();
    }
}
