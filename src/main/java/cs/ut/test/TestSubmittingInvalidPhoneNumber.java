package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSubmittingInvalidPhoneNumber extends SeleniumTest {

    public TestSubmittingInvalidPhoneNumber() {
        super(
                5,
                "Test submitting invalid phone number.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Fill all fields", "Press button 'Saada ära'", "Check that following message is displayed next to the phone field: 'Please enter a valid phone number'"))),
                new FormEntity(3, "Markus Leemet", "markusleemet@gmail.com", "Tartu", "This is not valid phone number 123", "No comment!"),
                "Following message is displayed next to the phone field: 'Please enter a valid phone number'"
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        fillPhoneField();
        clickSubmitButton();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.and(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElementByXPath("//form[@id='mG61Hd']/div[2]/div[1]/div[2]/div[5]"), By.className("freebirdFormviewerViewNumberedItemContainer")),
                    ExpectedConditions.textToBePresentInElementLocated(By.className("freebirdFormviewerViewNumberedItemContainer"), "Please enter a valid phone number")
            ));
            actualResult = "Following message is displayed next to the email field: 'Please enter a valid phone number'";
        } catch (TimeoutException timeoutException) {
            actualResult = "Message about invalid phone number is not displayed.";
        } finally {
            endTestAndCreateLog();
        }

    }
}
