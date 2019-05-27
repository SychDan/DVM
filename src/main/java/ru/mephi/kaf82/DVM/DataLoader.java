package ru.mephi.kaf82.DVM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.mephi.kaf82.DVM.model.Camera;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.File;
import ru.mephi.kaf82.DVM.model.Marshrut;
import ru.mephi.kaf82.DVM.model.Media;
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.model.Photo;
import ru.mephi.kaf82.DVM.model.Terminal;
import ru.mephi.kaf82.DVM.repository.CameraRepository;
import ru.mephi.kaf82.DVM.repository.EntryRepository;
import ru.mephi.kaf82.DVM.repository.FIleRepository;
import ru.mephi.kaf82.DVM.repository.MarshrutRepository;
import ru.mephi.kaf82.DVM.repository.MediaRepository;
import ru.mephi.kaf82.DVM.repository.PersonRepository;
import ru.mephi.kaf82.DVM.repository.PhotoRepository;
import ru.mephi.kaf82.DVM.repository.TerminalRepository;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Collection;
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
    private MediaRepository mediaRepository;

    @Resource
    private MarshrutRepository marshrutRepository;

    @Resource
    private CameraRepository cameraRepository;

    @Resource
    private PhotoRepository photoRepository;

    @Resource
    private FIleRepository fIleRepository;

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
        if (mediaRepository.count() == 0) {
            for (int i=0; i<5; i++) {
                Media media = new Media();
                Path path = Paths.get("/home/danny/Downloads/Rick_Astley_Never_Gonna_Give_You_Up (online-video-cutter.com).mp4");
                String name = "file.txt";
                String originalFileName = "Rick_Astley_Never_Gonna_Give_You_Up.mp4";
                String contentType = "text/plain";
                byte[] content = null;
                try {
                    content = Files.readAllBytes(path);
                } catch (final IOException e) {
                    LOG.error("Ошибка во время загрузки файла");
                }
                MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
                media.setContentType(result.getContentType());
                try {
                    media.setContent(result.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                media.setName(result.getName());
                media.setSizeFound(BigInteger.valueOf(result.getSize()));
                mediaRepository.save(media);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createPhoto() {
        if (photoRepository.count() == 0) {
            for (int i=0; i<2; i++) {
                Photo photo = new Photo();
                Path path = Paths.get("/home/danny/Pictures/Wall/37669442555_6927a3d2df_o.jpg");
                String name = "photo.jpg";
                String originalFileName = "photo.jpg";
                String contentType = "text/plain";
                byte[] content = null;
                try {
                    content = Files.readAllBytes(path);
                } catch (final IOException e) {
                    LOG.error("Ошибка во время загрузки файла");
                }
                MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
                photo.setContentType(result.getContentType());
                try {
                    photo.setContent(result.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                photo.setPhoto("photo.jpg");
                photoRepository.save(photo);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void createFile() {
        File file = new File();
        Path path = Paths.get("/home/danny/Downloads.log.txt");
        String name = "file.txt";
        String originalFileName = "log.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            LOG.error("Ошибка во время загрузки файла");
        }
        MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
        file.setContentType(result.getContentType());
        try {
            file.setContent(result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.setName("log.txt");
        fIleRepository.save(file);
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
            entry.setMedia(((List<Media>)mediaRepository.findAll()).get(0));
            entry.setMarshrut(((List<Marshrut>)marshrutRepository.findAll()).get(0));
            entry.setCamera(((List<Camera>)cameraRepository.findAll()).get(0));
            entry.getMediaList().addAll((Collection<? extends Media>) mediaRepository.findAll());
            entry.getPhotos().addAll((List<Photo>)photoRepository.findAll());
            entry.getLogs().addAll((List<File>) fIleRepository.findAll());
            entryRepository.save(entry);
            for (Media media : mediaRepository.findAll()) {
                media.setEntry(entry);
                mediaRepository.save(media);
            }
            for (Photo photo : photoRepository.findAll()) {
                photo.setEntry(entry);
                photoRepository.save(photo);
            }
            for (File file : fIleRepository.findAll()) {
                file.setEntry(entry);
                fIleRepository.save(file);
            }
        }
    }
}
