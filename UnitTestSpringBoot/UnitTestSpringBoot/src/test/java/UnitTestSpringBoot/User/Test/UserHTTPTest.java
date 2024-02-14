package UnitTestSpringBoot.User.Test;
import UnitTestSpringBoot.User.Entity.DTO.UserCreateDTO;
import UnitTestSpringBoot.User.Entity.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserHTTPTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void shouldReturnListOfUsers() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user/all",
                List.class)).isNotNull();
    }
    @Test
    void shouldCreateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserCreateDTO userCreateDTO = new UserCreateDTO("testCreate");

        HttpEntity<UserCreateDTO> request = new HttpEntity<>(userCreateDTO, headers);

        ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:" + port + "/user/create", request, User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("testCreate");
    }
}
