package com.OmdaSolutions.crudDemo.dao;

import com.OmdaSolutions.crudDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private final EntityManager entityManager ;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class ,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student Where lastName = :theData",Student.class);
        theQuery.setParameter("theData",theLastName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void updateStudent(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(int Id) {
        Student theStudent = entityManager.find(Student.class,Id);
        if(theStudent != null)
        {
            entityManager.remove(theStudent);
        }
        else {
            System.out.println("No Student Found by this id");
        }
    }

    @Override
    @Transactional
    public int deleteAllStudents() {
        int numRowsDeleted =  entityManager.createQuery("Delete From Student").executeUpdate();
        return numRowsDeleted;
    }
}
