package spg.pos.predjeskovic.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spg.pos.predjeskovic.persistence.StudentRepository;


import java.util.List;
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
                .map(f->new StudentDto(f.getStudentId(),f.getFirstName(),f.getLastName()))
                .collect(Collectors.toList());
    }
}