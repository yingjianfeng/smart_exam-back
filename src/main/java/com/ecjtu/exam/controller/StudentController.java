package com.ecjtu.exam.controller;

import com.ecjtu.exam.pojo.Student;
import com.ecjtu.exam.service.IStudnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    IStudnetService iStudnetService;

    @GetMapping("/queryall")
    public List<Student> queryall(){
        return iStudnetService.qryAll();
    }

}
