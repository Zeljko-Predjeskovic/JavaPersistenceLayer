package spg.pos.predjeskovic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spg.pos.predjeskovic.domain.OptionalTopic;

@Repository
public interface OptionalTopicRepository extends CrudRepository<OptionalTopic,Long> {


}
