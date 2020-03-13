package com.ecjtu.exam.config.security;

import com.ecjtu.exam.dao.IPeopleDao;
import com.ecjtu.exam.pojo.Resource;
import com.ecjtu.exam.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.security.access.SecurityConfig;
import java.util.Collection;
import java.util.List;

@Component
//接收用户请求的地址，返回访问该地址需要的所有权限
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    public IPeopleDao iPeopleDao;

    @Override
    //接收用户请求的地址，返回访问该地址需要的所有权限
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //得到用户的请求地址,控制台输出一下
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if(requestUrl.indexOf("?")!=-1){
            requestUrl = requestUrl.substring(0,requestUrl.indexOf("?"));
        }
        System.out.println("用户请求的地址是：" + requestUrl);
//        System.out.println("FilterInvocationSecurityMetadataSourceImpl");

        //如果登录页面就不需要权限
        if ("/login".equals(requestUrl)) {
            return null;
        }
        Resource resource = iPeopleDao.getResourceByUrl(requestUrl);
        //如果没有匹配的url则说明大家都可以访问
        if (resource == null) {
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        //将resource所需要到的roles按框架要求封装返回（ResourceService里面的getRoles方法是基于RoleRepository实现的）
        List<Role> roles = iPeopleDao.getRolesByResourceId(resource.getId());
        int size = roles.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            Role role = roles.get(i);
            values[i] = role.getName();
//            System.out.println("values[i]  "+values[i]);
        }

//        System.out.println(SecurityConfig.createList(values));
        return SecurityConfig.createList(values);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}