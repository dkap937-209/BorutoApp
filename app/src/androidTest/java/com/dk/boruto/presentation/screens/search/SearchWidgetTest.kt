package com.dk.boruto.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText(){

        val text = mutableStateOf("")
        val textInput = "Sas"

        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onSearchClicked = {},
                onClosedClicked = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput(textInput)

        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals(textInput)
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseIconOnce_assertEmptyInputText(){

        val text = mutableStateOf("")
        val textInput = "Sas"

        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onSearchClicked = {},
                onClosedClicked = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput(textInput)

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressClosedTwice_assertClosedState(){

        val text = mutableStateOf("")
        val textInput = "Sas"
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {

            if(searchWidgetShown.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onClosedClicked = { searchWidgetShown.value  = false }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput(textInput)

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressClosedOnceWhenInputIsEmpty_assertClosedState(){

        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {

            if(searchWidgetShown.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onClosedClicked = { searchWidgetShown.value  = false }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
}