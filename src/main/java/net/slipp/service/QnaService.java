package net.slipp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;

@Service("qnaService")
public class QnaService {
	private static final Logger log = LoggerFactory.getLogger(QnaService.class);
	
	@Resource(name = "questionRepository")
	private QuestionRepository questionRepository;

	public void create(User loginUser, Question question) {
		question.writeBy(loginUser);
		log.debug("question : {}", question);
		questionRepository.save(question);
	}

	public Question findById(long id) {
		return questionRepository.findOne(id);
	}

	public Question update(User loginUser, long id, Question updatedQuestion) {
		Question question = questionRepository.findOne(id);
		question.update(loginUser, updatedQuestion);
		return questionRepository.save(question);
	}
}
