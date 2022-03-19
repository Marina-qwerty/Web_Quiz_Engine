package engine.service;

import engine.businesslayer.Quiz;
import engine.repository.QuizzesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class QuizService {

    @Resource
    private QuizzesRepository quizzesRepository;

    public Optional<Quiz> findQuizById(Long id) {
        return quizzesRepository.findById(id);
    }

    public Quiz save(Quiz toSave) {
        return quizzesRepository.save(toSave);
    }

    public Iterable<Quiz> findAll() {
        return quizzesRepository.findAll();
    }

    public Page<Quiz> findAllQuizzesWithPagination(Pageable pageable) {
        return quizzesRepository.findAllQuizzesWithPagination(pageable);
    }

    public void deleteQuiz(Long id) {
        quizzesRepository.deleteById(id);
    }
}

