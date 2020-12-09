package com.alamin.springbootdemo.service;

import com.alamin.springbootdemo.dao.StudentRepository;
import com.alamin.springbootdemo.exception.ResourceAlreadyExistException;
import com.alamin.springbootdemo.exception.ResourceNotFoundException;
import com.alamin.springbootdemo.model.Student;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> allStudents(){
        List<Student> studentList=new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
       // List<Student> studentList= (List<Student>) studentRepository.findAll();
        return studentList;
    }

    public Student findById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Student student=studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return student;

    }

    public Student createStudent(@RequestBody Student student) throws ResourceAlreadyExistException {
        if (studentRepository.existsById(student.getId())){
            throw  new ResourceAlreadyExistException();
        }
        else {
            Student savedStudent=studentRepository.save(student);
            return savedStudent;
        }

    }

    public Student updateStudent(@RequestBody Student student)throws ResourceNotFoundException {
        if (studentRepository.existsById(student.getId())){
            Student updatedStudent=studentRepository.save(student);
            return updatedStudent;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    public Student deleteById(@PathVariable("id") long id)throws ResourceNotFoundException{
        if (studentRepository.existsById(id)){
            Student student=studentRepository.findById(id).get();
            studentRepository.deleteById(id);
            return student;
        }
        else {
            throw  new ResourceNotFoundException();
        }

    }
}
