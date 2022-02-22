package spg.pos.predjeskovic.service;

import lombok.*;
import spg.pos.predjeskovic.domain.Student;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;

    public Student mergeWith(Student student){
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setStudentId(studentId);

        return student;
    }
}
