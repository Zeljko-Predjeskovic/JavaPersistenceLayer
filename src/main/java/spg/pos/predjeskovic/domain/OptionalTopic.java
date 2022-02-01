package spg.pos.predjeskovic.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity //@Table(indexes = @Index(columnList = "name"))
public class OptionalTopic extends CustomAbstractPersistable{

    @Getter
    private String topicId;

    @Getter @Setter
    private String name;

    @Getter
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OptionalTopicLesson> optionalTopicLessonList;

    public void addOptionalTopicLesson(OptionalTopicLesson optionalTopicLesson){
        if(optionalTopicLessonList==null){
            optionalTopicLessonList = new ArrayList<OptionalTopicLesson>();
        }
        optionalTopicLessonList.add(optionalTopicLesson);
    }

}
