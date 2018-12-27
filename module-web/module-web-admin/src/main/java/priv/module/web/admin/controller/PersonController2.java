package priv.module.web.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import priv.module.business.person.bean.Person;
import priv.module.business.person.service.PersonService;

@Controller
@RequestMapping("/person2")
public class PersonController2 {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController2.class);

	@Autowired
	private PersonService service;

	@RequestMapping(value = "/list")
	public ModelAndView listPerson(Model model) {
		List<Person> listPerson = service.listPerson();
		model.addAttribute("persons", listPerson);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("person2/list");
		return mv;
	}
	
	@RequestMapping(value = "/listjson")
	public void jsonToPage(HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		
		List<Person> listPerson = service.listPerson();
		Map<String, Object> rtnMap = new HashMap<>();
		rtnMap.put("code", 0);
		rtnMap.put("msg", "");
		rtnMap.put("count", 100);
		rtnMap.put("data", listPerson);
		String jsonResult = JSON.toJSONString(rtnMap);
		logger.error("测试json值：{}", jsonResult);
		// 获取jsonResult并发送响应
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(jsonResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("输出失败", e);
		}
	}

	@RequestMapping(value = "/addPage")
	public ModelAndView addPersonPage(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/addPage");
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPerson(@ModelAttribute Person person) {
		service.addPerson(person);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/person/list");
		return mv;
	}
	
	@RequestMapping(value = "/delete/{id}")
	public ModelAndView deletePerson(@PathVariable("id") int id) {
		Person person = new Person();
		person.setId(id);
		service.deletePerson(person);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/person/list");
		return mv;
	}
	

	@RequestMapping(value = "/updatePage/{id}")
	public ModelAndView updatePersonPage(@PathVariable("id") int id, Model model) {
		Person person = new Person();
		person.setId(id);
		Person personDb = service.getPerson(person.getId());
		model.addAttribute("person", personDb);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/updatePage");
		return mv;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePerson(@ModelAttribute Person person) {
		service.updatePerson(person);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/person/list");
		return mv;
	}
	

	@RequestMapping(value = "/get/{id}")
	public ModelAndView getPerson(@PathVariable("id") int id, Model model) {
		Person personDb = service.getPerson(id);
		model.addAttribute("person", personDb);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/detail");
		return mv;
	}
}
