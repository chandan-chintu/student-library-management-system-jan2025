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

    public String updateStudent(int id, StudentRequestDto studentRequestDto){
        Student student = getStudentById(id);
        if(student!=null){
            student.setName(studentRequestDto.getName());
            student.setDepartment(studentRequestDto.getDepartment());
            student.setDob(studentRequestDto.getDob());
            student.setEmail(studentRequestDto.getEmail());
            student.setMobile(studentRequestDto.getMobile());
            student.setGender(studentRequestDto.getGender());
            student.setSemester(studentRequestDto.getSemester());

            studentRepository.save(student);
            return "student updated";
        }else{
            return "Student not found, cannot update";
        }
    }

    public String updateStudentSemester(int id, String semester){
        Student student = getStudentById(id);
        if(student!=null){
            student.setSemester(semester);
            studentRepository.save(student);
            return "Student semester updated";
        }else{
            return "Student not found, cannot update";
        }
    }

    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "student with id "+id+" deleted successfully";
    }
}
