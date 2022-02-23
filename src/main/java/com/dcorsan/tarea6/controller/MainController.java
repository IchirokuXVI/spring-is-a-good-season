package com.dcorsan.tarea6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*")
public class MainController {
	@GetMapping
	private String index(Model model) {
		model.addAttribute("insertView", "index");
		return "main";
	}
}
