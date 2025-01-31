package com.demo.example.student_library_management_system.service;

import com.demo.example.student_library_management_system.converter.StudentConverter;
import com.demo.example.student_library_management_system.enums.CardStatus;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.repository.StudentRepository;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(StudentRequestDto studentRequestDto){

        // first convert studentrequestdto into student object
        Student student = StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);

        //whenever the student are added card also issued/added at same time
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);

        student.setCard(card);
        card.setStudent(student);

        studentRepository.save(student);
        return "Student and card saved successfully";
    }

    public Student getStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }

    public List<Student> getAllStudents(){
        List<Student> studentList=studentRepository.findAll();
        return studentList;
    }
}
