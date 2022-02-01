package spg.pos.predjeskovic.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spg.pos.predjeskovic.domain.OptionalTopic;
import spg.pos.predjeskovic.domain.OptionalTopicLesson;
import spg.pos.predjeskovic.domain.TopicType;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OptionalTopicRepositoryTest {

    @Autowired
    private OptionalTopicRepository optionalTopicRepository;

    @Test
    void assertSaveOptionalTopic(){
        OptionalTopic optionalTopic =
                OptionalTopic.builder().topicId(UUID.randomUUID().toString())
                        .name("Historischer Schwertkampf")
                        .optionalTopicLessonList(List.of(OptionalTopicLesson.builder()
                                .topicType(TopicType.FREIGEGENSTAND).build()))
                        .build();

        assertThat(optionalTopicRepository.save(optionalTopic)).isNotNull();


    }


}
