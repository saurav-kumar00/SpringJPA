//By default @RequestMapping follows get request. we can also mention @GetMapping.

package com.practice.jpa.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practice.jpa.dao.StudentRepo;

//@Controller
//if we don't want to mention @ResponseBody for every method then we can mention @RestController here.
@RestController
public class FrntController {
	
	@Autowired
	StudentRepo s;

	@RequestMapping("/")
	public String home() {
		return "index.jsp";
	}
	//@PostMapping will allow to accept data from client.
	//@RequestBody will enable to accept data in raw json format.
	@PostMapping("/student")
	public String home(@RequestBody Student student) {
		
		s.save(student);
		return "index.jsp";
	}
	
	@RequestMapping("/getStudent")
	public ModelAndView show(@RequestParam String name) {
		ModelAndView mv = new ModelAndView("show.jsp");
		//Student st = s.findAllById(id);
		System.out.println(name);
		List<Student> stud = s.findByName(name);
		mv.addObject(stud);
		System.out.println(stud);
		return mv;
	}
	
	//@RequestMapping(path="/student", produces={"application/xml"})
	@GetMapping(path="/student")
	@ResponseBody
	//@ResponseBody means the return value is not a view but the actual data.
	public List<Student> showStudents() {
		
		return s.findAll();
	}
	
	@RequestMapping("/student/{id}")
	@ResponseBody
	public Optional<Student> showStudent(@PathVariable("id") int id) {
		
		return s.findById(id);
	}
	
}
