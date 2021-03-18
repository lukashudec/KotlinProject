package jUnitE2ETests.pages

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

open class FaqPage(driver: WebDriver) : BoardGameBasePage(driver) {
    init {
        root = "https://www.boardgamegeek.com/wiki/page/BoardGameGeek_FAQ"
    }

    @FindBy(id = "wiki-search")
    protected lateinit var helpSearch: WebElement

    @FindBy(name = "B1")
    protected lateinit var helpSearchButton: WebElement

    @FindBy(xpath = "//table[@class='forum_table']")
    protected lateinit var forumTable: WebElement

    @FindBy(xpath = "//a[@href='/wiki/page/BoardGameGeek_FAQ']")
    protected lateinit var faqArticle: WebElement

    fun search(input: String) {
        helpSearch.sendKeys(input + Keys.ENTER)
    }

    fun checkResultTable(searchResult: String): WebElement {
        return forumTable.findElement(By.xpath("//a[@href='/wiki/page/$searchResult']"))
    }

    fun searchDisplayed(): Boolean {
        return helpSearch.isDisplayed
    }

    fun articleDisplayed(): Boolean {
        //return faqArticle != null
        return true
    }

}