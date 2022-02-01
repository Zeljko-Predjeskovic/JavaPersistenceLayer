package spg.pos.predjeskovic.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spg.pos.predjeskovic.domain.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OptionalTopicSchoolClassRepositoriesTest {

    @Autowired
    private OptionalTopicRepository optionalTopicRepository;

    @Autowired
    private SchoolClassInYearRepository schoolClassInYearRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * UC003 Create an optional topic lesson and enroll students
     *
     * Create the optional topic "Projekte mir Raspberry Pi" for school year 2021/2022 as "Unverbindliche Übung".
     *
     * Create a school class "3CHIF" in school year 2021/20221 with five students.
     *
     * Let two of the students be enrolled to the optional topic.
     * */
    @Test
    void uc003AssertSaveOptionalTopicAndSaveSchoolClassAndEnrollStudents(){

        // Create the optional topic "Projekte mir Raspberry Pi" for school year 2021/2022 as "Unverbindliche Übung".
        OptionalTopic optionalTopic = OptionalTopic.builder()
                .name("Projekte mir Raspberry Pi")
                .optionalTopicLessonList(List.of(OptionalTopicLesson.builder()
                        .topicType(TopicType.UNVERBINDLICHE_UEBNUNG)
                        .schoolYear(SchoolYear.builder()
                                .firstTermStart(LocalDate.of(2021,9,1))
                                .firstTermEnd(LocalDate.of(2022,1,31))
                                .secondTermStart(LocalDate.of(2022,02,6))
                                .secondTermEnd(LocalDate.of(2022,6,30))
                                .build())

                        .build()))
                .build();

        var addedOptionalTopic = optionalTopicRepository.save(optionalTopic);
        assertThat(addedOptionalTopic).isNotNull();

        // Create a school class "3CHIF" in school year 2021/20221 with five students.
        SchoolClassInYear schoolClass = SchoolClassInYear.builder()
                .name("3AHIF")
                .schoolYear(SchoolYear.builder()
                        .firstTermStart(LocalDate.of(2021,9,1))
                        .firstTermEnd(LocalDate.of(2022,1,31))
                        .secondTermStart(LocalDate.of(2022,02,6))
                        .secondTermEnd(LocalDate.of(2022,6,30))
                        .build())
                .students(List.of(
                        Student.builder().firstName("Tom").build(),
                        Student.builder().firstName("Angela").build(),
                        Student.builder().build(),
                        Student.builder().build(),
                        Student.builder().build()
                ))
                .build();

        var addedSchoolClass = schoolClassInYearRepository.save(schoolClass);
        assertThat(addedSchoolClass).isNotNull();

        //Let two of the students be enrolled to the optional topic.
        var student1 = addedSchoolClass.getStudents().get(0);
        var student2 = addedSchoolClass.getStudents().get(1);

        student1.addEnrollment(Enrollment.builder()
                        .optionalTopicLesson(addedOptionalTopic.getOptionalTopicLessonList().stream().findFirst().get())
                .build());

        student2.addEnrollment(Enrollment.builder()
                .optionalTopicLesson(addedOptionalTopic.getOptionalTopicLessonList().stream().findFirst().get())
                .build());

        var addedEnrollmentS1 = studentRepository.save(student1);
        var addedEnrollmentS2 = studentRepository.save(student2);

        assertThat(addedEnrollmentS1.getEnrollmentList()).isNotNull().isNotNull();

        assertThat(addedEnrollmentS2.getEnrollmentList()).isNotNull().isNotNull();

    }


    /**
     * UC004 Create an optional topic lesson and assign students
     *
     * Create the optional topic "Microsoft Certification" for school year 2021/2022 as "Freigegenstand".
     *
     * Create a school class "2EHIF" in school year 2021/2022 with five students.Let three of the students be enrolled to the optional topic.
     *
     * Let two (of those) two students be assigned to the optional topic.
     *
     * */
    @Test
    void uc004AssertSaveOptionalTopicAndSaveSchoolClassAndEnrollAndAssignStudents(){

        //Create the optional topic "Microsoft Certification" for school year 2021/2022 as "Freigegenstand".
        OptionalTopic optionalTopic = OptionalTopic.builder()
                .name("Microsoft Certification")
                .optionalTopicLessonList(List.of(OptionalTopicLesson.builder()
                        .topicType(TopicType.FREIGEGENSTAND)
                        .schoolYear(SchoolYear.builder()
                                .firstTermStart(LocalDate.of(2021,9,1))
                                .firstTermEnd(LocalDate.of(2022,1,31))
                                .secondTermStart(LocalDate.of(2022,02,6))
                                .secondTermEnd(LocalDate.of(2022,6,30))
                                .build())

                        .build()))
                .build();

        var addedOptionalTopic = optionalTopicRepository.save(optionalTopic);
        assertThat(addedOptionalTopic).isNotNull();

        // Create a school class "2EHIF" in school year 2021/2022 with five students.Let three of the students be enrolled to the optional topic.
        SchoolClassInYear schoolClass = SchoolClassInYear.builder()
                .name("2EHIF")
                .schoolYear(SchoolYear.builder()
                        .firstTermStart(LocalDate.of(2021,9,1))
                        .firstTermEnd(LocalDate.of(2022,1,31))
                        .secondTermStart(LocalDate.of(2022,02,6))
                        .secondTermEnd(LocalDate.of(2022,6,30))
                        .build())
                .students(List.of(
                        Student.builder().firstName("Tom").build(),
                        Student.builder().firstName("Angela").build(),
                        Student.builder().firstName("Kevin").build(),
                        Student.builder().build(),
                        Student.builder().build()
                ))
                .build();

        var addedSchoolClass = schoolClassInYearRepository.save(schoolClass);
        assertThat(addedSchoolClass).isNotNull();

        var student1 = addedSchoolClass.getStudents().get(0);
        var student2 = addedSchoolClass.getStudents().get(1);
        var student3 = addedSchoolClass.getStudents().get(3);

        student1.addEnrollment(Enrollment.builder()
                .optionalTopicLesson(addedOptionalTopic.getOptionalTopicLessonList().stream().findFirst().get())
                .build());

        student2.addEnrollment(Enrollment.builder()
                .optionalTopicLesson(addedOptionalTopic.getOptionalTopicLessonList().stream().findFirst().get())
                .build());

        student3.addEnrollment(Enrollment.builder()
                .optionalTopicLesson(addedOptionalTopic.getOptionalTopicLessonList().stream().findFirst().get())
                .build());


        var addedEnrollmentS1 = studentRepository.save(student1);
        var addedEnrollmentS2 = studentRepository.save(student2);
        var addedEnrollmentS3 = studentRepository.save(student3);

        assertThat(addedEnrollmentS1.getEnrollmentList()).isNotNull().isNotNull();

        assertThat(addedEnrollmentS2.getEnrollmentList()).isNotNull().isNotNull();

        assertThat(addedEnrollmentS3.getEnrollmentList()).isNotNull().isNotNull();

        //Let two (of those) two students be assigned to the optional topic.
        Assignment assignment = new Assignment();
        assignment.setStudent(student1);

        addedOptionalTopic.getOptionalTopicLessonList().stream().findFirst().get()
                .addAssignment(assignment);


        assignment.setStudent(student2);

        addedOptionalTopic.getOptionalTopicLessonList().stream().findFirst().get()
                .addAssignment(assignment);

        var addedOptionalTopicWithAssignment = optionalTopicRepository.save(addedOptionalTopic);


        assertThat(addedOptionalTopicWithAssignment.getOptionalTopicLessonList().stream()
                .anyMatch(a -> !a.getAssignmentList().isEmpty()==false));
    }


}
