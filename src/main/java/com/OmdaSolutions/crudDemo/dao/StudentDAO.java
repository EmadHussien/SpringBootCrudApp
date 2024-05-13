package com.OmdaSolutions.crudDemo.dao;

import com.OmdaSolutions.crudDemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(int id);
    List<Student> findAll();
    List<Student> findByLastName(String theLastName);

    void updateStudent(Student theStudent);
    void deleteStudent(int Id);

    int deleteAllStudents();
}
