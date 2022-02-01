package spg.pos.predjeskovic.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Entity
public class SchoolYear extends CustomAbstractPersistable{

    @Getter @Setter
    private String name;

    @Getter @Setter
    private LocalDate firstTermStart;

    @Getter @Setter
    private LocalDate firstTermEnd;

    @Getter @Setter
    private LocalDate secondTermStart;

    @Getter @Setter
    private LocalDate secondTermEnd;

    @Getter
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OptionalTopic> optionalTopicList;

    @Getter
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<SchoolClassInYear> schoolClassInYearList;

    public void addOptionalTopic(OptionalTopic optionalTopic){
        if(optionalTopicList==null){
          optionalTopicList = new ArrayList<OptionalTopic>();
        }
        optionalTopicList.add(optionalTopic);
    }

    public void addSchoolClassInYear(SchoolClassInYear schoolClassInYear){
        if(schoolClassInYearList == null){
            schoolClassInYearList = new ArrayList<SchoolClassInYear>();
        }
        schoolClassInYearList.add(schoolClassInYear);
    }

}
