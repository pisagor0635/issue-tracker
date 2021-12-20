package ag.pinguin.issuetracker.test.service;

import ag.pinguin.issuetracker.exception.WorkOverflowOnSprintPeriodException;
import ag.pinguin.issuetracker.service.StoryServiceUtil;
import ag.pinguin.issuetracker.service.impl.StoryServiceUtilImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StoryServiceTest {

    private StoryServiceUtil storyServiceUtil;

    @BeforeEach
    public void setUp() {
        storyServiceUtil = new StoryServiceUtilImpl();

    }

    @Test
    @DisplayName("One developer can complete 10 story points a week")
    public void testCheckMaxStoryLimitOfDeveloper() {

        int newStoryPoint = 5;
        long availableStoryPoints = 4;
        long numberOfDevelopers = 2;

        WorkOverflowOnSprintPeriodException exception
                = Assertions.assertThrows(WorkOverflowOnSprintPeriodException.class,
                () -> storyServiceUtil.checkAvailabilityOfAssignmentToSprint(newStoryPoint, availableStoryPoints, numberOfDevelopers));

        Assertions.assertEquals("Number of Developer : 2 Available story points : 4", exception.getMessage());


    }

    @Test
    @DisplayName("The total amount of story points in a week should not exceed number_of_developer times 10")
    public void testCheckTotalStoryLimitAgainstNumberOfDevelopers() {

        int sum = 8;
        int storyPoint = 3;
        int maxWorkloadPerDeveloper = 10;

        WorkOverflowOnSprintPeriodException exception
                = Assertions.assertThrows(WorkOverflowOnSprintPeriodException.class,
                () -> storyServiceUtil.checkAvailabilityOfAssignmentToDeveloper(sum, storyPoint, maxWorkloadPerDeveloper));

        Assertions.assertEquals("Current sum of story points of a user is : 8 . Available story points: 2", exception.getMessage());

    }
}
