package ag.pinguin.issuetracker.service.impl;

import ag.pinguin.issuetracker.entity.Developer;
import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;
import ag.pinguin.issuetracker.repository.DeveloperRepository;
import ag.pinguin.issuetracker.service.DeveloperService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public DeveloperResponse add(DeveloperRequest developerRequest) {

        Developer developer = mapper.map(developerRequest, Developer.class);

        return mapper.map(developerRepository.save(developer), DeveloperResponse.class);
    }
}
