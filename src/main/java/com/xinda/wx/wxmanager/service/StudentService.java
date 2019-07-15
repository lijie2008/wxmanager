package com.xinda.wx.wxmanager.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xinda.wx.wxmanager.entity.Student;

import java.util.List;

/**
 * <p>
 * 学员表 服务类
 * </p>
 *
 * @author Lios123
 * @since 2019-07-12
 */
public interface StudentService extends IService<Student> {
    Student insertStudent(Student student);

    Page<Student> page(Student student);
}
