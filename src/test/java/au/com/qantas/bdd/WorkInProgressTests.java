package au.com.future-airlines.bdd;

import net.thucydides.core.Thucydides;
import net.thucydides.jbehave.ThucydidesJUnitStory;
import org.jbehave.core.annotations.BeforeStory;

/**
 * Created by boses on 6/07/2017.
 */
public class WorkInProgressTests extends ThucydidesJUnitStory {

    @BeforeStory
    public void clearCache(){
        Thucydides.getCurrentSession().clear();
    }

}
