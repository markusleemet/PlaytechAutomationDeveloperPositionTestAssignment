package cs.ut.test;

import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;

public class TestFillingOnlyRequiredFields extends SeleniumTest {

    public TestFillingOnlyRequiredFields() {
        super(
                10,
                "Test that filling only required fields is enough to submit form.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Fill name field", "Fill email field", "Fill address field", "Click button 'Saada ära'", "Check that user is redirected to next page and following message is displayed: 'Thanks for submitting your contact info!'"))),
                new FormEntity(null, "Markus Leemet", "markusleeemt@gmail.com", "Tartu", "", ""),
                "User is redirected to the next page and following message is displayed: 'Thanks for submitting your contact info!'."
        );
    }

    @Override
    public void runTest() {
        setUpTest();

        fillNameField();
        fillEmailField();
        fillAddressField();

        WebElement submitButton = getSubmitButton();
        submitButton.click();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.urlContains("formResponse"));
            actualResult = "User is redirected to the next page and following message is displayed: 'Thanks for submitting your contact info!'.";
        } catch (TimeoutException timeoutException) {
            actualResult = "Form is not submitted and user is not redirected to next page.";
        } finally {
            endTestAndWriteResultToLog();
        }
    }
}
