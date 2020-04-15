package com.ecjtu.exam.dao;

import com.ecjtu.exam.pojo.People;
import com.ecjtu.exam.pojo.Resource;
import com.ecjtu.exam.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPeopleDao {
    public List<People> qryAll();

    public People qryByAccountAndType(People people) ;

    public People qryByAccount(String account) ;

    public void insert(People people) ;

    public List<Role> qryRoleOfPeople(int  roleid);

    public Resource getResourceByUrl(String url);
    public List<Role> getRolesByResourceId(int resourceId);

    public People qryById(int id) ;

    public void UpdatePwdById(@Param("password") String password,@Param("id")  int id);

    public void UpdateImgById(@Param("img") String img,@Param("id")  int id);

}
