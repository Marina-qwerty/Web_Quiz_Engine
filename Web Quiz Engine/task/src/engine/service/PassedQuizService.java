package engine.service;

import engine.businesslayer.PassedQuiz;
import engine.repository.PassedQuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class PassedQuizService {

    @Resource
    private PassedQuizRepository passedQuizRepository;

    public Page<PassedQuiz> findAllPassedQuizzes(String username, Pageable pageable) {
        return passedQuizRepository.findAllPassedQuizzes(username, pageable);
    }

    public PassedQuiz save(PassedQuiz passedQuiz) {
        return passedQuizRepository.save(passedQuiz);
    }
}
