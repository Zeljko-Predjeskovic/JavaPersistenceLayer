package spg.pos.predjeskovic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spg.pos.predjeskovic.domain.SchoolClassInYear;

@Repository
public interface SchoolClassInYearRepository extends CrudRepository<SchoolClassInYear,Long> {

}
