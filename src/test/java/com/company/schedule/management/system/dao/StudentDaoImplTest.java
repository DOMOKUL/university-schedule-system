package com.company.schedule.management.system.dao;

import com.company.schedule.management.system.dao.exception.DaoException;
import com.company.schedule.management.system.dao.impl.StudentDaoImpl;
import com.company.schedule.management.system.model.Faculty;
import com.company.schedule.management.system.model.Group;
import com.company.schedule.management.system.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class StudentDaoImplTest extends BaseIntegrationTest {

    private static final Faculty TEST_FACULTY = new Faculty(10L, "IKBSP", null, null);
    private static final Group TEST_GROUP = new Group(10L, "BSBO-04-20", TEST_FACULTY, null, null);
    private static final Student TEST_STUDENT = new Student(10L, 10, TEST_GROUP);

    @Autowired
    private StudentDaoImpl studentDao;

    @Test
    void create_shouldReturnCorrectStudent_whenInputCorrectData() {
        Student expected = new Student(1L, 1, TEST_GROUP);
        Student actual = studentDao.create(new Student(1, TEST_GROUP));
        assertEquals(expected, actual);
    }

    @Test
    void create_shouldThrowException_whenInputExistId() {
        assertThrows(InvalidDataAccessApiUsageException.class, () ->
                studentDao.create(TEST_STUDENT));
    }

    @Test
    void findById_shouldReturnCorrectStudent_whenInputExistId() {
        assertEquals(TEST_STUDENT, studentDao.findById(10L).get());
    }

    @Test
    void findById_shouldThrowDaoException_whenInputNonExistentStudentId() {
        assertEquals(Optional.empty(), studentDao.findById(10000L));
    }

    @Test
    void findAll_shouldReturnStudentsList() {
        List<Student> students = studentDao.findAll();
        assertFalse(students.isEmpty());
    }

    @Test
    void update_shouldUpdateStudent_whenInputExistId() {
        Student actual = studentDao.update(TEST_STUDENT);
        assertEquals(TEST_STUDENT, actual);
    }

    @Test
    void delete_shouldDeleteStudent_whenInputExistId() {
        boolean actual = studentDao.deleteById(10L);
        assertTrue(actual);
    }

    @Test
    void delete_shouldThrowDaoException_whenInputNotExistStudentId() {
        assertThrows(DaoException.class, () ->
                studentDao.deleteById(100L));
    }
}