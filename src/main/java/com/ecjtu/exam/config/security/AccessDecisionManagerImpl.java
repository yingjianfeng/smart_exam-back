package com.ecjtu.exam.config.security;

import com.ecjtu.exam.pojo.Role;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
//        System.out.println("当前： "+authentication.getPrincipal());
//        System.out.println("AccessDecisionManagerImpl");
        //迭代器遍历目标url的权限列表
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            String needRole = ca.getAttribute();
//            if ("ROLE_LOGIN".equals(needRole)) {
//                if (authentication instanceof AnonymousAuthenticationToken) {
//                    throw new BadCredentialsException("未登录");
//                } else{
//                    return;
//                }
//            }
            //遍历当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                System.out.println("当前用户所具有的权限:"+authority.getAuthority());
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
//        System.out.println("权限不足");
        //执行到这里说明没有匹配到应有权限
        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
