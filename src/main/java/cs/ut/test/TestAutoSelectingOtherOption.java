package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestAutoSelectingOtherOption extends SeleniumTest {
    public TestAutoSelectingOtherOption() {
        super(
                9,
                "Test that inserting character/word into first question input field sets 'Muu' as selected option.",
                "1-> Go to site under test 2-> Click on first question input field 3-> insert 'select this option' 4-> Check that 'Muu' is set to selected option",
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

        endTestAndCreateLog();
    }
}
