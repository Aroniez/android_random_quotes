package com.aroniez.quotes

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aroniez.quotes.ui.MainActivity
import com.schibsted.spain.barista.assertion.BaristaClickableAssertions.assertClickable
import com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertEnabled
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @JvmField
    @field:Rule
    var rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkIfWidgetsAreDisplayed() {
        assertDisplayed(R.id.refreshQuote, R.string.next_fortune_quote)
        assertDisplayed(R.id.appBarLayout)
        assertNotDisplayed(R.id.baseMessageTextView)
    }

    @Test
    fun checkIfWidgetsAreNotDisplayed() {
        assertNotDisplayed(R.id.baseMessageTextView)
    }


    @Test
    fun checkIfWidgetsAreEnabled() {
        assertEnabled(R.id.baseProgressBar)
        assertEnabled(R.id.baseMessageTextView)
        assertEnabled(R.id.refreshQuote)
    }

    @Test
    fun checkIfButtonIsClickable() {
        assertClickable(R.id.refreshQuote)
    }


}