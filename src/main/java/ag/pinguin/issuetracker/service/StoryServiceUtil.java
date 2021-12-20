package ag.pinguin.issuetracker.service;

public interface StoryServiceUtil {

    void checkAvailabilityOfAssignmentToSprint(int newStoryPoint, long availableStoryPoints, long numberOfDevelopers, int maxWorkloadPerDeveloper);

    void checkAvailabilityOfAssignmentToDeveloper(int sum, int storyPoint, int maxWorkloadPerDeveloper);
}
