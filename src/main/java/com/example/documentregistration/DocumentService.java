package com.example.documentregistration;
import java.util.List;
import java.util.Optional;

import com.example.documentregistration.security.UserInfo;
import com.example.documentregistration.security.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для работы с документами.
 */
@Service
public class DocumentService {
    /**
    * Репозиторий документов.
    */
    @Autowired
    private DocumentRepository repo;

    /**
    * Репозиторий информации о пользователях.
    */
    @Autowired
    private UserInfoRepository userRepo;

    /**
    * Репозиторий информации о сотрудниках.
    */
    @Autowired
    private EmployeeRepository empRepo;

    /**
    * Репозиторий информации о сотрудниках.
    */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Возвращает список всех документов, соответствующих заданному ключевому слову или возвращает все документы, если ключевое слово не указано.
     * @param keyword ключевое слово для поиска документов
     * @return список документов, соответствующих ключевому слову или все документы, если ключевое слово не указано
     */
    public List<Document> listAll(String keyword){
        if (keyword != null){
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    /**
     * Возвращает список документов с заданным именем или все документы, если имя не указано.
     * @param keyword имя для поиска документов
     * @return список документов с заданным именем или все документы, если имя не указано
     */
    public List<Document> listName(String keyword){
        if (keyword != null){
            return repo.searchByDocName(keyword);
        }
        return repo.findAll();
    }

    /**
     * Возвращает список документов с заданной датой регистрации или все документы, если дата регистрации не указана.
     * @param keyword дата регистрации для поиска документов
     * @return список документов с заданной датой регистрации или все документы, если дата регистрации не указана
     */
    public List<Document> listRegDate(String keyword){
        if (keyword != null){
            return repo.searchByRegistrationDate(keyword);
        }
        return repo.findAll();
    }

    /**
     * Возвращает список документов с заданным номером или все документы, если номер не указан.
     * @param keyword номер для поиска документов
     * @return список документов с заданным номером или все документы, если номер не указан
     */
    public List<Document> listNumber(String keyword){
        if (keyword != null){
            return repo.searchByDocNumber(keyword);
        }
        return repo.findAll();
    }

    /**
     * Метод возвращает список документов, которые содержат указанное ключевое слово в описании.
     * Если ключевое слово равно null, метод возвращает список всех документов.
     * @param keyword ключевое слово для поиска в описании документов
     * @return список документов, которые содержат указанное ключевое слово в описании, или список всех документов, если ключевое слово равно null
     */
    public List<Document> listDescription(String keyword){
        if (keyword != null){
            return repo.searchByDocDescription(keyword);
        }
        return repo.findAll();
    }

    /**
     * Метод возвращает список документов, относящихся к указанной категории.
     * Если категория равна null, метод возвращает список всех документов.
     * @param keyword категория документов для поиска
     * @return список документов, относящихся к указанной категории, или список всех документов, если категория равна null
     */
    public List<Document> listCategory(String keyword){
        if (keyword != null){
            return repo.searchByCategory(keyword);
        }
        return repo.findAll();
    }

    /**
     * Метод возвращает список документов, автором которых является указанный автор.
     * Если имя пользователя равно null, метод возвращает список всех документов.
     * @param keyword имя пользователя для поиска документов
     * @return список документов, автором которых является указанный пользователь, или список всех документов, если имя пользователя равно null
     */
    public List<Document> listAuthor(String keyword){
        if (keyword != null){
            return repo.searchByAuthor(keyword);
        }
        return repo.findAll();
    }

    /**
     * Метод возвращает список документов, у которых указанный пользователь является автором.
     * Если идентификатор пользователя равен null, метод возвращает список всех документов.
     * @param keyword идентификатор пользователя для поиска документов
     * @return список документов, у которых указанный пользователь является автором, или список всех документов, если идентификатор пользователя равен null
     */
    public List<Document> listAuthorId(String keyword){
        if (keyword != null){
            return repo.searchByAuthorId(keyword);
        }
        return repo.findAll();
    }

    /**
     * Метод возвращает список документов, у которых указанный пользователь является получателем.
     * Если имя пользователя равно null, метод возвращает список всех документов.
     * @param keyword имя пользователя для поиска документов
     * @return список документов, у которых указанный пользователь является получателем, или список всех документов, если имя пользователя равно null
     */
    public List<Document> listRecipient(String keyword){
        if (keyword != null){
            return repo.searchByRecipient(keyword);
        }
        return repo.findAll();
    }

    /**
     * Возвращает список документов, содержащих файлы с заданным ключевым словом.
     * Если ключевое слово не задано, возвращает все документы.
     * @param keyword ключевое слово для поиска
     * @return список документов, содержащих файлы с заданным ключевым словом, или все документы
     */
    public List<Document> listFiles(String keyword){
        if (keyword != null){
            return repo.searchByDocFile(keyword);
        }
        return repo.findAll();
    }

    /**
     * Возвращает список документов, соответствующих заданным условиям.
     * Если условие не задано, возвращает все документы.
     * @param keyword ключевое слово для поиска
     * @return список документов, соответствующих заданным условиям, или все документы
     */
    public List<Document> listConditions(String keyword){
        if (keyword != null){
            return repo.searchByCondition(keyword);
        }
        return repo.findAll();
    }

    /**
     * Добавляет нового пользователя в систему.
     * @param userInfo информация о пользователе
     * @return сообщение об успешном добавлении пользователя в систему
     */
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepo.save(userInfo);
        return "user added to system";
    }

    /**
     * Возвращает список всех сотрудников, соответствующих заданному ключевому слову.
     * Если ключевое слово не задано, возвращает всех сотрудников.
     * @param keyword ключевое слово для поиска
     * @return список всех сотрудников, соответствующих заданному ключевому слову, или всех сотрудников
     */
    public List<Employee> listAllEmp(String keyword){
        if (keyword != null){
            return empRepo.searchEmp(keyword);
        }
        return empRepo.findAll();
    }

    /**
     * Возвращает список сотрудников, у которых имя содержит заданное ключевое слово.
     * Если ключевое слово не задано, возвращает всех сотрудников.
     * @param keyword ключевое слово для поиска
     * @return список сотрудников, у которых имя содержит заданное ключевое слово, или всех сотрудников
     */
    public List<Employee> listFirstName(String keyword){
        if (keyword != null){
            return empRepo.searchByFirstName(keyword);
        }
        return empRepo.findAll();
    }

    /**
     * Возвращает список сотрудников, у которых фамилия содержит заданное ключевое слово.
     * Если ключевое слово не задано, возвращает всех сотрудников.
     * @param keyword ключевое слово для поиска
     * @return список сотрудников, у которых фамилия содержит заданное ключевое слово, или всех сотрудников
     */
    public List<Employee> listSecondName(String keyword){
        if (keyword != null){
            return empRepo.searchBySecondName(keyword);
        }
        return empRepo.findAll();
    }

    /**
     * Возвращает список сотрудников, у которых должность содержит заданный ключевое слово.
     * Если ключевое слово не задано, возвращает полный список сотрудников.
     * @param keyword ключевое слово, которое должно содержаться в должности сотрудника
     * @return список сотрудников, у которых должность содержит заданный ключевой слово
     */
    public List<Employee> listPosition(String keyword){
        if (keyword != null){
            return empRepo.searchByPosition(keyword);
        }
        return empRepo.findAll();
    }

    /**
     * Сохраняет документ в базу данных.
     * @param cargo документ, который необходимо сохранить
     */
    public void save(Document cargo) {repo.save(cargo);}

    /**
     * Сохраняет информацию о сотруднике в базу данных.
     * @param emp информация о сотруднике, которую необходимо сохранить
     */
    public void saveEmp(Employee emp) {empRepo.save(emp);}

    /**
     * Возвращает документ с заданным идентификатором.
     * @param id идентификатор документа
     * @return документ с заданным идентификатором
     */
    public Document get(Long id) {return repo.findById(id).get();}

    /**
     * Возвращает информацию о сотруднике с заданным идентификатором.
     * @param id идентификатор сотрудника
     * @return информация о сотруднике с заданным идентификатором
     */
    public Employee getEmp(Long id) {return empRepo.findById(id).get();}

    /**
     * Удаляет документ с заданным идентификатором из базы данных.
     * @param id идентификатор документа, который необходимо удалить
     */
    public void delete(Long id) {repo.deleteById(id);}

    /**
     * Удаляет информацию о сотруднике с заданным идентификатором из базы данных.
     * @param id идентификатор сотрудника, информацию о котором необходимо удалить
     */
    public void deleteEmp(Long id) {empRepo.deleteById(id);}

}
