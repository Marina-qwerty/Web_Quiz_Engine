package engine.repository;

import engine.businesslayer.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface QuizzesRepository extends PagingAndSortingRepository<Quiz, Long> {

    @Override
    Optional<Quiz> findById(Long aLong);

    @Query(value = "SELECT * FROM quizzes", nativeQuery = true)
    Page<Quiz> findAllQuizzesWithPagination(Pageable pageable);
}
