package info.seleniumcucumber.steps;

import info.seleniumcucumber.pages.AbstractPage;
import info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.Then;

import java.io.IOException;

public class CommonSteps extends AbstractPage {
    // Navigation Steps

    // Step to navigate to specified URL
    @Then("^I navigate to \"([^\"]*)\"$")
    public void navigate_to(String link) {
        navigationObj.navigateTo(link);
    }

    // Step to navigate forward
    @Then("^I navigate forward")
    public void navigate_forward() {
        navigationObj.navigate("forward");
    }

    // Step to navigate backward
    @Then("^I navigate back")
    public void navigate_back() {
        navigationObj.navigate("back");
    }

    // steps to refresh page
    @Then("^I refresh page$")
    public void refresh_page() {
        getDriver().navigate().refresh();
    }

    // Switch between windows

    // Switch to new window
    @Then("^I switch to new window$")
    public void switch_to_new_window() {
        navigationObj.switchToNewWindow();
    }

    // Switch to old window
    @Then("^I switch to previous window$")
    public void switch_to_old_window() {
        navigationObj.switchToOldWindow();
    }

    // Switch to new window by window title
    @Then("^I switch to window having title \"(.*?)\"$")
    public void switch_to_window_by_title(String windowTitle) throws Exception {
        navigationObj.switchToWindowByTitle(windowTitle);
    }

    // Close new window
    @Then("^I close new window$")
    public void close_new_window() {
        navigationObj.closeNewWindow();
    }

    // Switch between frame

    // step to switch to main content
    @Then("^I switch to main content$")
    public void switch_to_default_content() {
        navigationObj.switchToDefaultContent();
    }

    // To interact with browser

    // step to resize browser
    @Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
    public void resize_browser(int width, int heigth) {
        navigationObj.resizeBrowser(width, heigth);
    }

    // step to maximize browser
    @Then("^I maximize browser window$")
    public void maximize_browser() {
        navigationObj.maximizeBrowser();
    }

    // zoom in/out page

    // steps to zoom in page
    @Then("^I zoom in page$")
    public void zoom_in() {
        navigationObj.zoomInOut("ADD");
    }

    // steps to zoom out page
    @Then("^I zoom out page$")
    public void zoom_out() {
        navigationObj.zoomInOut("SUBTRACT");
    }

    // reset webpage view use

    @Then("^I reset page view$")
    public void reset_page_zoom() {
        navigationObj.zoomInOut("reset");
    }

    // scroll webpage

    @Then("^I scroll to (top|end) of page$")
    public void scroll_page(String to) throws Exception {
        navigationObj.scrollPage(to);
    }

    // Assertion steps

    /**
     * page title checking
     *
     * @param present :
     * @param title   :
     */
    @Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
    public void check_title(String present, String title) throws TestCaseFailed {
        // System.out.println("Present :" + present.isEmpty());
        assertionObj.checkTitle(title, present.isEmpty());
    }

    // step to check element partial text
    @Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
    public void check_partial_text(String present, String partialTextTitle) throws TestCaseFailed {
        // System.out.println("Present :" + present.isEmpty());
        assertionObj.checkPartialTitle(partialTextTitle, present.isEmpty());
    }

    // step to assert javascript pop-up alert text
    @Then("^I should see alert text as \"(.*?)\"$")
    public void check_alert_text(String actualValue) throws TestCaseFailed {
        assertionObj.checkAlertText(actualValue);
    }

    // JavaScript handling steps

    // Step to handle java script
    @Then("^I accept alert$")
    public void handle_alert() {
        javascriptObj.handleAlert("accept");
    }

    // Steps to dismiss java script
    @Then("^I dismiss alert$")
    public void dismiss_alert() {
        javascriptObj.handleAlert("dismiss");
    }

    // Screen shot methods

    @Then("^I take screenshot$")
    public void take_screenshot() throws IOException {
        screenshotObj.takeScreenShot();
    }

}