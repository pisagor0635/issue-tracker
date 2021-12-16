package ag.pinguin.issuetracker.service.impl;

import ag.pinguin.issuetracker.entity.Bug;
import ag.pinguin.issuetracker.entity.Developer;
import ag.pinguin.issuetracker.exception.DuplicateEntryException;
import ag.pinguin.issuetracker.exception.ResourceNotFoundException;
import ag.pinguin.issuetracker.model.BugRequest;
import ag.pinguin.issuetracker.model.BugResponse;
import ag.pinguin.issuetracker.repository.BugRepository;
import ag.pinguin.issuetracker.repository.DeveloperRepository;
import ag.pinguin.issuetracker.service.BugService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    @Override
    public BugResponse add(BugRequest bugRequest) {
        BugResponse bugResponse = new BugResponse();
        Bug bug = mapper.map(bugRequest, Bug.class);

        try {
            bugResponse = mapper.map(bug = bugRepository.save(bug), BugResponse.class);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntryException("Bug with this issue id already exists");
        }
        return bugResponse;
    }

    @Override
    public List<BugResponse> getBugs() {
        List<BugResponse> bugResponseList = new ArrayList<>();
        bugRepository.findAll().forEach(b -> {
            bugResponseList.add(mapper.map(b, BugResponse.class));
        });
        return bugResponseList;
    }

    @Override
    @Transactional
    public BugResponse assignToDeveloper(Long bugId, Long developerId) {

        Bug bug = bugRepository.findById(bugId)
                .orElseThrow(() -> new ResourceNotFoundException("Bug not exist with id : " + bugId));

        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not exist with id : " + developerId));

        bug.setDeveloper(developer);

        return mapper.map(bug, BugResponse.class);
    }
}
