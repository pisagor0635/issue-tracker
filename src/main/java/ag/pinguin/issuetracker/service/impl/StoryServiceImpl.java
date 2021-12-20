package ag.pinguin.issuetracker.service.impl;

import ag.pinguin.issuetracker.entity.Developer;
import ag.pinguin.issuetracker.entity.Story;
import ag.pinguin.issuetracker.exception.DuplicateEntryException;
import ag.pinguin.issuetracker.exception.ResourceNotFoundException;
import ag.pinguin.issuetracker.exception.WorkOverflowOnSprintPeriodException;
import ag.pinguin.issuetracker.model.StoryRequest;
import ag.pinguin.issuetracker.model.StoryResponse;
import ag.pinguin.issuetracker.repository.DeveloperRepository;
import ag.pinguin.issuetracker.repository.StoryRepository;
import ag.pinguin.issuetracker.service.StoryService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;

    private final DeveloperRepository developerRepository;

    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    @Override
    public StoryResponse add(StoryRequest storyRequest) {
        StoryResponse storyResponse = new StoryResponse();
        checkMaxStoryLimitOfDeveloper(storyRequest);
        Story story = mapper.map(storyRequest, Story.class);
        try {
            storyResponse = mapper.map(storyRepository.save(story), StoryResponse.class);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntryException("Story with this issue id already exists");

        }
        return storyResponse;
    }

    private void checkMaxStoryLimitOfDeveloper(StoryRequest storyRequest) {
        long numberOfDevelopers = developerRepository.count();
        int currentStoryPoints = storyRepository.findAll().stream().filter(s -> (s.getFromDate()
                .after(storyRequest.getSprintStartDate()) && s.getToDate()
                .before(storyRequest.getSprintEndDate()))).mapToInt(s -> s.getStoryPoint()).sum();
        long maxTotalStoryPoints = numberOfDevelopers * 10;
        long availableStoryPoints = maxTotalStoryPoints - currentStoryPoints;
        if (storyRequest.getStoryPoint() > availableStoryPoints) {
            throw new WorkOverflowOnSprintPeriodException("Number of Developer : "
                    + numberOfDevelopers + " Available story points : " + availableStoryPoints);
        }
    }

    @Override
    public List<StoryResponse> getStories() {

        List<StoryResponse> storyResponseList = new ArrayList<>();
        storyRepository.findAll().forEach(s ->
                storyResponseList.add(mapper.map(s, StoryResponse.class)));
        return storyResponseList;
    }

    @Override
    @Transactional
    public StoryResponse assignToDeveloper(Long storyId, Long developerId) {
        checkTotalStoryLimitAgainstNumberOfDevelopers(storyId, developerId);
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new ResourceNotFoundException("Story not exist with id : " + storyId));
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not exist with id : " + developerId));
        story.setDeveloper(developer);
        return mapper.map(story, StoryResponse.class);
    }

    private void checkTotalStoryLimitAgainstNumberOfDevelopers(Long storyId, Long developerId) {
        long sum = storyRepository.findAll().stream().filter(f -> f.getDeveloper() != null
                && f.getDeveloper().getId() == developerId).mapToInt(s -> s.getStoryPoint()).sum();
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new ResourceNotFoundException("Story not exist with id : " + storyId));
        if (sum + story.getStoryPoint() > 10) {
            throw new WorkOverflowOnSprintPeriodException("Current sum of story points of a user is : "
                    + sum + " . Available story points: " + (10 - sum));
        }
    }
}
