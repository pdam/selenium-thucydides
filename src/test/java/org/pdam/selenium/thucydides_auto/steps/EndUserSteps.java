import java.util.Dictionary;
import org.pdam.selenium.thucydides_auto.pages.IndexPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import static ch.lambdaj.Lambda.join;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class EndUserSteps extends ScenarioSteps {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public EndUserSteps(Pages pages) {
		super(pages);
	}

	@Step
	public void enters(String keyword) {
         onIndexPage().enter_keywords(keyword);
	}

    
	@Step
    public void starts_search() {
        onIndexPage().lookup_terms();
    }

    private  IndexPage onIndexPage() {
        return getPages().currentPageAt(IndexPage.class);
    }

    
    @Step
	public void should_see_definition_containing_words(String terms) {
        assertThat(onIndexPage().getDefinitions(), hasItem(containsString(terms)));
	}

    @Step
         public void is_the_home_page() {
        onDictionaryPage().get(getPages());
    }

    

	@Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }
}
