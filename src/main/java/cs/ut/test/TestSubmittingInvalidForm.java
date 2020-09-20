package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSubmittingInvalidForm extends SeleniumTest {
    public TestSubmittingInvalidForm() {
        super(
                1,
                "Test if form can be submitted when one or more required fields is empty.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Press button 'Saada ära'", "Check if form is submited or not"))),
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
