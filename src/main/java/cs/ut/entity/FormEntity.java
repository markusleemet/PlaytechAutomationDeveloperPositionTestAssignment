package cs.ut.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormEntity {
    Integer firstQuestionOptionValue;
    String nameValue;
    String emailValue;
    String addressValue;
    String phoneValue;
    String commentsValue;

    public FormEntity(Integer firstQuestionOptionValue, String nameValue, String emailValue, String addressValue, String phoneValue, String commentsValue) {
        this.firstQuestionOptionValue = firstQuestionOptionValue;
        this.nameValue = nameValue;
        this.emailValue = emailValue;
        this.addressValue = addressValue;
        this.phoneValue = phoneValue;
        this.commentsValue = commentsValue;
    }

    public WebElement getSubmitButton(FirefoxDriver driver) {
        return driver.findElementByXPath("(//div[contains(@class,'appsMaterialWizButtonEl appsMaterialWizButtonPaperbuttonEl')])[2]");
    }

    public WebElement getCancelSelectionButton(FirefoxDriver driver) {
        return driver.findElementByXPath("(//span[contains(@class,'appsMaterialWizButtonPaperbuttonLabel quantumWizButtonPaperbuttonLabel')])[1]");
    }


    public WebElement getFirstQuestionContainer(FirefoxDriver driver) {
        return driver.findElementByXPath("(//div[@class='freebirdFormviewerViewNumberedItemContainer'])[1]");
    }

    public void selectFirstQuestionOption(FirefoxDriver driver) {
        switch (firstQuestionOptionValue) {
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

    public WebElement getEmailContainer(FirefoxDriver driver) {
        return driver.findElementByXPath("(//div[@class='freebirdFormviewerViewNumberedItemContainer'])[3]");
    }

    public WebElement getEmailField(FirefoxDriver driver) {
        return driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[2]"));
    }

    public void fillEmailField(FirefoxDriver driver){
        WebElement emailField = getEmailField(driver);
        emailField.click();
        emailField.sendKeys(emailValue);
    }


    public void fillNameField(FirefoxDriver driver){
        WebElement emailField = driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[1]"));
        emailField.click();
        emailField.sendKeys(nameValue);
    }

    public void fillAddressField(FirefoxDriver driver){
        WebElement emailField = driver.findElement(By.xpath("(//textarea[@class='quantumWizTextinputPapertextareaInput exportTextarea'])[1]"));
        emailField.click();
        emailField.sendKeys(addressValue);
    }


    public WebElement getPhoneField(FirefoxDriver driver) {
        return driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[3]"));
    }

    public void fillPhoneField(FirefoxDriver driver){
        WebElement emailField = driver.findElement(By.xpath("(//input[@class='quantumWizTextinputPaperinputInput exportInput'])[3]"));
        emailField.click();
        emailField.sendKeys(phoneValue);
    }

    public void fillCommentsField(FirefoxDriver driver){
        WebElement emailField = driver.findElement(By.xpath("(//textarea[@class='quantumWizTextinputPapertextareaInput exportTextarea'])[2]"));
        emailField.click();
        emailField.sendKeys(commentsValue);
    }

    public void fillAllFields(FirefoxDriver driver){
        selectFirstQuestionOption(driver);
        fillNameField(driver);
        fillEmailField(driver);
        fillAddressField(driver);
        fillPhoneField(driver);
        fillCommentsField(driver);
    }

    public void clickSubmitButton(FirefoxDriver driver) {
        WebElement submitButton = driver.findElementByXPath("(//div[contains(@class,'appsMaterialWizButtonEl appsMaterialWizButtonPaperbuttonEl')])[2]");
        submitButton.click();
    }
}