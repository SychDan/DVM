package ru.mephi.kaf82.DVM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.kaf82.DVM.model.Camera;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.File;
import ru.mephi.kaf82.DVM.model.Marshrut;
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.model.Terminal;
import ru.mephi.kaf82.DVM.model.Type;
import ru.mephi.kaf82.DVM.repository.CameraRepository;
import ru.mephi.kaf82.DVM.repository.EntryRepository;
import ru.mephi.kaf82.DVM.repository.FileRepository;
import ru.mephi.kaf82.DVM.repository.MarshrutRepository;
import ru.mephi.kaf82.DVM.repository.PersonRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;
import ru.mephi.kaf82.DVM.util.FileUtil;
import ru.mephi.kaf82.DVM.util.HashCalculator;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

/**
 * Created by Vakobo
 */

public class DataLoader implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Resource
    private PersonRepository personRepository;

    @Resource
    private TerminalRepository terminalRepository;

    @Resource
    private EntryRepository entryRepository;

    @Resource
    private MarshrutRepository marshrutRepository;

    @Resource
    private CameraRepository cameraRepository;

    @Resource
    private FileRepository fileRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        createPerson();
        createTerminal();
        createMarshrut();
        createMedia();
        createPhoto();
        createFile();
        createCamera();
        createJournals();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createPerson() {
        if (personRepository.count() == 0) {
            Person person = new Person();
            person.setFirstName("John");
            person.setSecondName("Doe");
            person.setCardNumber("03324");
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createMedia() {
        if (fileRepository.count() == 0) {
            for (int i=0; i<5; i++) {
                try {
                    File media = new File();
                    Path path = Paths.get("/home/danny/Downloads/Rick_Astley_Never_Gonna_Give_You_Up (online-video-cutter.com).mp4");
                    String name = path.getFileName().toString();
                    String hash = HashCalculator.getSHA256String(Files.readAllBytes(path));
                    byte[] fileData = Files.readAllBytes(path);
                    if (fileRepository.countByHash(hash) == 0) {
                        FileUtil.saveFile(hash, fileData);
                    }
                    media.setName(name);
                    media.setHash(hash);
                    media.setType(Type.MEDIA);
                    media.setDate(Instant.now());
                    fileRepository.save(media);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createPhoto() {
        if (fileRepository.count() < 6) {
            for (int i=0; i<2; i++) {
                try {
                    File photo = new File();
                    Path path = Paths.get("/home/danny/Pictures/Wall/37669442555_6927a3d2df_o.jpg");
                    String name = path.getFileName().toString();
                    String hash = HashCalculator.getSHA256String(Files.readAllBytes(path));
                    byte[] fileData = Files.readAllBytes(path);
                    if (fileRepository.countByHash(hash) == 0) {
                        FileUtil.saveFile(hash, fileData);
                    }
                    photo.setName(name);
                    photo.setHash(hash);
                    photo.setType(Type.IMAGE);
                    photo.setDate(Instant.now());
                    fileRepository.save(photo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createFile() {
        try {
            File file = new File();
            Path path = Paths.get("/home/danny/Downloads/log.txt");
            String name = path.getFileName().toString();
            String hash = HashCalculator.getSHA256String(Files.readAllBytes(path));
            byte[] fileData = Files.readAllBytes(path);
            if (fileRepository.countByHash(hash) == 0) {
                FileUtil.saveFile(hash, fileData);
            }
            file.setName(name);
            file.setHash(hash);
            file.setType(Type.LOG);
            file.setDate(Instant.now());
            fileRepository.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createMarshrut() {
        if (marshrutRepository.count() == 0) {
            Marshrut marshrut = new Marshrut();
            marshrut.setDescription("Пробный маршрут");
            marshrut.setName("Маршркт №1");
            marshrutRepository.save(marshrut);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createCamera() {
        if (cameraRepository.count() == 0) {
            Camera camera = new Camera();
            camera.setCamCode("111");
            camera.setInvNum("222");
            camera.setSerNum("777");
            cameraRepository.save(camera);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createJournals() {
        if (entryRepository.count() == 0) {
            Entry entry = new Entry();
            entry.setPerson(personRepository.findByCardNumber("03324"));
            entry.setDateOfEntry(Instant.now());
            entry.setMedia(fileRepository.findByType(Type.MEDIA).get(0));
            entry.setMarshrut(((List<Marshrut>)marshrutRepository.findAll()).get(0));
            entry.setCamera(((List<Camera>)cameraRepository.findAll()).get(0));
            entryRepository.save(entry);
            for (File media : fileRepository.findByType(Type.MEDIA)) {
                media.setEntry(entry);
                fileRepository.save(media);
            }
            for (File photo : fileRepository.findByType(Type.IMAGE)) {
                photo.setEntry(entry);
                fileRepository.save(photo);
            }
            for (File log : fileRepository.findByType(Type.LOG)) {
                log.setEntry(entry);
                fileRepository.save(log);
            }
        }
    }
}
