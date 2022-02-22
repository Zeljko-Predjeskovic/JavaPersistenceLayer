package spg.pos.predjeskovic.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import spg.pos.predjeskovic.domain.Student;

import java.util.Optional;


@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {

    Optional<Student> findById(Long id);
}
