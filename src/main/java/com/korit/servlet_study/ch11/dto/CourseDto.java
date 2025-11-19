package com.korit.servlet_study.ch11.dto;

import com.korit.servlet_study.ch11.entity.Course;
import lombok.Data;

@Data
public class CourseDto {
    private String code;
    private String name;
    private int professorId;
    private int credit;
    private int enrollmentCapacity;
    private String classroom;

    public Course toEntity(){
        return Course.builder()
                .courseCode(code)
                .courseName(name)
                .professorId(professorId)
                .credit(credit)
                .enrollmentCapacity(enrollmentCapacity)
                .classroom(classroom)
                .build();
    }
}
