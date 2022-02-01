package spg.pos.predjeskovic.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spg.pos.predjeskovic.domain.SchoolClassInYear;
import spg.pos.predjeskovic.domain.SchoolYear;
import spg.pos.predjeskovic.domain.Student;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SchoolClassInYearRepositoryTest {

    @Autowired
    private SchoolClassInYearRepository schoolClassInYearRepository;


    /**
     * UC002 Create a class with students
     *
     * Create a school class "4AHIF" in school year 2021/2022 with five students.
     *
     * */
    @Test
    void uc002AssertSaveSchoolClassWithStudents(){

        // Create a school class "4AHIF" in school year 2021/2022 with five students.
        SchoolClassInYear schoolClass = SchoolClassInYear.builder()
                .name("4AHIF")
                .schoolYear(SchoolYear.builder()
                        .firstTermStart(LocalDate.of(2021,9,1))
                        .firstTermEnd(LocalDate.of(2022,1,31))
                        .secondTermStart(LocalDate.of(2022,02,6))
                        .secondTermEnd(LocalDate.of(2022,6,30))
                        .build())
                .students(List.of(
                        Student.builder().build(),
                        Student.builder().build(),
                        Student.builder().build(),
                        Student.builder().build(),
                        Student.builder().build()
                ))
                .build();

        assertThat(schoolClassInYearRepository.save(schoolClass)).isNotNull();

    }

}
