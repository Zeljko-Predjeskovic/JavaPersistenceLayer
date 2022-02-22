package spg.pos.predjeskovic.domain;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Student extends CustomAbstractPersistable{

    @Getter @Setter
    private String studentId;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "school_class_in_year_id")
    private SchoolClassInYear schoolClassInYear;

    @Getter
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Assignment> assignmentList;

    @Getter
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Enrollment> enrollmentList;

    public void addAssignment(Assignment assignment){
        if(assignmentList==null){
            assignmentList = new ArrayList<Assignment>();
        }
        assignmentList.add(assignment);
    }

    public void addEnrollment(Enrollment enrollment){
        if(enrollmentList==null){
            enrollmentList = new ArrayList<Enrollment>();
        }
        enrollmentList.add(enrollment);
    }

}
