package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSubmittingInvalidEmail extends SeleniumTest {
    public TestSubmittingInvalidEmail() {
        super(
                3,
                "Test submitting invalid email address in email field.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Fill email field", "Press button 'Saada ära'", "Check that following message is displayed next to the email field: 'Please enter a valid email address'"))),
                new FormEntity(null, "", "mail-without-at-sign", "", "", ""),
                "Following message is displayed next to the email field: 'Please enter a valid email address'."
        );
    }


    @Override
    public void runTest() {
        setUpTest();
        WebElement submitButton = getSubmitButton();
        WebElement emailContainer = getEmailContainer();
        fillEmailField();
        submitButton.click();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.and(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(emailContainer, By.id("i29")),
                    ExpectedConditions.textToBePresentInElementLocated(By.id("i29"), "Please enter a valid email address")
            ));
            actualResult = "Following message is displayed next to the email field: 'Please enter a valid email address'.";
        } catch (TimeoutException timeoutException) {
            actualResult = "Message about invalid email is not displayed next to email input field.";
        } finally {
            endTestAndCreateLog();
        }
    }
}
