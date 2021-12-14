package ag.pinguin.issuetracker.service;

import ag.pinguin.issuetracker.model.StoryRequest;
import ag.pinguin.issuetracker.model.StoryResponse;

import java.util.List;

public interface StoryService {

    StoryResponse add(StoryRequest storyRequest);

    List<StoryResponse> getStories();

    StoryResponse assignToDeveloper(Long storyId, Long developerId);
}
