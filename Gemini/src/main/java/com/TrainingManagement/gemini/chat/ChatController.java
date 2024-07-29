package com.TrainingManagement.gemini.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrainingManagement.gemini.Service.GeminiService;

@RestController
@RequestMapping("/geminichat")
@CrossOrigin
public class ChatController {

	@Autowired
	GeminiService geminiService;
	
	@PostMapping("/prompt")
	public String getResponse( @RequestBody String prompt) {
		return geminiService.callApi(prompt);
	}
}