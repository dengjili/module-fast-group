package priv.module.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import priv.module.business.person.bean.Person;
import priv.module.business.person.service.PersonService;
import priv.module.config.bean.PageParams;
import priv.module.config.bean.ReturnParam;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;

	@RequestMapping(value = "/list")
	public ModelAndView listPerson(Model model) {
		List<Person> listPerson = service.listPerson();
		model.addAttribute("persons", listPerson);

		ModelAndView mv = new ModelAndView();

		mv.setViewName("person/list");
		return mv;
	}
	
	@RequestMapping(value = "/listPage")
	public ModelAndView listPersonPage(Model model, PageParams params) {
		ReturnParam<Person> returnParam = service.listPagePersons(params);
		model.addAttribute("persons", returnParam.getList());
		model.addAttribute("page", returnParam.getPage());

		ModelAndView mv = new ModelAndView();

		mv.setViewName("person/list");
		return mv;
	}
	
	@RequestMapping(value = "/queryByCond")
	public ModelAndView queryByCond(String query_id, String query_name, Model model) {
		List<Person> listPerson = service.listPersonByCond(query_id, query_name);
		model.addAttribute("persons", listPerson);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/list");
		return mv;
	}
	
	@RequestMapping(value = "/other")
	public ModelAndView other(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/other");
		return mv;
	}

	@RequestMapping(value = "/addPage")
	public ModelAndView addPersonPage(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/addPage");
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPerson(@ModelAttribute Person person, Model model) {
		service.addPerson(person);
		ModelAndView mv = new ModelAndView();
		
		List<Person> listPerson = service.listPerson();
		model.addAttribute("persons", listPerson);
		mv.setViewName("person/addPage");
		// mv.setViewName("redirect:/person/list");
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
