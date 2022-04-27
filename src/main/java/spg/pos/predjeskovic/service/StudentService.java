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
       return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(StudentDto::fromStudent)
                .collect(Collectors.toList());
    }

    public int getPages(int maxSize){
        return  studentRepository.findAll(Pageable.ofSize(maxSize)).getTotalPages();
    }

    public StudentDto save(StudentDto studentDto){
        var student = Optional.ofNullable(studentDto)
                .map(StudentDto::toStudent)
                .map(studentRepository::save)
                .map(StudentDto::fromStudent)
                .orElseGet(null);
        return student;
    }

    public StudentDto findById(Long id){
        return studentRepository.findById(id)
                .map(StudentDto::fromStudent)
                .orElse(null);
    }

    public StudentDto replace(StudentDto studentDto){
        return Optional.ofNullable(studentDto.getId())
                .flatMap(it -> studentRepository.findById(it))
                .map(student -> studentDto.mergeWith(student))
                .map(studentRepository::save)
                .map(StudentDto::fromStudent)
                .orElse(null);
    }

    public void remove(Long id){
        studentRepository.deleteById(id);
    }
}
