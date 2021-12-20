package test;

import ag.pinguin.issuetracker.exception.ResourceNotFoundException;
import ag.pinguin.issuetracker.model.DeveloperRequest;
import ag.pinguin.issuetracker.model.DeveloperResponse;
import ag.pinguin.issuetracker.repository.DeveloperRepository;
import ag.pinguin.issuetracker.service.DeveloperService;
import ag.pinguin.issuetracker.service.impl.DeveloperServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeveloperServiceTest {

    private DeveloperService developerService;

    private DeveloperRepository developerRepository;

    @BeforeEach
    public void setUp() {
        developerRepository = Mockito.mock(DeveloperRepository.class);
        developerService = new DeveloperServiceImpl(developerRepository);

    }

    @Test
    public void testAdd() {

        DeveloperRequest developerRequest = new DeveloperRequest();
        developerRequest.setName("Test");

        DeveloperResponse developerResponse = developerService.add(developerRequest);

        Assertions.assertEquals(developerRequest.getName(), developerResponse.getName());


    }

    @Test
    public void testRemoveNonExistingDeveloper() {

        Long nonExistingDeveloperId = -1L;

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> developerService.remove(nonExistingDeveloperId));

        Assertions.assertEquals("Developer not exist with id : " + nonExistingDeveloperId, exception.getMessage());

    }


}
