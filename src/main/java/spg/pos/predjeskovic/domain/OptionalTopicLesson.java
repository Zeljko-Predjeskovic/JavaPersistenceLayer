package spg.pos.predjeskovic.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Entity
public class OptionalTopicLesson extends CustomAbstractPersistable{


    @Getter @Setter
    private LocalDate start;

    @Getter @Setter
    private LocalDate end;

    @Getter @Setter
    private String note;

    @Enumerated(EnumType.STRING)
    private TopicType topicType;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "optional_topic_id")
    private OptionalTopic optionalTopic;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "school_year_id")
    private SchoolYear schoolYear;

    @Getter
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Enrollment> enrollmentList;

    @Getter
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Assignment> assignmentList;

    public void addEnrollment(Enrollment enrollment){
        if(enrollmentList==null){
            enrollmentList = new ArrayList<Enrollment>();
        }
        enrollmentList.add(enrollment);
    }

    public void addAssignment(Assignment assignment){
        if(assignmentList==null){
            assignmentList = new ArrayList<Assignment>();
        }
        assignmentList.add(assignment);
    }

}
