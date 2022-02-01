package spg.pos.predjeskovic.domain;

import jdk.jfr.Frequency;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Enrollment extends CustomAbstractPersistable{

    @Getter @Setter
    private LocalDateTime when;

    @Getter @Setter
    private LocalDateTime cancelled;

    @Getter @Setter
    private String note;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "student_id")
    private Student student;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "optional_topic_lesson_id")
    private OptionalTopicLesson optionalTopicLesson;
}
