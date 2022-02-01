package spg.pos.predjeskovic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Entity
public class SchoolClassInYear extends CustomAbstractPersistable{

    @Getter @Setter
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "school_year_id")
    private SchoolYear schoolYear;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Student> students;
}
