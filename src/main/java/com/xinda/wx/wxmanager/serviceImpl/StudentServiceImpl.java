package com.xinda.wx.wxmanager.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xinda.wx.wxmanager.dao.StudentDao;
import com.xinda.wx.wxmanager.entity.Student;
import com.xinda.wx.wxmanager.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 学员表 服务实现类
 * </p>
 *
 * @author Lios123
 * @since 2019-07-12
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public Student insertStudent(Student student) {
        studentDao.insert(student);
        return student;
    }

    @Override
    public Page<Student> page(Student student) {
        Page<Student> page = new Page<>();
        page.setSize(1);
        page.setCurrent(1);

        EntityWrapper wrapper = new EntityWrapper();
        wrapper.like(Student.WX_SNAME, student.getWxSname());
        List<Student> list = studentDao.selectPage(page, wrapper);
        page.setRecords(list);
        return page;
    }

}
