package ag.pinguin.issuetracker.service.impl;

import ag.pinguin.issuetracker.entity.Developer;
import ag.pinguin.issuetracker.exception.ResourceNotFoundException;
import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;
import ag.pinguin.issuetracker.repository.DeveloperRepository;
import ag.pinguin.issuetracker.service.DeveloperService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public DeveloperResponse add(DeveloperRequest developerRequest) {
        Developer developer = mapper.map(developerRequest, Developer.class);
        return mapper.map(developerRepository.save(developer), DeveloperResponse.class);
    }

    @Override
    public List<DeveloperResponse> getDevelopers() {
        List<DeveloperResponse> developerResponseList = new ArrayList<>();
        developerRepository.findAll().forEach(d ->
                developerResponseList.add(mapper.map(d, DeveloperResponse.class)));
        return developerResponseList;
    }

    @Override
    public Map<String, Boolean> remove(Long id) {

        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Developer not exist with id : " + id));
        developerRepository.delete(developer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    public void setDeveloperRepository(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }
}
