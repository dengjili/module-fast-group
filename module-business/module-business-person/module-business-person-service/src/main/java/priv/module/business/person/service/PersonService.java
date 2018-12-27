package priv.module.business.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.module.business.person.bean.Person;
import priv.module.business.person.dao.PersonMapper;
import priv.module.config.bean.PageParams;
import priv.module.config.bean.ReturnParam;

@Service
public class PersonService {

	@Autowired
	private PersonMapper mapper;
	
	// 不分页查询
	public List<Person> listPerson() {
		return mapper.listPagePersons(new PageParams());
	}
	
	public ReturnParam<Person> listPagePersons(PageParams params) {
		List<Person> listPagePersons = mapper.listPagePersons(params);
		ReturnParam<Person> persons = new ReturnParam<Person> (params);
		persons.setList(listPagePersons);
		return persons;
	}
	// 添加一个用户
	public void addPerson(Person person) {
		mapper.addPerson(person);
	}

	// 删除一个用户
	public void deletePerson(Person person) {
		mapper.deletePerson(person);
	}

	// 查询一个用户
	public Person getPerson(int id) {
		return mapper.getPerson(id);
	}

	// 更新一个用户
	public void updatePerson(Person person) {
		mapper.updatePerson(person);
	}

	// 模糊查询
	public List<Person> listPersonByCond(String id, String name) {
		return mapper.listPersonByCond(id, name);
	}
	
}
