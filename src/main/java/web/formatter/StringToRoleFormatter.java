package web.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.services.IUserService;
import java.text.ParseException;
import java.util.Locale;

@Component
public class StringToRoleFormatter implements Formatter<Role> {

    private IUserService userService;

    @Autowired
    public StringToRoleFormatter(IUserService userService) {
        this.userService=userService;
    }

    @Override
    public Role parse(String s, Locale locale) throws ParseException {
        return userService.getRoleById(Long.valueOf(s));
    }

    @Override
    public String print(Role role, Locale locale) {
        return role.toString();
    }
}
