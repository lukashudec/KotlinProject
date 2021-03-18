package jUnitE2ETests.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SignInPage(driver: WebDriver) : BasePage(driver) {

    @FindBy(xpath = "//form[@name='loginform']")
    protected lateinit var loginForm: WebElement

    @FindBy(id = "inputUsername")
    protected lateinit var username: WebElement

    @FindBy(id = "inputPassword")
    protected lateinit var password: WebElement

    fun signIn(username: String?, password: String?) {
        this.username.sendKeys(username)
        this.password.sendKeys(password)
    }

    fun isDisplayed(): Boolean {
        return loginForm.isDisplayed
    }

    fun usernameDisplayed(): Boolean {
        return username.isDisplayed
    }

    fun passwordDisplayed(): Boolean {
        return password.isDisplayed
    }
}