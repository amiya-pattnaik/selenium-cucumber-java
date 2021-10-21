package info.seleniumcucumber.utils;

import info.seleniumcucumber.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class NavigateMethodsByElement extends AbstractPage implements BaseTest {
    private String old_win = null;
    private String lastWinHandle;

    /**
     * Method to open link
     *
     * @param url : String : URL for navigation
     */
    public void navigateTo(String url) {
        getDriver().get(url);
    }

    /**
     * Method to navigate back & forward
     *
     * @param direction : String : Navigate to forward or backward
     */
    public void navigate(String direction) {
        if (direction.equals("back"))
            getDriver().navigate().back();
        else
            getDriver().navigate().forward();
    }

    /**
     * Method to return key by OS wise
     *
     * @return Keys : Return control or command key as per OS
     */
    public Keys getKey() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return Keys.CONTROL;
        else if (os.contains("nux") || os.contains("nix"))
            return Keys.CONTROL;
        else if (os.contains("mac"))
            return Keys.COMMAND;
        else
            return null;
    }

    /**
     * Method to zoom in/out page
     *
     * @param inOut : String : Zoom in or out
     */
    public void zoomInOut(String inOut) {
        WebElement Sel = getDriver().findElement(By.tagName("html"));
        switch (inOut) {
            case "ADD":
                Sel.sendKeys(Keys.chord(getKey(), Keys.ADD));
                break;
            case "SUBTRACT":
                Sel.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
                break;
            case "reset":
                Sel.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
                break;
        }
    }

    /**
     * Method to zoom in/out web page until web element displays
     *
     * @param element : WebElement : WebElement type
     * @param inOut      : String : Zoom in or out
     */
    public void zoomInOutTillElementDisplay(WebElement element, String inOut) throws NoSuchFieldException {
        Actions action = new Actions(getDriver());
        getDriverWait().waitForElementToLoad(element);
        while (true) {
            if (element.isDisplayed())
                break;
            else
                action.keyDown(getKey()).sendKeys(inOut).keyUp(getKey()).perform();
        }
    }

    /**
     * Method to resize browser
     *
     * @param width  : int : Width for browser resize
     * @param height : int : Height for browser resize
     */
    public void resizeBrowser(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    /**
     * Method to maximize browser
     */
    public void maximizeBrowser() {
        getDriver().manage().window().maximize();
    }

    /**
     * Method to hover on element
     *
     * @param element : WebElement : WebElement type
     */
    public void hoverOverElement(WebElement element) throws NoSuchFieldException {
        Actions action = new Actions(getDriver());
        getDriverWait().waitForElementToLoad(element);
        action.moveToElement(element).perform();
    }

    /**
     * Method to scroll page to particular element
     *
     * @param element : WebElement : WebElement type
     */
    public void scrollToElement(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Method to scroll page to top or end
     *
     * @param to : String : Scroll page to Top or End
     */
    public void scrollPage(String to) throws Exception {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        if (to.equals("end"))
            executor.executeScript(
                    "window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
        else if (to.equals("top"))
            executor.executeScript(
                    "window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);");
        else
            throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
    }

    /**
     * Method to switch to new window
     */
    public void switchToNewWindow() {
        old_win = getDriver().getWindowHandle();
        for (String winHandle : getDriver().getWindowHandles())
            lastWinHandle = winHandle;
        getDriver().switchTo().window(lastWinHandle);
    }

    /**
     * Method to switch to old window
     */
    public void switchToOldWindow() {
        getDriver().switchTo().window(old_win);
    }

    /**
     * Method to switch to window by title
     *
     * @param windowTitle : String : Name of window title to switch
     */
    public void switchToWindowByTitle(String windowTitle) throws Exception {
        // System.out.println("++"+windowTitle+"++");
        old_win = getDriver().getWindowHandle();
        boolean winFound = false;
        for (String winHandle : getDriver().getWindowHandles()) {
            String str = getDriver().switchTo().window(winHandle).getTitle();
            // System.out.println("**"+str+"**");
            if (str.equals(windowTitle)) {
                winFound = true;
                break;
            }
        }
        if (!winFound)
            throw new Exception("Window having title " + windowTitle + " not found");
    }

    /**
     * Method to close new window
     */
    public void closeNewWindow() {
        getDriver().close();
    }

    /**
     * Method to switch frame using web element frame
     *
     * @param element : WebElement : WebElement type
     */
    public void switchFrame(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        getDriver().switchTo().frame(element);
    }

    /**
     * method to switch to default content
     */
    public void switchParentFrame() {
        getDriver().switchTo().parentFrame();
    }

    /**
     * method to switch to default content
     */
    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }
}
