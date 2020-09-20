package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDeselectingRadioButton extends SeleniumTest {

    public TestDeselectingRadioButton() {
        super(
                11,
                "Test that clicking on selected radio button deselcts it.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Select first question 3rd option", "Click again on first question 3rd option", "Check if first question 3rd option is selected or not"))),
                new FormEntity(3, "", "", "", "", ""),
                "No option is selected in the first question."
        );
    }

    @Override
    public void runTest() {
        setUpTest();

        selectFirstQuestionOption();
        selectFirstQuestionOption();

        WebElement option3 = driver.findElementById("i11");
        Boolean option3isSelected = option3.getAttribute("aria-checked").equals(true);

        if (option3isSelected) {
            actualResult = "3rd option is selected in the first question.";
        }else{
            actualResult = "No option is selected in the first question.";
        }

        endTestAndCreateLog();
    }
}
