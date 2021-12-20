package ag.pinguin.issuetracker.test.service;

import ag.pinguin.issuetracker.exception.WorkOverflowOnSprintPeriodException;
import ag.pinguin.issuetracker.service.StoryServiceUtil;
import ag.pinguin.issuetracker.service.impl.StoryServiceUtilImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("Test of methods in story service")
public class StoryServiceTest {

    private StoryServiceUtil storyServiceUtil;
    private int maxWorkloadPerDeveloper = 10;

    @BeforeEach
    public void setUp() {
        storyServiceUtil = new StoryServiceUtilImpl();

    }

    @Test
    @DisplayName("The total amount of story points in a week should not exceed number_of_developer times 10")
    public void testCheckTotalStoryLimitAgainstNumberOfDevelopers() {

        int newStoryPoint = 5;
        long availableStoryPoints = 4;
        long numberOfDevelopers = 2;

        WorkOverflowOnSprintPeriodException exception
                = Assertions.assertThrows(WorkOverflowOnSprintPeriodException.class,
                () -> storyServiceUtil.checkAvailabilityOfAssignmentToSprint(newStoryPoint, availableStoryPoints, numberOfDevelopers, maxWorkloadPerDeveloper));

        Assertions.assertEquals("The total amount of story points in a sprint " +
                "should not exceed number of developer times " + maxWorkloadPerDeveloper, exception.getMessage());

    }

    @Test
    @DisplayName("One developer can complete maximum 10 story points a week")
    public void testCheckMaxStoryLimitOfDeveloper() {

        int sum = 8;
        int storyPoint = 3;

        WorkOverflowOnSprintPeriodException exception
                = Assertions.assertThrows(WorkOverflowOnSprintPeriodException.class,
                () -> storyServiceUtil.checkAvailabilityOfAssignmentToDeveloper(sum, storyPoint, maxWorkloadPerDeveloper));

        Assertions.assertEquals("One developer can complete maximum " + maxWorkloadPerDeveloper + "-story points in a sprint", exception.getMessage());

    }
}
