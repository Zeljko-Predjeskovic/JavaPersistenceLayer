package spg.pos.predjeskovic.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spg.pos.predjeskovic.domain.Student;
import spg.pos.predjeskovic.persistence.StudentRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> findAllStudents(Pageable pageable){
       return studentRepository.findAll(pageable).stream()
                .map(f->new StudentDto(f.getId(),f.getStudentId(),f.getFirstName(),f.getLastName()))
                .collect(Collectors.toList());
    }

    public int getPages(int maxSize){
        return  studentRepository.findAll(Pageable.ofSize(maxSize)).getTotalPages();
    }

    public void save(StudentDto studentDto){
        var student = Student.builder()
                .studentId(studentDto.getStudentId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName()).build();


        studentRepository.save(student);
    }

    public StudentDto findById(Long id){
        var student = studentRepository.findById(id).get();
        return new StudentDto(student.getId(),student.getStudentId(),student.getFirstName(),student.getLastName());
    }

    public StudentDto replace(StudentDto studentDto){
        return Optional.ofNullable(studentDto)
                .flatMap(it -> studentRepository.findById(it.getId()))
                .map(student -> studentDto.mergeWith(student))
                .map(studentRepository :: save)
                .map(it -> new StudentDto(it.getId(),it.getStudentId(),it.getFirstName(),it.getLastName()))
                .orElse(null);
    }
}
