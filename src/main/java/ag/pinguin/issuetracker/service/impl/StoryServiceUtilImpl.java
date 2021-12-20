package ag.pinguin.issuetracker.service.impl;

import ag.pinguin.issuetracker.exception.WorkOverflowOnSprintPeriodException;
import ag.pinguin.issuetracker.service.StoryServiceUtil;
import org.springframework.stereotype.Service;

@Service
public class StoryServiceUtilImpl implements StoryServiceUtil {

    public void checkAvailabilityOfAssignmentToSprint(int newStoryPoint, long availableStoryPoints, long numberOfDevelopers) {
        if (newStoryPoint > availableStoryPoints) {
            throw new WorkOverflowOnSprintPeriodException("Number of Developer : "
                    + numberOfDevelopers + " Available story points : " + availableStoryPoints);
        }
    }

    public void checkAvailabilityOfAssignmentToDeveloper(int sum, int storyPoint, int maxWorkloadPerDeveloper) {
        if (sum + storyPoint > maxWorkloadPerDeveloper) {
            throw new WorkOverflowOnSprintPeriodException("Current sum of story points of a user is : "
                    + sum + " . Available story points: " + (maxWorkloadPerDeveloper - sum));
        }
    }
}
