package com.ppy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("student")
@Data
public class Student {
    @TableField("id")
    private Integer id;
    @TableField("student_no")
    private String studentNo;
    @TableField("name")
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("class_name")
    private String className;
    @TableField("grade")
    private String grade;
    @TableField("gender")
    private String gender;
    @TableField("hometown")
    private String hometown;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", student_no='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", class_name='" + className + '\'' +
                ", grade='" + grade + '\'' +
                ", gender='" + gender + '\'' +
                ", hometown='" + hometown + '\'' +
                '}';
    }

}
