package jUnitE2ETests.pages

import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

open class MainPage(driver:WebDriver) : AbstractPage(driver) {
    override var root = "https://www.boardgamegeek.com/"

     override fun visit(): MainPage {
        driver.get(root)
        return this
    }

    @FindBy(name = "searchTerm")
    protected lateinit var searchBar: WebElement

    @FindBy(linkText = "Sign In")
    protected lateinit var signInButton: WebElement

    @FindBy(xpath = "//button[contains(.,'Help ')]")
    protected lateinit var helpDropdown: WebElement

    @FindBy(linkText = "FAQ")
    protected lateinit var faqButton: WebElement

    fun clickOnHelp(): MainPage {
        helpDropdown.click()
        return this
    }

    fun clickOnFaq(): FaqPage {
        faqButton.click()
        return FaqPage(driver)
    }

    fun clickOnSignIn(): SignInPage {
        signInButton.click()
        return SignInPage(driver)
    }

    fun searchGames(input: String): GeekSearchResultPage {
        searchBar.sendKeys(input + Keys.ENTER)
        return GeekSearchResultPage(driver)
    }
}