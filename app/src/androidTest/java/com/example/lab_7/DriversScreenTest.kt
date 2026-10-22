
package com.example.lab_7

import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertFalse

@RunWith(AndroidJUnit4::class)
class DriversScreenTest {

    @get:Rule
    val composeTestRule =
        createAndroidComposeRule<MainActivity>()

    @Test
    fun add_clear_and_return_from_drivers() {

        // 1. Navigate from Home to Drivers
        composeTestRule
            .onNodeWithTag("driversButton")
            .performClick()

        // 2. Verify the Drivers screen appears
        composeTestRule
            .onNodeWithText("FormulaZero Drivers")
            .assertIsDisplayed()

        // 3. Enter a driver name
        composeTestRule
            .onNodeWithTag("driverNameInput")
            .performTextInput("Max Verstappen")

        // 4. Click Add
        composeTestRule
            .onNodeWithTag("addDriverButton")
            .performClick()

        // 5. Verify the driver appears
        composeTestRule
            .onNodeWithText("Max Verstappen")
            .assertIsDisplayed()

        // 6. Clear the drivers list
        composeTestRule
            .onNodeWithTag("clearDriversButton")
            .performClick()

        // 7. Verify the driver was removed
        val driverExists = composeTestRule
            .onAllNodesWithText("Max Verstappen")
            .fetchSemanticsNodes()
            .isNotEmpty()

        assertFalse(
            "Driver should disappear after clicking Clear",
            driverExists
        )

        // 8. Return to Home
        composeTestRule
            .onNodeWithTag("driversBackButton")
            .performClick()

        // 9. Verify the Home screen appears
        composeTestRule
            .onNodeWithText("FormulaZero Home")
            .assertIsDisplayed()
    }
}
