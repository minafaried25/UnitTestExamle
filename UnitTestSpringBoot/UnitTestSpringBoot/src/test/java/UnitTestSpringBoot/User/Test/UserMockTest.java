package UnitTestSpringBoot.User.Test;

import UnitTestSpringBoot.User.Controller.UserController;
import UnitTestSpringBoot.User.Entity.DTO.UserCreateDTO;
import UnitTestSpringBoot.User.Entity.User;
import UnitTestSpringBoot.User.Service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(UserController.class)
public class UserMockTest {
    @MockBean
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void contextLoads(){
        assertThat(userService).isNotNull();
        assertThat(mockMvc).isNotNull();
    }
    @Test
    public void shouldReturnListOfUsers() throws Exception {
       when(userService.getAll()).thenReturn(List.of(new User("1","test")));
       this.mockMvc.perform(get("/user/all"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
               . andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("1")));
    }
    @Test
    public void shouldCreateUser() throws Exception {
        when(userService.create(new UserCreateDTO("testCreate"))).thenReturn(new User("1","testCreate"));
        this.mockMvc.
                perform(MockMvcRequestBuilders.post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"testCreate\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.instanceOf(String.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("testCreate")));
        verify(userService).create(any(UserCreateDTO.class));
    }
}
