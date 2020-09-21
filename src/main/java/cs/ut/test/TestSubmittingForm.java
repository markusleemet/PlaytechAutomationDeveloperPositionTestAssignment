package cs.ut.test;

import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSubmittingForm extends SeleniumTest {
    public TestSubmittingForm() {
        super(
                4,
                "Test that message is displayed to user after form submission.",
                new TestStepsEntity(new ArrayList<>(Arrays.asList("Go to site under test", "Fill all fields with valid data", "Press button 'Saada ära'", "Check that user is redirected to next page and following message is displayed: 'Thanks for submitting your contact info!'"))),
                new FormEntity(3, "Markus Leemet", "markusleemet@gmail.com", "Tartu", "+37256296263", "No comment!"),
                "User is redirected to the next page and following message is displayed: 'Thanks for submitting your contact info!'."
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        fillAllFields();
        clickSubmitButton();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.and(
                    ExpectedConditions.urlContains("formResponse"),
                    ExpectedConditions.textToBePresentInElementLocated(By.className("freebirdFormviewerViewResponseConfirmationMessage"), "Thanks for submitting your contact info!")
            ));
            actualResult = "User is redirected to the next page and following message is displayed: 'Thanks for submitting your contact info!'.";
        } catch (TimeoutException timeoutException) {
            actualResult = "User was not redirected to the next page and for was not submitted.";
        } finally {
            endTestAndWriteResultToLog();
        }
    }
}
