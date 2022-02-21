package spg.pos.predjeskovic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import spg.pos.predjeskovic.domain.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long>, PagingAndSortingRepository<Student,Long> {

}
