package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Course;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RequiredArgsConstructor
public class CourseDao {
    private final DBConnectionMgr mgr;

    public void insert(Course course) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = mgr.getConnection();
            String sql = """
                    insert into course_tb
                    values (default, ?, ?, ?, ?, ?, ?)
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, course.getCourseCode());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getProfessorId());
            ps.setInt(4, course.getCredit());
            ps.setInt(5, course.getEnrollmentCapacity());
            ps.setString(6, course.getClassroom());
            ps.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mgr.freeConnection(con, ps);
    }

}
