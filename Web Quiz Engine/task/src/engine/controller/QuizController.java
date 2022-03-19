package engine.controller;


import engine.businesslayer.*;
import engine.service.PassedQuizService;
import engine.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins="*")
public class QuizController {

    @Resource
    QuizService quizService;

    @Resource
    PassedQuizService passedQuizService;

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<Object> getQuizByID(@PathVariable long id) {
        Optional<Quiz> quiz = quizService.findQuizById(id);
        if (quiz.isPresent()) {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/api/quizzes")
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user){
        quiz.setUser(user);
        return new ResponseEntity<>(quizService.save(quiz), HttpStatus.OK);
    }

    public Page<Quiz> getAllQuizzesWithPagination(int page, int pageSize) {
        return quizService.findAllQuizzesWithPagination(PageRequest.of(page, pageSize));
    }

    @GetMapping("/api/quizzes")
    public Page<Quiz> getAllQuizzes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return getAllQuizzesWithPagination(page, pageSize);

    }

    @GetMapping("/api/quizzes/completed")
    public Page<PassedQuiz> getAllPassedQuizzes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @AuthenticationPrincipal User user) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("timestamp").descending());
        return passedQuizService.findAllPassedQuizzes(user.getUsername(), pageable);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity<ResponseAnswer> solveQuiz(@PathVariable long id, @RequestBody UserAnswer answer, @AuthenticationPrincipal User user) {
        Optional<Quiz> quiz = quizService.findQuizById(id);
        if (quiz.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (answer.isAnswerCorrect(quiz.get().getAnswer())) {
           PassedQuiz passedQuiz = new PassedQuiz(id, user.getUsername(), System.currentTimeMillis());
           passedQuizService.save(passedQuiz);
           return new ResponseEntity<>(new ResponseAnswer(true, ResponseAnswer.SUCCESS_MESSAGE), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseAnswer(false, ResponseAnswer.WRONG_MESSAGE), HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id, @AuthenticationPrincipal User user) {
        Optional<Quiz> quiz = quizService.findQuizById(id);
        if (quiz.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (quiz.get().getUser().getUsername().equals(user.getUsername())) {
            quizService.deleteQuiz(id);
        } else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
