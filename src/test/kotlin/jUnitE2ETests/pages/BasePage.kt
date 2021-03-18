package jUnitE2ETests.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

open class BasePage(_driver: WebDriver) {
    protected var driver: WebDriver = _driver
    protected lateinit var root: String

    init {
        PageFactory.initElements(driver, this)
    }

    fun go(): BasePage {
        driver.get(root)
        return this
    }

    fun find(element: By?): WebElement? {
        return driver.findElement(element)
    }
}