package org.pdam.selenium.thucydides_auto

import org.pdam.selenium.thucydides_auto.steps.EndUserSteps
import org.pdam.selenium.thucydides_auto.requirements.Application.Search.SearchByKeyword

using "thucydides"

thucydides.uses_default_base_url "http://www.immdb.com"
thucydides.uses_steps_from EndUserSteps
thucydides.tests_story SearchByKeyword

scenario "Looking up the definition of 'apple'", {
    given "the user is on the  home page", {
        end_user.is_the_home_page()
    }
    when "the end user looks up the definition of the word 'X-Men'", {
        end_user.looks_for "Wolverine"
    }
    then "they should see the definition of 'XMen", {
       end_user.should_see_definition_containing_words "Mutants"
    }
}
