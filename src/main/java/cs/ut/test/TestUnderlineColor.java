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

public class TestUnderlineColor extends SeleniumTest {

    public TestUnderlineColor() {
        super(
                8,
                "Test that unfocusing email field when invalid email is inserted email field will change its underline color to red that indicates it's filled incorrectly.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Fill all fields", "Select phone field", "Check that email field is underlined with red color"))),
                new FormEntity(null, "", "this-is-not-valid-email", "", "", ""),
                "Email field is underlined with red color."
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        fillEmailField();
        WebElement phoneField = getPhoneField();
        phoneField.click();
        WebElement emailContainer = getEmailContainer();

        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.presenceOfNestedElementLocatedBy(emailContainer, By.className("quantumWizTextinputPaperinputUnderline")));

            WebElement emailFieldUnderline = emailContainer.findElement(By.className("quantumWizTextinputPaperinputUnderline"));
            String underlineColor = emailFieldUnderline.getCssValue("background-color");


            if (underlineColor.equals("rgb(217, 48, 37)")) {
                actualResult = "Email field is underlined with red color.";
            } else {
                actualResult =  "Email field is not underlined with red color.";
            }
        } catch (TimeoutException timeoutException) {
            actualResult =  "Email field is not underlined with red color.";
        } finally {
            endTestAndCreateLog();
        }
    }
}
