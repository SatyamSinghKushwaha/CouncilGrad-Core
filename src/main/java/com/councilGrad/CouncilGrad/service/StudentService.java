package com.councilGrad.CouncilGrad.service;

import com.councilGrad.CouncilGrad.model.Student;
import com.councilGrad.CouncilGrad.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(s -> {
                    s.setName(updatedStudent.getName());
                    s.setTenthMarks(updatedStudent.getTenthMarks());
                    s.setTwelfthMarks(updatedStudent.getTwelfthMarks());
                    s.setBudget(updatedStudent.getBudget());
                    s.setDesiredCourse(updatedStudent.getDesiredCourse());
                    return studentRepository.save(s);
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

