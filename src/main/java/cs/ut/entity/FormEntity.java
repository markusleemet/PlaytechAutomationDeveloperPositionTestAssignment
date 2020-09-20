package cs.ut.entity;

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

    @Override
    public String toString() {
        return "\n" +
                "\tSelect 3rd option = " + firstQuestionOptionValue + "\n" +
                "\tName = " + nameValue + "\n" +
                "\tEmail = " + emailValue + "\n" +
                "\tAddress = " + addressValue + "\n" +
                "\tPhone = " + phoneValue + "\n" +
                "\tComments = " + commentsValue + "\n";
    }
}