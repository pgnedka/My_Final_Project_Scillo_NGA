package org.nedka.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;

public class PostPage extends BasePage {
    final String POST_PAGE_SUFFIX = "/posts/create";

    @FindBy(xpath = "//h3")
    private WebElement newPostPageHeader;

    @FindBy(css = "img.image-preview")
    private WebElement image;

    @FindBy(css = "input.input-lg")
    private WebElement imageTextElement;

    @FindBy(css = ".file[type='file']")
    private WebElement uploadField;

    @FindBy(name = "caption")
    private WebElement captionElement;

    @FindBy(id = "create-post")
    private WebElement createPostButton;

    @FindBy(xpath = "//div[contains(@aria-label,\"Successful login!\")]")
    private WebElement newPostToastMessage;

    public PostPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void navigateToPostPage(){
        navigateTo(POST_PAGE_SUFFIX);
    }

    public String getImageName() {
        String imageName = imageTextElement.getAttribute("placeholder");
        log.info("CONFIRMATION # The image name is: " + imageName);
        return imageName;
    }

    public void uploadPicture(File file) {
        isElementPresented(uploadField);
        uploadField.sendKeys(file.getAbsolutePath());
        log.info("CONFIRMATION # The file was successfully uploaded.");
    }

    public void providePostCaption(String caption) {
        wait.until(ExpectedConditions.visibilityOf(captionElement));
        captionElement.sendKeys(caption);
        log.info("CONFIRMATION # The user has provided caption text: " + caption);
    }

    public void clickCreatePostButton() {
        wait.until(ExpectedConditions.visibilityOf(createPostButton));
        createPostButton.click();
        log.info("CONFIRMATION # The user has clicked on the submit post button.");
    }

    public String getNewPostPageHeaderText() {
        return getElementText(newPostPageHeader);
    }

    public String getNewPostToastSuccessfulMsg(){
        return getElementText(newPostToastMessage);
    }

    public boolean isNewPostToastSuccessfulMsgVisible() {
        boolean isNewPostToastSuccessfulMsgVisible = false;
        try {
            isNewPostToastSuccessfulMsgVisible = wait.until(ExpectedConditions.visibilityOf(newPostToastMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post! message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post Failed! message is not displayed!");
            isNewPostToastSuccessfulMsgVisible = false;
        }
        return isNewPostToastSuccessfulMsgVisible;
    }
}