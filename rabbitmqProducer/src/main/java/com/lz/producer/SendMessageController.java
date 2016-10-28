package com.lz.producer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SendMessageController {
	
	@Autowired
	SendMessages sendService;
	
	String mesg = "";
	
	@RequestMapping("/send")
	public String send(Model model, @RequestParam(value = "text", required = false) String text) throws IOException{
		if(text == null || text.isEmpty()){
			return "error";
		}
		else{
			mesg = "The message has been sent!";
			model.addAttribute("mesg", mesg);
			model.addAttribute("text", text);
			sendService.sendMessage(text);
			
		}
		return "send";
		
	}

}
