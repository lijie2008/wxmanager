package com.xinda.wx.wxmanager;

import com.baomidou.mybatisplus.plugins.Page;
import com.xinda.wx.wxmanager.entity.Student;
import com.xinda.wx.wxmanager.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxmanagerApplicationTests {

    @Resource
    private StudentService studentService;

    @Test
    public void insert() {
        Student student = new Student();
        student.setWxSex("1");
        student.setWxSname("李杰");
        studentService.insertStudent(student);
    }

    @Test
    public void page() {
        Student student = new Student();
        student.setWxSex("1");
        student.setWxSname("李杰");
        Page<Student> page = studentService.page(student);

        System.out.println(page);
    }
}
