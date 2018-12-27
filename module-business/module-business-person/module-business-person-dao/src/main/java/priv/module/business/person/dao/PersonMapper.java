package priv.module.business.person.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import priv.module.business.person.bean.Person;
import priv.module.config.bean.PageParams;

@Repository
public interface PersonMapper {

	List<Person> listPersons();
	
	List<Person> listPagePersons(PageParams params);

	void addPerson(Person person);

	void deletePerson(Person person);

	Person getPerson(int id);

	void updatePerson(Person person);

	List<Person> listPersonByCond(@Param("id")String id, @Param("name")String name);
}