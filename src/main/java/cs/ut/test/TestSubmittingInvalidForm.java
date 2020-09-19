package cs.ut.test;


import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSubmittingInvalidForm extends SeleniumTest {
    public TestSubmittingInvalidForm() {
        super(
                1,
                "Test if form can be submitted when one or more required fields is empty.",
                "Go to site under test 2-> Press button 'Saada ära' 3-> Check if form is submited or not",
                new FormEntity(null, "", "", "", "", ""),
                "Form is not submitted and user is not redirected to next page."
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        WebElement submitButton = getSubmitButton();
        submitButton.click();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.urlContains("formResponse"));
            actualResult = "Form was submitted and user was redirected to the next page.";
        } catch (TimeoutException timeoutException) {
            actualResult = "Form is not submitted and user is not redirected to next page.";
        } finally {
            endTestAndCreateLog();
        }
    }
}
