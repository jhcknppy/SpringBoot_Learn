package com.ppy.handler;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ppy.entity.Student;
import com.ppy.mapper.StudentMapper;
import com.ppy.service.StudentService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentHandler {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private StudentService studentService;

    private static final Logger logger = LoggerFactory.getLogger(StudentHandler.class);

    // 查询所有学生
    public List<Student> getAllStudents() {
        List<Student> students = studentService.list();

        if(!students.isEmpty()){
            for(Student student : students){
                logger.info("Student: " + student);
            }
            return students;
        }else{
            return studentMapper.selectList(null);
        }
    }

    // 根据ID查询学生
    public Student getStudentById(Integer id) {
        Student student = studentService.getById(id);
        if(student != null){
            return student;
        }else{
            return studentMapper.selectById(id);
        }
    }

    // 根据姓名查询学生
    public List<Student> getStudentByName(String name) {
        Wrapper<Student> wrapper = new QueryWrapper<Student>().eq("name", name);
        List<Student> students = studentMapper.selectList(wrapper);
        if(!students.isEmpty()){
            return students;
        }
        return null;
    }

    public Student getStudentByNo(String student_no) {
        Wrapper<Student> wrapper = new QueryWrapper<Student>().eq("student_no", student_no);
        return studentMapper.selectOne(wrapper);

    }

    // 根据学号逻辑删除学生
    public void delStudentByNo(String student_no){
        Wrapper<Student> wrapper = new QueryWrapper<Student>().eq("student_no", student_no);
        studentMapper.delete(wrapper);
    }
    // 根据ID删除学生
    public void delStudentById(Integer id){
        studentMapper.deleteById(id);
    }

    // 批量删除学生
    public void delStudentByIds(List<Integer> ids){
        studentService.removeBatchByIds(ids);
    }

    // 插入学生
    public int insertStudent(Student student){
        return studentMapper.insert(student);
    }

}
