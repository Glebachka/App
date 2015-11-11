package com.saienko.service;

import com.saienko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by gleb on 11.11.2015.
 */
@Service("customUserDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userSsn) throws UsernameNotFoundException {
        User user = userService.findUserBySsn(userSsn);
        if (user == null) {
            throw new UsernameNotFoundException("Username not Found in CustomUserDetailsService");
        }
//        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getUserSsn(), user.getUserName(), user.getUserPassword(),true, true,true,true,getGrantedAuthorities(user));
        //Here can be mistake. I use hard equals fin this place.
        return (UserDetails) user;
    }
//this is method for get auth type for user and transmute it into UserDetailsService.
//    private List<GrantedAuthority> getGrantedAuthorities(User user){
//        List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
//
//        for (UserRole userRole : user.getUserRoles()){
//            authorities.add(new SimpleGrantedAuthority("ROLE"+userRole.getRole()));
//        }
//        return authorities;
//    }
}
