package web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import web.model.User;
import web.model.UserDTO;
import web.service.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BootCrudApplicationTests {

    UserMapper userMapper;

    public BootCrudApplicationTests(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void userMapperFromDTOtoUser() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setUsername("John");

        User entity = userMapper.userFromDTO(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getUsername(), entity.getUsername());
    }

}
