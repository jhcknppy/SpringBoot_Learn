package com.ppy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppy.entity.Student;
import com.ppy.mapper.StudentMapper;
import com.ppy.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
