package cs.ut.test;

import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCancelSelectionButton extends SeleniumTest {

    public TestCancelSelectionButton() {
        super(
                7,
                "Test that clicking on 'Tühista valik' unselects selected option in first question.",
                new TestStepsEntity(new ArrayList<>(Arrays.asList("Go to site under test", "Select third option in first question", "Press button 'Tühista valik'", "Check that none of the options are selected in first question"))),
                new FormEntity(3, "", "", "", "", ""),
                "None of the options are selected in the first question."
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        selectFirstQuestionOption();
        WebElement cancelSelectionButton = getCancelSelectionButton();
        cancelSelectionButton.click();
        WebElement firstQuestionContainer = getFirstQuestionContainer();

        // Actual result may change later on
        actualResult =  "None of the options are selected in the first question.";

        // Get all radio buttons
        List<WebElement> radioButtons = firstQuestionContainer.findElements(By.className("appsMaterialWizToggleRadiogroupEl"));

        for (WebElement radioButton : radioButtons) {
            if (radioButton.getAttribute("aria-checked").equals("true")) {
                actualResult =  "One option is selected in the first question.";
            }
        }
        endTestAndWriteResultToLog();
    }
}
