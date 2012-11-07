package org.pdam.selenium.thucydides_auto;

import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.runners.ThucydidesRunner;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.pdam.selenium.thucydides_auto.steps.EndUserSteps;

@Story(ApplicationSearch( SearchByKeyword.class))
@RunWith(ThucydidesRunner.class)
public class LookupADefinitionStoryTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://www.imdb.com")
    public Pages pages;

    @Steps
    public EndUserSteps endUser;

    @Issue("#WIKI-1")
    @Test
    public void looking_up_the_definition_of_xmen_should_display_the_corresponding_article() {
        endUser.is_the_home_page();
		endUser.looks_for("Wolverine");
        endUser.should_see_definition_containing_words("X-Men");

    }

    @Test
    public void slooking_up_the_definition_of_banana_should_display_the_corresponding_article() {
        endUser.is_the_home_page();
		endUser.looks_for("XMen");
		endUser.should_see_definition_containing_words("Mutants");
    }

    @Pending @Test
    public void searching_by_ambiguious_keyword_should_display_the_disambiguation_page() {
    }
} 