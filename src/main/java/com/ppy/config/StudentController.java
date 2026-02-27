package com.ppy.config;

import com.ppy.entity.Student;
import com.ppy.handler.StudentHandler;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentHandler studentHandler;

    @GetMapping("/getAll")
    public String getAllStudents() {
        List<Student> students = studentHandler.getAllStudents();
        return students.toString();
    }

    @GetMapping("/getStuByNo")
    public Student getStudentByNo(String student_no) {
        return studentHandler.getStudentByNo(student_no);
    }

    @PostMapping("/addStudent")
    public void addStudent(Student student) {
        // 参数校验
        if (student.getId() != null && student.getStudentNo() != null) {
            studentHandler.insertStudent(student);
        }
    }
}
