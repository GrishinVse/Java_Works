package com.spring_work.grishin.controller;

import com.spring_work.grishin.exception.*;
import com.spring_work.grishin.repository.*;
import com.spring_work.grishin.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    @GetMapping
    public Page<Question> getAllQuestions(Pageable pageable){
        return questionRepository.findAll(pageable);
    }

    @GetMapping("{questionId}")
    public Question getQuestionById(@PathVariable Long questionId){
        return questionRepository.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question with ID = " + questionId + " not found!"));
    }

    @PostMapping
    public Question createQuestion(@Valid @RequestBody Question question){
        Question curr = questionRepository.save(question);
        if (curr.getAnswerList() != null){
            curr.getAnswerList().forEach(answer -> answer.setQuestion(question));
        }
        if (curr.getTest() != null) {
            curr.getTest().setTest_id(question.getId());
        }
        return questionRepository.save(curr);
    }

    @PutMapping("{questionId}")
    public Question updateQuestion(@PathVariable Long questionId, @Valid @RequestBody Question questionRequest, Pageable pageable){
        return questionRepository.findById(questionId).map(question -> {
                    question.setActive(question.getActive());
                    question.setText(question.getText());
                    question.setScore(question.getScore());

                    List<Answer> currList = question.getAnswerList();
                    currList.forEach(answer -> answerRepository.delete(answer));
                    question.setAnswerList(questionRequest.getAnswerList());

                    if (questionRequest.getTest().getTest_id() == null)
                        throw new ParamNotSpecifiedException("Specify test_id first !");
                    question.setTest(questionRequest.getTest());

                    Question curr = questionRepository.save(question);
                    curr.getAnswerList().forEach(answer -> answer.setQuestion_id(question.getId()));
                    curr.getTest().setQuestion_id(question.getId());

                    return questionRepository.save(curr);
                }).orElseThrow(() -> new ResourceNotFoundException("Question with ID = " + questionId + " not found!"));
    }

    @DeleteMapping("{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId){
        return questionRepository.findById(questionId).map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question with ID = " + questionId + " not found!"));
    }
}
