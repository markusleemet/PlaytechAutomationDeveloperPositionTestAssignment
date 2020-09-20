package cs.ut;

import cs.ut.test.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    private static void runAllTests() throws IOException {
        // Clear output file
        new PrintWriter("testResult/seleniumTestResult").close();

        new TestSubmittingInvalidForm().runTest();
        new TestShowingErrorMessage().runTest();
        new TestSubmittingInvalidEmail().runTest();
        new TestSubmittingForm().runTest();
        new TestSubmittingInvalidPhoneNumber().runTest();
        new TestRequiredFields().runTest();
        new TestCancelSelectionButton().runTest();
        new TestUnderlineColor().runTest();
        new TestAutoSelectingOtherOption().runTest();
        new TestFillingOnlyRequiredFields().runTest();
        new TestDeselectingRadioButton().runTest();
        new TestCancelSelectionButton2().runTest();
    }

    public static void main(String[] args) throws IOException {
        // Setup RemoteWebDrivers to run Selenium tests
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();

        runAllTests();
    }
}