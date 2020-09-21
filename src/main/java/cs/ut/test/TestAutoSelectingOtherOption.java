package cs.ut.test;

import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;

public class TestAutoSelectingOtherOption extends SeleniumTest {
    public TestAutoSelectingOtherOption() {
        super(
                9,
                "Test that inserting character/word into first question input field sets 'Muu' as selected option.",
                new TestStepsEntity(new ArrayList<>(Arrays.asList("Go to site under test", "Click on first question input field", "insert 'select this option'", "Check that 'Muu' is set to selected option"))),
                new FormEntity(null, "", "", "", "", ""),
                "First question has 'Muu' option selected."
        );
    }

    @Override
    public void runTest() {
        setUpTest();

        WebElement firstQuestionInputField = driver.findElementByXPath("//input[@class='quantumWizTextinputSimpleinputInput exportInput']");
        firstQuestionInputField.click();
        firstQuestionInputField.sendKeys("select this option");

        WebElement firstQuestionContainer = getFirstQuestionContainer();

        // Get last radio button - 'Muu'
        WebElement firstQuestionLastOption = firstQuestionContainer.findElements(By.className("appsMaterialWizToggleRadiogroupEl")).get(5);


        if (firstQuestionLastOption.getAttribute("aria-checked").equals("true")) {
            actualResult = "First question has 'Muu' option selected.";
        }else{
            actualResult = "First question has not 'Muu' option selected.";
        }

        endTestAndWriteResultToLog();
    }
}
