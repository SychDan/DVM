package ru.mephi.kaf82.DVM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.repository.PersonRepository;

import javax.annotation.Resource;

/**
 * Created by Vakobo
 */

public class DataLoader implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Resource
    private PersonRepository personRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        createPerson();

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createPerson() {
        if (personRepository.count() == 0) {
            Person person = new Person();
            person.setFirstName("User");
            person.setSecondName("User");
            personRepository.save(person);
        }
    }
}
