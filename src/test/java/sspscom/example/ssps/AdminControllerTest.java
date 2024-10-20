package sspscom.example.ssps;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sspscom.example.ssps.Dto.Request.UserCreationRequest;
import sspscom.example.ssps.Entity.Role;
import sspscom.example.ssps.Entity.User;
import sspscom.example.ssps.Service.AdminService;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(authorities = {"ADMIN"})
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private UserCreationRequest requestStudent;
    private User userStudent;

    @MockBean
    private AdminService adminService;

    @BeforeEach
    public void initData() {
        requestStudent = UserCreationRequest.builder()
                .email("test@gmail.com")
                .username("student")
                .password("student")
                .build();

        userStudent = User.builder()
                .id("1234")
                .email("test@gmail.com")
                .username("student")
                .password("student")
                .role(Role.USER)
                .build();
    }

    @Test
    // test if add student successfully
    public void addStudent_validrequest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(requestStudent);

        Mockito.when(adminService.addStudent(requestStudent)).thenReturn(userStudent);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/admin/add/student")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000));
    }

    @Test
    // test if add student fail
    public void addStudent_wrong_request() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        requestStudent.setUsername("a");
        String content = objectMapper.writeValueAsString(requestStudent);

        Mockito.when(adminService.addStudent(requestStudent)).thenReturn(userStudent);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/admin/add/student")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000));

    }

}
