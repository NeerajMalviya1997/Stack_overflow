package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Service.UserServiceImpl;
import com.masai.model.Questions;
import com.masai.model.Tags;
import com.masai.model.User;
import com.masai.model.UserDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/create")
	public  User  saveUser(@RequestBody User er) {
		User user= userServiceImpl.createUser(er);
		return user;
		
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user, @RequestParam(required = false) String key) {

		return userServiceImpl.updateUser(user, key);
	}
	
	@PostMapping("/login")
	public String logInUser(@RequestBody UserDto userDTO) throws Exception {
		return userServiceImpl.logIntoAccount(userDTO);
	}
	
	@PatchMapping("/logout")
	public String logOutUser(@RequestParam(required = false) String key) {
		return userServiceImpl.logOutAccount(key);
	}
	
	@PostMapping("/addTags")
	public String addtags(@RequestBody Tags tag,@RequestParam String key) {
		return userServiceImpl.addTags(tag, key);
	}
	
	
	@PostMapping("/createQuestions")
	public String addtags(@RequestBody Questions questions,@RequestParam String key) {
		return userServiceImpl.createQuestions(questions, key);
	}
	
	
	@PostMapping("/updateQuestions/{id}")
	public String updateQuestions(@RequestBody Questions questions,@RequestParam String key,@PathVariable("id") int id) {
		return userServiceImpl.updateQuestion(questions, key, id);
	}
	
	@PatchMapping("/addtag/{id}")
	public String addtags(@RequestBody Questions questions,@RequestParam String key,@PathVariable("id") int id) {
		return userServiceImpl.addTagsToQuestion(questions, key, id);
	}
	@GetMapping("getQuestion/{tagname}")
	public String getquestion(@RequestParam String key,@PathVariable("tagname") String id) {
		return userServiceImpl.getQuestionBytagname(key, id);
	}
}
