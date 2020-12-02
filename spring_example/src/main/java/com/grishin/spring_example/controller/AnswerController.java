package com.grishin.spring_example.controller;

import com.grishin.spring_example.exception.ResourceNotFoundException;
import com.grishin.spring_example.models.Answer;
import com.grishin.spring_example.repository.AnswerRepository;
import com.grishin.spring_example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController{
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswerByQuestionId(@PathVariable Long questionId){
        return answerRepository.findByQuestionId(questionId);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(@PathVariable Long questionId, @Valid @RequestBody Answer answer){
        return questionRepository.findById(questionId)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerRepository.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with ID" + questionId));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable Long questionId,
                               @PathVariable Long answerId,
                               @Valid @RequestBody Answer answerRequest) {
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with ID" + questionId);
        }

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answer.setText(answerRequest.getText());
                    return answerRepository.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID" + answerId));
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,
                                          @PathVariable Long answerId){
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with ID" + questionId);
        }

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with ID" + answerId));
    }
}
