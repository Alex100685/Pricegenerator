package ua.autoshop.security;

/**
 * Created by Пользователь on 08.10.2015.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.autoshop.dal.Dao;
import ua.autoshop.model.User;
import java.lang.Override;
import java.lang.String;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private Dao<User> daoUser;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = daoUser.findByName(username);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        CustomUserDetailsUser customUserDetailsUser = new CustomUserDetailsUser(
                user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAutorities(user.getUsername()),
                user.getSalt()
        );

        return customUserDetailsUser;
    }

    public Collection<? extends GrantedAuthority> getAutorities(String name) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(name));
        return authList;
    }

    public List<String> getRoles(String name) {
        List<String> roles = new ArrayList<String>();

        if(name.equals("autoshopadmin")) {
            roles.add("ROLE_ADMIN");
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List <String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}
