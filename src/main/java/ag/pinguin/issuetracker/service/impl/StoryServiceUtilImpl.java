package ag.pinguin.issuetracker.service.impl;

import ag.pinguin.issuetracker.exception.WorkOverflowOnSprintPeriodException;
import ag.pinguin.issuetracker.service.StoryServiceUtil;
import org.springframework.stereotype.Service;

@Service
public class StoryServiceUtilImpl implements StoryServiceUtil {

    public void checkAvailabilityOfAssignmentToSprint(int newStoryPoint, long availableStoryPoints, long numberOfDevelopers, int maxWorkloadPerDeveloper) {
        if (newStoryPoint > availableStoryPoints) {
            throw new WorkOverflowOnSprintPeriodException("The total amount of story points in a sprint should not exceed number of developer times " + maxWorkloadPerDeveloper);
        }
    }

    public void checkAvailabilityOfAssignmentToDeveloper(int sum, int storyPoint, int maxWorkloadPerDeveloper) {
        if (sum + storyPoint > maxWorkloadPerDeveloper) {
            throw new WorkOverflowOnSprintPeriodException("One developer can complete maximum " + maxWorkloadPerDeveloper + "-story points in a sprint");
        }
    }
}
