package cs.ut.test;

import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCancelSelectionButton2 extends SeleniumTest {

    public TestCancelSelectionButton2() {
        super(
                12,
                "Test that selecting option in the first question reveals 'Tühista valik' button.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Select first question 3rd option", "Check if 'Tühista valik' button is visible or not"))),
                new FormEntity(3, "", "", "", "", ""),
                "Button 'Tühista valik' is visible."
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        selectFirstQuestionOption();
        Boolean cancelButtonIsVisible = getCancelSelectionButton().isDisplayed();

        if (cancelButtonIsVisible) {
            actualResult = "Button 'Tühista valik' is visible.";
        }else{
            actualResult =  "Button 'Tühista valik' is not visible.";
        }

        endTestAndWriteResultToLog();
    }
}
