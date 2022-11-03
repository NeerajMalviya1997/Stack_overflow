package com.masai.Service;



import com.masai.model.Questions;
import com.masai.model.Tags;
import com.masai.model.User;
import com.masai.model.UserDto;

public interface UserService {

	public User createUser(User user);
	
	public User updateUser(User user, String key);
	
	
   public String logIntoAccount(UserDto userDTO) throws Exception;
	
	public String logOutAccount(String key);
	
	public String addTags(Tags tag,String key);
	
	public String createQuestions(Questions questions,String key);
	
	public String updateQuestion(Questions questions,String key,int questionId);
	public String addTagsToQuestion(Questions questions,String key,int tagId);
	
	public String getQuestionBytagname(String key,String tagname);
}
