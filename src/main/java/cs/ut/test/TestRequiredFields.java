package cs.ut.test;

import cs.ut.SeleniumTest;
import cs.ut.entity.FormEntity;
import cs.ut.entity.TestStepsEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRequiredFields extends SeleniumTest {

    public TestRequiredFields() {
        super(
                6,
                "Test that all required fields have visual element next to them that shows this field is required for form submission.",
                new TestStepsEntity(new ArrayList<String>(Arrays.asList("Go to site under test", "Check that all fields that have attribute 'required' also have visual element next to it that indicates that this field is required for form submission\""))),
                new FormEntity(null, "", "", "", "", ""),
                "All fields that have attribute 'required' also have visual element next to them that show these are required."
        );
    }

    @Override
    public void runTest() {
        setUpTest();

        // Actual result may change later on
        actualResult =  "All fields that have attribute 'required' also have visual element next to them that show these are required.";

        // Get all question containers
        List<WebElement> allQuestionContainers = driver.findElementsByClassName("freebirdFormviewerViewNumberedItemContainer");

        for (WebElement questionContainer : allQuestionContainers) {
            String questionName = questionContainer.findElement(By.className("freebirdFormviewerComponentsQuestionBaseTitle")).getText();

            // Get input field inside container
            WebElement inputField = questionContainer.findElement(By.xpath(
                    ".//input[@class='quantumWizTextinputPaperinputInput exportInput'] | " +
                            ".//textarea[@class='quantumWizTextinputPapertextareaInput exportTextarea'] |" +
                            ".//div[@class='appsMaterialWizToggleRadiogroupGroupContainer exportGroupContainer freebirdFormviewerViewItemsRadiogroupRadioGroup']"));

            // Check if input has attribute required set to true
            if (inputField.getAttribute("required") != null) {
                // Check if container with required input has asterisk element
                List<WebElement> asterisks = questionContainer.findElements(By.className("freebirdFormviewerComponentsQuestionBaseRequiredAsterisk"));
                if (asterisks.size() == 0) {
                    driver.quit();
                    actualResult = "Some of the fields that have attribute required doesn't have visual asterisk element next to them";
                }
            }
        }

        endTestAndCreateLog();
    }
}
