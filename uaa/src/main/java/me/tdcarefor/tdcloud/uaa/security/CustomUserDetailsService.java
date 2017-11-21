package me.tdcarefor.tdcloud.uaa.security;

import java.util.ArrayList;
import java.util.List;
import me.tdcarefor.tdcloud.remotert.TdCloudClientRT;
import me.tdcarefor.tdcloud.uaa.query.entity.UserEntity;
import me.tdcarefor.tdcloud.uaa.query.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userModel = userQueryRepository.findByUserName(username);
        if (null == userModel) {
            throw new UsernameNotFoundException("No user present with openid: " + username);
        } else {
            List<String> userRoles = new ArrayList<>();

            return new CustomUserDetails(userModel, userRoles);
        }
    }

}
