package spg.pos.predjeskovic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spg.pos.predjeskovic.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

}
