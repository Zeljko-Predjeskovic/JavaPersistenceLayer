package spg.pos.predjeskovic;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spg.pos.predjeskovic.domain.SchoolClassInYear;
import spg.pos.predjeskovic.domain.SchoolYear;
import spg.pos.predjeskovic.domain.Student;
import spg.pos.predjeskovic.persistence.StudentRepository;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository) {
        return args -> {




            for (int i = 1; i < 202; i++) {
                Student student = Student.builder()
                        .firstName("Firstname" + i)
                        .lastName("Lastname" + 1)
                        .studentId("" + (7382 / i))
                        .schoolClassInYear(SchoolClassInYear.builder()
                                .name("2021/2022")
                                .build())

                        .build();
                studentRepository.save(student);
            }
            System.out.printf("Geaddet");
        };
    }
}
