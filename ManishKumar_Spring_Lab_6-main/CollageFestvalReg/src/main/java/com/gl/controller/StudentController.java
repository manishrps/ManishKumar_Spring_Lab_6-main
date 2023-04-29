package com.gl.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.entity.Student;
import com.gl.service.StudentService;
@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;

	@RequestMapping("/list")
	public String showStudentList(Model model) {
		System.out.println("welcome to controller");
		
		List<Student> theStudent = studentService.findAll();
		model.addAttribute("students", theStudent);
		
		return "list-students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Student theStudent = new Student();
		model.addAttribute("student", theStudent);
		System.out.println("welcome to show form for add");
		return "student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student theStudent = studentService.findById(id);
		model.addAttribute("student", theStudent);

		return "student-form";

	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id")  int id, 
		@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName,
		@RequestParam("course") String course,
		@RequestParam("country") String country) {

			System.out.println("welcome to save method");
			Student theStudent;
			if(id!=0)
			{
				theStudent=studentService.findById(id);
				theStudent.setFirstName(firstName);
				theStudent.setLastName(lastName);
				theStudent.setCourse(course);
				theStudent.setCountry(country);
			}
			else {
				theStudent=new Student(firstName, lastName, course,country);
			}
			// save the Student
			studentService.save(theStudent);
			return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		System.out.println("welcome to delete method");
		return "redirect:/students/list";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		
		
		System.out.println("welcome to acsess denied");
		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
