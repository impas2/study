package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.services.IUserService;

@Component
public class WebUserDetailsServiceImpl implements UserDetailsService {

    final IUserService userService;

    @Autowired
    public WebUserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        UserDetails user2 = User
//                .withUsername("admin")
//                .password(new BCryptPasswordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return user2;

        return userService.getUserByName(s);
    }
}
