package engine.repository;

import engine.businesslayer.PassedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassedQuizRepository extends PagingAndSortingRepository<PassedQuiz, Long> {

    @Query(value = "SELECT * FROM passed_quizzes WHERE username = ?1", nativeQuery = true)
    Page<PassedQuiz> findAllPassedQuizzes(String username, Pageable pageable);

}
