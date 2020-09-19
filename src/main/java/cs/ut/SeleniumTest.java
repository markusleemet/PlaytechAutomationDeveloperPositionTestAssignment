package cs.ut;

import cs.ut.entity.FormEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.List;

public class SeleniumTest {
    private static final String webDriverPath = "c:\\selenium\\geckodriver.exe";
    private static final String systemUnderTest = "https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform?fbzx=1323604193658677770";


    /**
     * Create FirefoxDriver and get webpage that is being tested with it
     *
     * @return Firefox driver instance
     */
    private static FirefoxDriver setUpTest() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get(systemUnderTest);
        return driver;
    }

    private static String testCase1() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(null, "", "", "", "", "");
        WebElement submitButton = formEntity.getSubmitButton(driver);
        submitButton.click();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.urlContains("formResponse"));
            return "Form was submitted and user was redirected to the next page.";
        } catch (TimeoutException timeoutException) {
            return "Form is not submitted and user is not redirected to next page.";
        } finally {
            driver.quit();
        }
    }

    private static String testCase2() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(null, "", "", "", "", "");
        WebElement submitButton = formEntity.getSubmitButton(driver);
        submitButton.click();


        // Get all question containers
        List<WebElement> allQuestionContainers = driver.findElementsByClassName("freebirdFormviewerViewNumberedItemContainer");

        for (WebElement questionContainer : allQuestionContainers) {
            // Check if container has asterisk element
            List<WebElement> asterisks = questionContainer.findElements(By.className("freebirdFormviewerComponentsQuestionBaseRequiredAsterisk"));
            if (asterisks.size() == 1) {
                try {
                    questionContainer.findElement(By.xpath("(.//div[text()='See on kohustuslik küsimus'])"));
                } catch (NoSuchElementException noSuchElementException) {
                    driver.quit();
                    return "Some of the required fields don't have error message 'See on kohustuslik küsimus' next to them.";
                }
            }
        }
        driver.quit();
        return "All required field that are marked with * have following message next to them 'See on kohustuslik küsimus'.";
    }

    private static String testCase3() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(null, null, "mail-without-at-sign", null, null, null);
        WebElement submitButton = formEntity.getSubmitButton(driver);
        WebElement emailContainer = formEntity.getEmailContainer(driver);
        formEntity.fillEmailField(driver);
        submitButton.click();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.and(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(emailContainer, By.id("i29")),
                    ExpectedConditions.textToBePresentInElementLocated(By.id("i29"), "Please enter a valid email address")
            ));
            return "Following message is displayed next to the email field: 'Please enter a valid email address'.";
        } catch (TimeoutException timeoutException) {
            return "Message about invalid email is not displayed next to email input field.";
        } finally {
            driver.quit();
        }
    }

    private static String testCase4() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get(systemUnderTest);
        FormEntity formEntity = new FormEntity(2, "Markus Leemet", "markusleemet@gmail.com", "Tartu", "56196263", "No comment!");
        formEntity.fillAllFields(driver);
        formEntity.clickSubmitButton(driver);

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.and(
                    ExpectedConditions.urlContains("formResponse"),
                    ExpectedConditions.textToBePresentInElementLocated(By.className("freebirdFormviewerViewResponseConfirmationMessage"), "Thanks for submitting your contact info!")
            ));
            return "User is redirected to the next page and following message is displayed: 'Thanks for submitting your contact info!'.";
        } catch (TimeoutException timeoutException) {
            return "User was not redirected to the next page and for was not submitted.";
        } finally {
            driver.quit();
        }
    }

    private static String testCase5() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(3, "Markus Leemet", "markusleemet@gmail.com", "Tartu", "This is not valid phone number 123", "No comment!");
        formEntity.fillPhoneField(driver);
        formEntity.clickSubmitButton(driver);

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.and(
                    ExpectedConditions.presenceOfNestedElementLocatedBy(driver.findElementByXPath("//form[@id='mG61Hd']/div[2]/div[1]/div[2]/div[5]"), By.className("freebirdFormviewerViewNumberedItemContainer")),
                    ExpectedConditions.textToBePresentInElementLocated(By.className("freebirdFormviewerViewNumberedItemContainer"), "Please enter a valid phone number")
            ));
            return "Following message is displayed next to the email field: 'Please enter a valid phone number'";
        } catch (TimeoutException timeoutException) {
            return "Message about invalid phone number is not displayed.";
        } finally {
            driver.quit();
        }
    }

    private static String testCase6() {
        FirefoxDriver driver = setUpTest();

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
                    return "Some of the fields that have attribute required doesn't have visual asterisk element next to them";
                }
            }
        }

        driver.quit();
        return "All fields that have attribute 'required' also have visual element next to them that show these are required.";
    }

    private static String testCase7() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(3, "", "", "", "", "");
        formEntity.selectFirstQuestionOption(driver);
        WebElement cancelSelectionButton = formEntity.getCancelSelectionButton(driver);
        cancelSelectionButton.click();
        WebElement firstQuestionContainer = formEntity.getFirstQuestionContainer(driver);

        // Get all radio buttons
        List<WebElement> radioButtons = firstQuestionContainer.findElements(By.className("appsMaterialWizToggleRadiogroupEl"));

        for (WebElement radioButton : radioButtons) {
            if (radioButton.getAttribute("aria-checked").equals("true")) {
                return "One option is selected in the first question.";
            }
        }

        driver.quit();
        return "None of the options are selected in the first question.";
    }

    private static String testCase8() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(null, "", "this-is-not-valid-email", "", "", "");
        formEntity.fillEmailField(driver);
        WebElement phoneField = formEntity.getPhoneField(driver);
        phoneField.click();
        WebElement emailContainer = formEntity.getEmailContainer(driver);

        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.presenceOfNestedElementLocatedBy(emailContainer, By.className("quantumWizTextinputPaperinputUnderline")));

            WebElement emailFieldUnderline = emailContainer.findElement(By.className("quantumWizTextinputPaperinputUnderline"));
            String underlineColor = emailFieldUnderline.getCssValue("background-color");


            if (underlineColor.equals("rgb(217, 48, 37)")) {
                return "Email field is underlined with red color.";
            } else {
                return "Email field is not underlined with red color.";
            }
        } catch (TimeoutException timeoutException) {
            return "Email field is not underlined with red color.";
        } finally {
            driver.quit();
        }
    }

    private static String testCase9() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(null, "", "", "", "", "");

        WebElement firstQuestionInputField = driver.findElementByXPath("//input[@class='quantumWizTextinputSimpleinputInput exportInput']");
        firstQuestionInputField.click();
        firstQuestionInputField.sendKeys("select this option");

        WebElement firstQuestionContainer = formEntity.getFirstQuestionContainer(driver);

        // Get last radio button - 'Muu'
        WebElement firstQuestionLastOption = firstQuestionContainer.findElements(By.className("appsMaterialWizToggleRadiogroupEl")).get(5);


        if (firstQuestionLastOption.getAttribute("aria-checked").equals("true")) {
            driver.quit();
            return "First question has 'Muu' option selected.";
        }else{
            driver.quit();
            return "First question has not 'Muu' option selected.";
        }
    }

    private static String testCase10() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(null, "Markus Leemet", "markusleeemt@gmail.com", "Tartu", "", "");

        formEntity.fillNameField(driver);
        formEntity.fillEmailField(driver);
        formEntity.fillAddressField(driver);

        WebElement submitButton = formEntity.getSubmitButton(driver);
        submitButton.click();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.urlContains("formResponse"));
            return "User is redirected to the next page and following message is displayed: 'Thanks for submitting your contact info!'.";
        } catch (TimeoutException timeoutException) {
            return "Form is not submitted and user is not redirected to next page.";
        } finally {
            driver.quit();
        }
    }

    private static String testCase11() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(3, "", "", "", "", "");

        formEntity.selectFirstQuestionOption(driver);
        formEntity.selectFirstQuestionOption(driver);

        WebElement option3 = driver.findElementById("i11");
        Boolean option3isSelected = option3.getAttribute("aria-checked").equals(true);

        driver.quit();
        if (option3isSelected) {
            return "3rd option is selected in the first question.";
        }else{
            return "No option is selected in the first question.";
        }
    }

    private static String testCase12() {
        FirefoxDriver driver = setUpTest();
        FormEntity formEntity = new FormEntity(3, "", "", "", "", "");
        formEntity.selectFirstQuestionOption(driver);
        Boolean cancelButtonIsVisible = formEntity.getCancelSelectionButton(driver).isDisplayed();
        driver.quit();

        if (cancelButtonIsVisible) {
            return "Button 'Tühista valik' is visible.";
        }else{
            return "Button 'Tühista valik' is not visible.";
        }
    }

    private static void writeIntoFile(PrintWriter printWriter, BufferedReader bufferedReader, String result) throws IOException{
        for (int i = 0; i < 4; i++) {
            String line = bufferedReader.readLine();
            printWriter.print(line);
            printWriter.print("\n");
        }
        String expectedResult = bufferedReader.readLine().split("Expected result: ")[1];
        printWriter.printf("Expected result: %s", expectedResult);
        printWriter.print("\n");

        printWriter.printf("Actual result: %s", result);
        printWriter.print("\n");

        if (expectedResult.equals(result)) {
            printWriter.print("Pass/fail: PASS");
        }else{
            printWriter.print("Pass/fail: FAIL");
        }

        bufferedReader.readLine();
        printWriter.print("\n\n");
    }

    public static void main(String[] args) throws IOException {
        // Set webdriver path to make Selenium use possible
        System.setProperty("webdriver.gecko.driver", webDriverPath);


        // Create printWriter to write tests results into file
        File file = new File("src/main/java/cs/ut/output/seleniumTestResult");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        // Create bufferedReader to read in test cs.ut.output structure
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/cs/ut/output/outputStructure"));

        writeIntoFile(printWriter, bufferedReader, testCase1());
        writeIntoFile(printWriter, bufferedReader, testCase2());
        writeIntoFile(printWriter, bufferedReader, testCase3());
        writeIntoFile(printWriter, bufferedReader, testCase4());
        writeIntoFile(printWriter, bufferedReader, testCase5());
        writeIntoFile(printWriter, bufferedReader, testCase6());
        writeIntoFile(printWriter, bufferedReader, testCase7());
        writeIntoFile(printWriter, bufferedReader, testCase8());
        writeIntoFile(printWriter, bufferedReader, testCase9());
        writeIntoFile(printWriter, bufferedReader, testCase10());
        writeIntoFile(printWriter, bufferedReader, testCase11());
        writeIntoFile(printWriter, bufferedReader, testCase12());

        bufferedReader.close();
        printWriter.close();
    }
}