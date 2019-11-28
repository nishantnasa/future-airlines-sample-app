package au.com.future-airlines;

import net.thucydides.jbehave.ThucydidesJUnitStories;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.ParameterControls;
import org.springframework.util.SystemPropertyUtils;

public class RunAllUserStories extends ThucydidesJUnitStories  {

    private static final String STORY_NAME_PATTERN = "**/${jbehave.story.name:*}.story";

    public RunAllUserStories() {
        findStoriesCalled(storyNamesFromEnvironmentVariable());
    }

    @Override
    public Configuration configuration() {
        return super.configuration()
                .useParameterControls(new ParameterControls().useDelimiterNamedParameters(true));
    }

    private String storyNamesFromEnvironmentVariable() {
//        return SystemPropertyUtils.resolvePlaceholders("**/*.story");
        return SystemPropertyUtils.resolvePlaceholders(STORY_NAME_PATTERN);
    }
}
