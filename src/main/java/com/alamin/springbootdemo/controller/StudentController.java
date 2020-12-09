package com.alamin.springbootdemo.controller;

import com.alamin.springbootdemo.dao.StudentRepository;
import com.alamin.springbootdemo.exception.ResourceAlreadyExistException;
import com.alamin.springbootdemo.exception.ResourceNotFoundException;
import com.alamin.springbootdemo.model.Student;
import com.alamin.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> studentList=studentService.allStudents();
        return ResponseEntity.ok(studentList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getSingleStudents(@PathVariable("id") long id){
        try {
            Student student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        try {
            Student createdStudent=studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @PutMapping("")
    public  ResponseEntity<Student> updateStudent(@RequestBody Student student){
        try {
            Student updateStudent=studentService.updateStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateStudent);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id){
        try {
            Student deletedStudent = studentService.deleteById(id);
            return ResponseEntity.ok(deletedStudent);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
