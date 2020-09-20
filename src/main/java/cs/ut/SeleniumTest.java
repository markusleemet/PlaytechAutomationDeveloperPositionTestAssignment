package cs.ut;

import cs.ut.entity.FormEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class SeleniumTest {
    protected final String systemUnderTest = "https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform?fbzx=1323604193658677770";

    protected int testCaseId;
    protected String testScenario;
    protected String testSteps;
    protected FormEntity testData;
    protected String expectedResult;
    protected String actualResult;
    protected RemoteWebDriver driver = new ChromeDriver();


    public SeleniumTest(int testCaseId, String testScenario, String testSteps, FormEntity testData, String expectedResult) {
        this.testCaseId = testCaseId;
        this.testScenario = testScenario;
        this.testSteps = testSteps;
        this.testData = testData;
        this.expectedResult = expectedResult;
        actualResult = "";
    }

    protected RemoteWebDriver setUpTest() {
        driver.get(systemUnderTest);
        return driver;
    }

    public abstract void runTest();


    protected void endTestAndCreateLog() {
        String testResult = expectedResult.equals(actualResult) ? "PASS" : "FAIL";

        try (PrintWriter printWriter = new PrintWriter(new FileWriter("testResult/seleniumTestResult", true))){
            printWriter.append(String.format("TEST CASE: %d\n", testCaseId));
            printWriter.append(String.format("Test scenario: %s\n", testScenario));
            printWriter.append(String.format("Test steps: %s\n", testSteps));
            printWriter.append(String.format("Test data: %s\n", testData));
            printWriter.append(String.format("Expected result: %s\n", expectedResult));
            printWriter.append(String.format("Actual result: %s\n", actualResult));
            printWriter.append(String.format("Pass/fail: %s\n\n", testResult));
        } catch (IOException ioException) {
            System.err.println("Test result was not printed to output file");
        }

        driver.quit();
    }

    public WebElement getSubmitButton() {
        return driver.findElementByXPath("(//div[contains(@class,'appsMaterialWizButtonEl appsMaterialWizButtonPaperbuttonEl')])[2]");
    }

    public WebElement getCancelSelectionButton() {
        return driver.findElementByXPath("(//span[contains(@class,'appsMaterialWizButtonPaperbuttonLabel quantumWizButtonPaperbuttonLabel')])[1]");
    }


    public WebElement getFirstQuestionContainer() {
        return driver.findElementByXPath("(//div[@class='freebirdFormviewerViewNumberedItemContainer'])[1]");
    }

    public void selectFirstQuestionOption() {
        switch (testData.getFirstQuestionOptionValue()) {
            case 1:
                WebElement option1 = driver.findElementByXPath("(//div[@class='appsMaterialWizToggleRadiogroupOffRadio exportOuterCircle'])[1]");
                new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(option1));
                option1.click();
                break;
            case 2:
                WebElement option2 = driver.findElementByXPath("(//div[@class='appsMaterialWizToggleRadiogroupOffRadio exportOuterCircle'])[2]");
                new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(option2));
                option2.click();
                break;
            case 3:
                WebElement option3 = driver.findElementByXPath("(//div[@class='appsMaterialWizToggleRadiogroupOffRadio exportOuterCircle'])[3]");
                new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(option3));
                option3.click();
                break;
            case 4:
                WebElement option4 = driver.findElementByXPath("//div[@id='i14']/div[3]/div[1]");
                new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(option4));
                option4.click();
                break;
            case 5:
                WebElement option5 = driver.findElementByXPath("//div[@id='i17']/div[3]/div[1]");
                new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(option5));
                option5.click();
                break;
        }
    }

    public WebElement getEmailContainer() {
        return driver.findElementByXPath("(//div[@class='freebirdFormviewerViewNumberedItemContainer'])[3]");
    }

    public WebElement getEmailField() {
        return driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[2]"));
    }

    public void fillEmailField(){
        WebElement emailField = getEmailField();
        emailField.click();
        emailField.sendKeys(testData.getEmailValue());
    }


    public void fillNameField(){
        WebElement emailField = driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[1]"));
        emailField.click();
        emailField.sendKeys(testData.getNameValue());
    }

    public void fillAddressField(){
        WebElement emailField = driver.findElement(By.xpath("(//textarea[@class='quantumWizTextinputPapertextareaInput exportTextarea'])[1]"));
        emailField.click();
        emailField.sendKeys(testData.getAddressValue());
    }


    public WebElement getPhoneField() {
        return driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[3]"));
    }

    public void fillPhoneField(){
        WebElement emailField = driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[3]"));
        emailField.click();
        emailField.sendKeys(testData.getPhoneValue());
    }

    public void fillCommentsField(){
        WebElement emailField = driver.findElement(By.xpath("(//textarea[@class='quantumWizTextinputPapertextareaInput exportTextarea'])[2]"));
        emailField.click();
        emailField.sendKeys(testData.getCommentsValue());
    }

    public void fillAllFields(){
        selectFirstQuestionOption();
        fillNameField();
        fillEmailField();
        fillAddressField();
        fillPhoneField();
        fillCommentsField();
    }

    public void clickSubmitButton() {
        WebElement submitButton = driver.findElementByXPath("(//div[contains(@class,'appsMaterialWizButtonEl appsMaterialWizButtonPaperbuttonEl')])[2]");
        submitButton.click();
    }
}
