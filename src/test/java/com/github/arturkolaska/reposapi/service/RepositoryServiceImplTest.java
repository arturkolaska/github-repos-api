package com.github.arturkolaska.reposapi.service;

import com.github.arturkolaska.reposapi.exception.WebClientErrorException;
import com.github.arturkolaska.reposapi.model.RepositoryModel;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepositoryServiceImplTest {

    private String username;

    @Autowired
    private RepositoryServiceImpl service;

    @Test
    void getAllRepositoriesByUsername_GivenWrongUsername_ShouldThrowWebClientErrorException() {
        // given
        username = RandomString.make(20);

        // then
        assertThrows(WebClientErrorException.class, () -> service.getAllRepositoriesByUsername(username));
    }

    @Test
    void getTotalStarsByUsername_GivenWrongUsername_ShouldThrowWebClientErrorException() {
        // given
        username = RandomString.make(20);

        // then
        assertThrows(WebClientErrorException.class, () -> service.getTotalStarsByUsername(username));
    }

    @Test
    void getAllRepositoriesByUsername_GivenRightUsername_ShouldReturnListOfRepos() {
        // given
        username = "arturkolaska";

        try {
            // when
            List<RepositoryModel> response = service.getAllRepositoriesByUsername(username);

            // then
            assertNotNull(response);
            assertTrue(response.isEmpty() || response.get(0).getName().length() > 0);

        } catch (WebClientErrorException e) {
            fail("WebClientErrorException has occured.");
        }
    }

    @Test
    void getTotalStarsByUsername_GivenRightUsername_ShouldReturnInteger() {
        // given
        username = "arturkolaska";

        try {
            // when
            Integer response = service.getTotalStarsByUsername(username);

            // then
            assertNotNull(response);

        } catch (WebClientErrorException e) {
            fail("WebClientErrorException has occured.");
        }
    }
}