package au.com.future-airlines;

import net.thucydides.core.Thucydides;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RunAllUserStories.class })
public class TesterSequence {
    @BeforeClass
    public static void beforeStories() throws Exception {
        //TODO Implement before story steps
        Thucydides.getCurrentSession().clear();
    }

    @AfterClass
    public static void afterStories() throws Exception {
        //TODO Implement after story steps
        Thucydides.getCurrentSession().clear();
    }
}
