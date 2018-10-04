package com.novoda.androidstoreexample.tests

import android.support.test.rule.ActivityTestRule
import com.novoda.androidstoreexample.activities.MainActivity
import com.novoda.androidstoreexample.data.repositories.ArticleRepository
import com.novoda.androidstoreexample.userflows.productUserFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EspressoTestExampleWithRepositories {

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    lateinit var articleRepository: ArticleRepository

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = activityTestRule

    @Before
    fun setUp() {
        articleRepository = ArticleRepository()
    }

    @Test
    fun testNavigationWithFirstItemFromRepository() {
        val hat = articleRepository.getHat()

        productUserFlow {
            navigateToCategory(hat.category)
            openItemFromProductlist(hat.title)
            checkThatCorrectProductIsDisplayed()
        }
    }

    @Test
    fun testNavigationWithRandomItemFromRepository() {
        val hat = articleRepository.getRandomHat()

        productUserFlow {
            navigateToCategory(hat.category)
            openItemFromProductlist(hat.title)
            checkThatCorrectProductIsDisplayed()
        }
    }

    @Test
    fun testNavigationWithItemFromFixture() {
        val hat = articleRepository.standardArticle

        productUserFlow {
            navigateToCategory(hat.category)
            openItemFromProductlist(hat.title)
            checkThatCorrectProductIsDisplayed()
        }
    }
}