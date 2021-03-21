package jUnitE2ETests.pages

import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SignInPage(driver: WebDriver) : AbstractPage(driver) {

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

    fun isDisplayed(): SignInPage {
        assertTrue(loginForm.isDisplayed)
        return this
    }

    fun usernameDisplayed(): SignInPage {
        assertTrue(username.isDisplayed)
        return this
    }

    fun passwordDisplayed(): SignInPage {
        assertTrue(password.isDisplayed)
        return this
    }
}