package spg.pos.predjeskovic.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spg.pos.predjeskovic.domain.OptionalTopic;
import spg.pos.predjeskovic.domain.OptionalTopicLesson;
import spg.pos.predjeskovic.domain.SchoolYear;
import spg.pos.predjeskovic.domain.TopicType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OptionalTopicRepositoryTest {

    @Autowired
    private OptionalTopicRepository optionalTopicRepository;

    /**
     * UC001 Create an optiona topic lesson
     *
     * Create the optional topic "Historischer Schwertkampf " for school year 2021/2022 as"Freigegenstand".
     * */
    @Test
    void uc001AssertSaveOptionalTopic(){

        //Create the optional topic "Historischer Schwertkampf " for school year 2021/2022 as"Freigegenstand".
        OptionalTopic optionalTopic =
                OptionalTopic.builder().topicId(UUID.randomUUID().toString())
                        .name("Historischer Schwertkampf")
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

        assertThat(optionalTopicRepository.save(optionalTopic)).isNotNull();
    }




}
