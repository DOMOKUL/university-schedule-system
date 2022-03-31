package com.company.schedule.management.system.service.impl;

import com.company.schedule.management.system.dao.StudentDao;
import com.company.schedule.management.system.dao.exception.DaoException;
import com.company.schedule.management.system.model.Student;
import com.company.schedule.management.system.service.StudentService;
import com.company.schedule.management.system.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentDao studentDao;

    @Override
    public Student saveStudent(Student student) {
        try {
            return studentDao.create(student);
        } catch (DaoException cause) {
            throw new ServiceException(cause);
        }
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.findById(id).orElseThrow(() -> new ServiceException("Student with id: " + id + " doesn't exist"));
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentDao.findAll();
        } catch (DaoException cause) {
            throw new ServiceException(cause);
        }
    }

    @Override
    public Student updateStudent(Student student) {
        try {
            return studentDao.update(student);
        } catch (DaoException cause) {
            throw new ServiceException(cause);
        }
    }

    @Override
    public boolean deleteStudentById(Long id) {
        try {
            return studentDao.deleteById(id);
        } catch (DaoException cause) {
            throw new ServiceException("Student doesn't delete ", cause);
        }
    }
}
