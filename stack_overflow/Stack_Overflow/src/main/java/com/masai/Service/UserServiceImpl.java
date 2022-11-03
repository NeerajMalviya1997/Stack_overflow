package com.masai.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.CurrentUserSession;
import com.masai.model.Questions;
import com.masai.model.Tags;
import com.masai.model.User;
import com.masai.model.UserDto;

import net.bytebuddy.utility.RandomString;

import com.masai.Repo.QuestionsDao;
import com.masai.Repo.TagsDao;
import com.masai.Repo.UserDao;
import com.masai.Repo.UserSessionDAO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	
	@Autowired
	private TagsDao tagsDao;
	
	@Autowired
	private QuestionsDao questionsDao;
	
	@Override
	public User createUser(User user) {
		
		
		Optional<User> opt= userDao.findByMobileNo(user.getMobileNo());
		
		if(opt.isPresent()) {

			throw new RuntimeException("User already exist");
		}
		
		userDao.save(user);
		return user;
		
	}

	@Override
	public User updateUser(User user, String key) {

		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
		
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
			return userDao.save(user);

	}
	@Override
	public String logIntoAccount(UserDto userDTO) throws Exception {

		Optional<User> opt= userDao.findByMobileNo(userDTO.getMobileNo());
		
		if(!opt.isPresent()) {
			return "Please enter valid Mobile number!";
		}
		
		User user1= opt.get();
		Integer userId = user1.getId();
		Optional<CurrentUserSession>  currUseropt1= userSessionDAO.findByUserId(userId);
		
		if(currUseropt1.isPresent()) {
			return "User already logged in with this number.";
		}
		
		if(user1.getPassword().equals(userDTO.getPassword())) {
			
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession(userId, key, LocalDateTime.now());
			
			userSessionDAO.save(currentUserSession);

			return currentUserSession.toString();
		}
		else {
			return "Please Enter valid password.";
		}

		
	}
	

	@Override
	public String logOutAccount(String key) {
		Optional<CurrentUserSession> currUserOpt=userSessionDAO.findByUuid(key);
		
		if(currUserOpt.isPresent()) {
			CurrentUserSession currUser1=currUserOpt.get();
			
			userSessionDAO.delete(currUser1);
			return "User logged out successfully.";
		}
		return "User does not exist, Enter correct uuid";

	}

	@Override
	public String addTags(Tags tag, String key) {
      Optional<CurrentUserSession> currUserOpt=userSessionDAO.findByUuid(key);
		
		if(!currUserOpt.isPresent()) {
			
			return "Please login First";
		}
		return tagsDao.save(tag).toString();
	}

	@Override
	public String createQuestions(Questions questions, String key) {
     Optional<CurrentUserSession> currUserOpt=userSessionDAO.findByUuid(key);
		
		if(!currUserOpt.isPresent()) {
			
			return "Please login First";
		}
		
		return questionsDao.save(questions).toString();
	
	}

	@Override
	public String updateQuestion(Questions questions, String key, int questionId) {
		 Optional<CurrentUserSession> currUserOpt=userSessionDAO.findByUuid(key);
			
			if(!currUserOpt.isPresent()) {
				
				return "Please login First";
			}
			
			Optional<Questions> q=questionsDao.findById(questionId);
			
			if(!q.isPresent()) {
				return "question id is wrong";
			}
			
			questions.setQuestionid(questionId);
			return questionsDao.save(questions).toString();
		
			
		
	}

	@Override
	public String addTagsToQuestion(Questions questions, String key, int tagId) {
		 Optional<CurrentUserSession> currUserOpt=userSessionDAO.findByUuid(key);
			
			if(!currUserOpt.isPresent()) {
				
				return "Please login First";
			}
			
			List<Tags> lt=new ArrayList<>();
		Optional<Tags>tag=tagsDao.findById(tagId);
		if(!tag.isPresent()) {
			return "tag id is wrong";
		}
		lt.add(tag.get());
		questions.setTags(lt);
		return questionsDao.save(questions).toString();
		
	}

	@Override
	public String getQuestionBytagname(String key, String tagname) {
		 Optional<CurrentUserSession> currUserOpt=userSessionDAO.findByUuid(key);
			
			if(!currUserOpt.isPresent()) {
				
				return "Please login First";
			}
			return tagsDao.findByTagName(tagname).get().getQuestions().toString();
		
	}

	

}
