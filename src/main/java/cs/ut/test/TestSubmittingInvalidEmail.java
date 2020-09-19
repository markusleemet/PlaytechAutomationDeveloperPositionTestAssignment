package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSubmittingInvalidEmail extends SeleniumTest {
    public TestSubmittingInvalidEmail() {
        super(
                3,
                "Test submitting invalid email address in email field.",
                "1-> Go to site under test 2-> Fill email field 3-> Press button 'Saada ära' 3-> Check that following message is displayed next to the email field: 'Please enter a valid email address'",
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
