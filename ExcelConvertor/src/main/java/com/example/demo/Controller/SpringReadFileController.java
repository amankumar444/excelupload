package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Model.EmployeeEntity;
import com.example.demo.Service.ServiceImpl;

@Controller

public class SpringReadFileController {

	@Autowired
	private ServiceImpl springImpl;

	
	@RequestMapping(value = "/ViewList", method = RequestMethod.GET)
	public String viewList(Model model) {
		List<EmployeeEntity> eList = springImpl.getAllUsers();
		model.addAttribute("Employees", eList);
		return "EmployeeList";
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		System.out.println(springImpl.averageSalary());
		return "home";
		
	}
	
	@RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
	public String uploadfile() {
		return "uploadfile";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		
		boolean isFlag = springImpl.saveDataFromUploadfile(file);
		if(isFlag) {
			redirectAttributes.addFlashAttribute("successmessage", "File upload success");
		}
		else {
			redirectAttributes.addFlashAttribute("errormessage", "Upload not done, Please try Again!!");
			
		}
		return "redirect:/ViewList";
	}	
	
}
