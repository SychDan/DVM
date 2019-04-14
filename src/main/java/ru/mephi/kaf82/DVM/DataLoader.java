package ru.mephi.kaf82.DVM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.model.Terminal;
import ru.mephi.kaf82.DVM.repository.PersonRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;

import javax.annotation.Resource;

/**
 * Created by Vakobo
 */

public class DataLoader implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Resource
    private PersonRepository personRepository;

    @Resource
    private TerminalRepository terminalRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
        createPerson();
        createTerminal();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createPerson() {
        if (personRepository.count() == 0) {
            Person person = new Person();
            person.setFirstName("John");
            person.setSecondName("Doe");
            personRepository.save(person);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createTerminal() {
        if (terminalRepository.count() == 0) {
            Terminal terminal = new Terminal();
            terminal.setNameGroup("Тестовое название");
            terminal.setAddress("Бибирево 23");
            terminal.setIp("123.2314.212.32");
            terminal.setTermNumber("001");
            terminalRepository.save(terminal);
        }
    }
}
