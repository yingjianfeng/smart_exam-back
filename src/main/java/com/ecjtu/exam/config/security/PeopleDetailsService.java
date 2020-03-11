package com.ecjtu.exam.config.security;


import com.ecjtu.exam.dao.IPeopleDao;
import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleDetailsService implements UserDetailsService {
    @Autowired
    IPeopleDao iPeopleDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // TODO 根据用户名，查找到对应的密码，与权限
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
//        System.out.println("account:  "+s);
//        System.out.println("PeopleDetailsService");
        People people = iPeopleDao.qryByAccount(s);
        if(people == null)
        {
            throw new UsernameNotFoundException("没有该用户");
        }
//        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
//        String hashPass = bcryptPasswordEncoder.encode(people.getPassword());
//        people.setPassword(hashPass);
        List<Role> roles = iPeopleDao.qryRoleOfPeople(people.getRole_id());
        System.out.println("............"+roles);
        return new PeopleDetailsImpl(people,roles);
    }
}