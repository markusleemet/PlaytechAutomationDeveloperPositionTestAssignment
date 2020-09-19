package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;

public class TestCancelSelectionButton2 extends SeleniumTest {

    public TestCancelSelectionButton2() {
        super(
                12,
                "Test that selecting option in the first question reveals 'Tühista valik' button.",
                "1-> Go to site under test 2-> Select first question 3rd option 3-> Check if 'Tühista valik' button is visible or not",
                new FormEntity(3, "", "", "", "", ""),
                "Button 'Tühista valik' is visible."
        );
    }

    @Override
    public void runTest() {
        setUpTest();
        selectFirstQuestionOption();
        Boolean cancelButtonIsVisible = getCancelSelectionButton().isDisplayed();
        driver.quit();

        if (cancelButtonIsVisible) {
            actualResult = "Button 'Tühista valik' is visible.";
        }else{
            actualResult =  "Button 'Tühista valik' is not visible.";
        }

        endTestAndCreateLog();
    }
}
