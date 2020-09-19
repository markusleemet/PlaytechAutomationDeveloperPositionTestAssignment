package cs.ut.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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

    public Integer getFirstQuestionOptionValue() {
        return firstQuestionOptionValue;
    }

    public String getNameValue() {
        return nameValue;
    }

    public String getEmailValue() {
        return emailValue;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public String getPhoneValue() {
        return phoneValue;
    }

    public String getCommentsValue() {
        return commentsValue;
    }
}