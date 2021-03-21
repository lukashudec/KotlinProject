package jUnitE2ETests.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


open class AbstractPage(protected var driver: WebDriver) {
    protected open lateinit var root: String

    init {
        PageFactory.initElements(driver, this)
    }

    open fun visit(): AbstractPage {
        driver.get(root)
        return this
    }

    fun find(element: By): WebElement? {
        return driver.findElement(element)
    }
}