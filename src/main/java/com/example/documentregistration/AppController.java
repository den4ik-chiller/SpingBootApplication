package com.example.documentregistration;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.documentregistration.security.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Класс-контроллер, обрабатывающий HTTP-запросы и взаимодействующий с сервисом документов.
 */
@Controller
public class AppController {

    /**
     * Сервис для работы с документами.
     */
    @Autowired
    private DocumentService service;

    /**
     * Обрабатывает GET-запрос на показ страницы логина.
     * @return имя шаблона для отображения страницы логина.
     */
    @GetMapping("/login_page")
    public String login() {
        return "login_page";
    }

    /**
     * Обрабатывает POST-запрос с отправкой формы логина.
     * @param username имя пользователя, введенное в форму.
     * @param session  текущая сессия пользователя.
     * @return перенаправляет на главную страницу.
     */
    @PostMapping("/login_page")
    public String loginSubmit(@RequestParam String username, HttpSession session) {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username", currentUser);
        return "redirect:/";
    }

    /**
     * Обрабатывает POST-запрос с отправкой формы регистрации нового пользователя.
     * @param userInfo данные нового пользователя.
     * @param name     имя нового пользователя.
     * @param roles    роли нового пользователя.
     * @param session  текущая сессия пользователя.
     * @return перенаправляет на главную страницу.
     */
    @PostMapping("/reg")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        service.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }

    /**
     * Обрабатывает GET-запрос на показ страницы регистрации нового пользователя.
     * @param request  текущий HTTP-запрос.
     * @param response текущий HTTP-ответ.
     * @return имя шаблона для отображения страницы регистрации нового пользователя.
     */
    @GetMapping("/reg")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "reg_page";
    }

    /**
     * Обрабатывает POST-запрос с отправкой формы регистрации нового пользователя администратором.
     * @param userInfo данные нового пользователя.
     * @param name     имя нового пользователя.
     * @param roles    роли нового пользователя.
     * @param session  текущая сессия пользователя.
     * @return перенаправляет на главную страницу.
     */
    @PostMapping("/reg_admin")
    public String addNewUserAdmin(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println(roles);
        service.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }


    /**
     * Обработчик GET запросов на адрес "/reg_admin". Возвращает страницу регистрации нового пользователя для администратора.
     * @return строка, содержащая имя представления страницы регистрации нового пользователя для администратора
     */
    @GetMapping("/reg_admin")
    public String registerAdmin() {
        return "reg_page";
    }

    /**
     * Обрабатывает запрос на главную страницу приложения и выводит список всех документов.
     * @param model         объект модели для передачи данных в представление
     * @param keyword       ключевое слово для поиска
     * @return              имя представления для главной страницы
     */
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Document> listDoc = service.listAll(keyword);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keyword", keyword);
        model.addAttribute("inputId", "keyword");
        return "index";
    }

    /**
     * Обрабатывает запрос на поиск документа по его названию.
     * @param model             объект модели для передачи данных в представление
     * @param keywordDocName    ключевое слово для поиска документа по его названию
     * @return                  имя представления для главной страницы
     */
    @RequestMapping("/searchDocName")
    public String searchByDocName(Model model, @Param("keywordDocName") String keywordDocName) {
        List<Document> listDoc = service.listName(keywordDocName);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordDocName", keywordDocName);
        model.addAttribute("inputId", "keywordDocName");
        return "index";
    }

    /**
     * Обрабатывает запрос на поиск документов по дате регистрации.
     * @param model                     объект модели для передачи данных в представление
     * @param keywordRegistrationDate  ключевое слово для поиска документов по дате регистрации
     * @return                          имя представления для главной страницы
     */
    @RequestMapping("/searchRegistrationDate")
    public String searchByRegistrationDate(Model model, @Param("keywordRegistrationDate") String keywordRegistrationDate) {
        List<Document> listDoc = service.listRegDate(keywordRegistrationDate);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordRegistrationDate", keywordRegistrationDate);
        model.addAttribute("inputId", "keywordRegistrationDate");
        return "index";
    }

    /**
     * Обрабатывает запрос на поиск документа по его номеру.
     * @param model             объект модели для передачи данных в представление
     * @param keywordDocNumber  ключевое слово для поиска документа по его номеру
     * @return                  имя представления для главной страницы
     */
    @RequestMapping("/searchDocNumber")
    public String searchByDocNumber(Model model, @Param("keywordDocNumber") String keywordDocNumber) {
        List<Document> listDoc = service.listNumber(keywordDocNumber);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordDocNumber", keywordDocNumber);
        model.addAttribute("inputId", "keywordDocNumber");
        return "index";
    }

    /**
     * Обрабатывает запрос на поиск документов по описанию.
     * @param model                     объект модели для передачи данных в представление
     * @param keywordDocDescription    ключевое слово для поиска документов по описанию
     * @return                          имя представления для главной страницы
     */
    @RequestMapping("/searchDocDescription")
    public String searchByDocDescription(Model model, @Param("keywordDocDescription") String keywordDocDescription) {
        List<Document> listDoc = service.listDescription(keywordDocDescription);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordDocDescription", keywordDocDescription);
        model.addAttribute("inputId", "keywordDocDescription");
        return "index";
    }

    /**
     * Обрабатывает запрос поиска по категории документа.
     * @param model объект Model, необходимый для передачи данных в представление
     * @param keywordCategory строка ключевых слов, которые нужно найти в категории документа
     * @return строка, содержащая имя представления
     */
    @RequestMapping("/searchCategory")
    public String searchByCategory(Model model, @Param("keywordCategory") String keywordCategory) {
        List<Document> listDoc = service.listCategory(keywordCategory);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordCategory", keywordCategory);
        model.addAttribute("inputId", "keywordCategory");
        return "index";
    }

    /**
     * Обрабатывает запрос поиска по автору документа.
     * @param model объект Model, необходимый для передачи данных в представление
     * @param keywordAuthor строка ключевых слов, которые нужно найти в имени автора документа
     * @return строка, содержащая имя представления
     */
    @RequestMapping("/searchAuthor")
    public String searchByAuthor(Model model, @Param("keywordAuthor") String keywordAuthor) {
        List<Document> listDoc = service.listAuthor(keywordAuthor);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordAuthor", keywordAuthor);
        model.addAttribute("inputId", "keywordAuthor");

        return "index";
    }

    /**
     * Обрабатывает запрос поиска по ID автора документа.
     * @param model объект Model, необходимый для передачи данных в представление
     * @param keywordAuthorId строка, содержащая ID автора документа, по которому нужно выполнить поиск
     * @return строка, содержащая имя представления
     */
    @RequestMapping("/searchAuthorId")
    public String searchByAuthorId(Model model, @Param("keywordAuthorId") String keywordAuthorId) {
        List<Document> listDoc = service.listAuthorId(keywordAuthorId);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordAuthorId", keywordAuthorId);
        model.addAttribute("inputId", "keywordAuthorId");

        return "index";
    }

    /**
     * Метод для поиска документов по получателю
     * @param model объект класса Model, используемый для передачи данных в представление
     * @param keywordRecipient строка, содержащая ключевые слова для поиска
     * @return возвращает имя представления "index", в которое передаются найденные документы, ключевые слова для поиска и ID для поля ввода
     */
    @RequestMapping("/searchRecipient")
    public String searchByRecipient(Model model, @Param("keywordRecipient") String keywordRecipient) {
        List<Document> listDoc = service.listRecipient(keywordRecipient);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordRecipient", keywordRecipient);
        model.addAttribute("inputId", "keywordRecipient");

        return "index";
    }

    /**
     * Метод для поиска документов по имени файла
     * @param model объект класса Model, используемый для передачи данных в представление
     * @param keywordDocFile строка, содержащая ключевые слова для поиска
     * @return возвращает имя представления "index", в которое передаются найденные документы, ключевые слова для поиска и ID для поля ввода
     */
    @RequestMapping("/searchDocFile")
    public String searchByDocFile(Model model, @Param("keywordDocFile") String keywordDocFile) {
        List<Document> listDoc = service.listFiles(keywordDocFile);
        model.addAttribute("listDoc", listDoc);
        model.addAttribute("keywordDocFile", keywordDocFile);
        model.addAttribute("inputId", "keywordDocFile");

        return "index";
    }

    /**
     * Метод для поиска документов по состоянию
     * @param model объект класса Model, используемый для передачи данных в представление
     * @param keywordCondition строка, содержащая ключевые слова для поиска
     * @return возвращает имя представления "index", в которое передаются найденные документы, ключевые слова для поиска и ID для поля ввода
     */
    @RequestMapping("/searchCondition")
    public String searchByCondition(Model model, @Param("keywordCondition") String keywordCondition) {
        List<Document> listCondition = service.listConditions(keywordCondition);
        model.addAttribute("listDoc", listCondition);
        model.addAttribute("keywordCondition", keywordCondition);
        model.addAttribute("inputId", "keywordCondition");
        return "index";
    }

    /**
     * Обработчик запроса по адресу "/employee".
     * Отображает страницу со списком всех сотрудников.
     * @param model объект, содержащий данные, передаваемые в представление
     * @param keywordAllEmp ключевое слово для поиска всех сотрудников
     * @return имя представления для отображения списка всех сотрудников
     */
    @RequestMapping("/employee")
    public String viewEmpPage(Model model, @Param("keywordAllEmp") String keywordAllEmp) {
        List<Employee> listEmp = service.listAllEmp(keywordAllEmp);
        System.out.println(keywordAllEmp);
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("keywordAllEmp", keywordAllEmp);
        model.addAttribute("inputId", "keywordAllEmp");
        return "employee";
    }

    /**
     * Отображает страницу со списком сотрудников, чьи имена совпадают с заданным ключевым словом.
     * @param model объект, содержащий данные, передаваемые в представление
     * @param keywordFirstName ключевое слово для поиска сотрудников по имени
     * @return имя представления для отображения списка найденных сотрудников
     */
    @RequestMapping("/searchFirstName")
    public String searchByFirstName(Model model, @Param("keywordFirstName") String keywordFirstName) {
        List<Employee> listEmp = service.listFirstName(keywordFirstName);
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("keywordFirstName", keywordFirstName);
        model.addAttribute("inputId", "keywordFirstName");
        return "employee";
    }

    /**
     * Отображает страницу со списком сотрудников, чьи фамилии совпадают с заданным ключевым словом.
     * @param model объект, содержащий данные, передаваемые в представление
     * @param keywordSecondName ключевое слово для поиска сотрудников по фамилии
     * @return имя представления для отображения списка найденных сотрудников
     */
    @RequestMapping("/searchSecondName")
    public String searchBySecondName(Model model, @Param("keywordSecondName") String keywordSecondName) {
        List<Employee> listEmp = service.listSecondName(keywordSecondName);
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("keywordSecondName", keywordSecondName);
        model.addAttribute("inputId", "keywordSecondName");
        return "employee";
    }

    /**
     * Отображает страницу со списком сотрудников, чьи должности совпадают с заданным ключевым словом.
     * @param model объект, содержащий данные, передаваемые в представление
     * @param keywordPosition ключевое слово для поиска сотрудников по должности
     * @return имя представления для отображения списка найденных сотрудников
     */
    @RequestMapping("/searchPosition")
    public String searchByPosition(Model model, @Param("keywordPosition") String keywordPosition) {
        List<Employee> listEmp = service.listPosition(keywordPosition);
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("keywordPosition", keywordPosition);
        model.addAttribute("inputId", "keywordPosition");
        return "employee";
    }

    /**
     * Обрабатывает запрос на отображение формы для создания нового документа.
     * @param model объект, содержащий модель данных, используемых для построения представления
     * @return имя представления, отображающего форму для создания нового документа
     */
    @RequestMapping("/new")
    public String showNewDocForm(Model model) {
        Document doc = new Document();
        model.addAttribute("doc", doc);
        return "new_doc";
    }

    /**
     * Обрабатывает запрос на отображение формы для создания нового сотрудника.
     * @param model объект, содержащий модель данных, используемых для построения представления
     * @return имя представления, отображающего форму для создания нового сотрудника
     */
    @RequestMapping("/newEmp")
    public String showNewEmpForm(Model model) {
        Employee emp = new Employee();
        model.addAttribute("emp", emp);
        return "new_emp";
    }

    /**
     * Обрабатывает запрос на отображение информации о разработчике.
     * @return имя представления, отображающего информацию о разработчике
     */
    @RequestMapping("/aboutMe")
    public String showMe() {
        return "aboutMe";
    }

    /**
     * Обрабатывает запрос на сохранение нового документа.
     * @param doc объект документа, полученный из формы
     * @return имя представления для перенаправления после сохранения документа
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDoc(@ModelAttribute("doc") Document doc) {
        service.save(doc);
        return "redirect:/";
    }

    /**
     * Обрабатывает запрос на сохранение нового сотрудника.
     * @param emp объект сотрудника, полученный из формы
     * @return имя представления для перенаправления после сохранения сотрудника
     */
    @RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
    public String saveEmp(@ModelAttribute("emp") Employee emp) {
        service.saveEmp(emp);
        return "redirect:/employee";
    }

    /**
     * Отображает форму редактирования документа с указанным идентификатором.
     * @param id Идентификатор документа для редактирования.
     * @return ModelAndView объект, содержащий представление "edit_doc" и объект документа для редактирования.
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditDocForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_doc");
        Document doc = service.get(id);
        mav.addObject("doc", doc);
        return mav;
    }

    /**
     * Отображает форму редактирования информации о сотруднике с указанным идентификатором.
     * @param id Идентификатор сотрудника для редактирования.
     * @return ModelAndView объект, содержащий представление "edit_emp" и объект сотрудника для редактирования.
     */
    @RequestMapping("/editEmp/{id}")
    public ModelAndView showEditEmpForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_emp");
        Employee emp = service.getEmp(id);
        mav.addObject("emp", emp);
        return mav;
    }

    /**
     * Удаляет документ с указанным идентификатором.
     * @param id Идентификатор документа для удаления.
     * @return Редирект на главную страницу приложения.
     */
    @RequestMapping("/delete/{id}")
    public String deleteDoc(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    /**
     * Удаляет информацию о сотруднике с указанным идентификатором.
     * @param id Идентификатор сотрудника для удаления.
     * @return Редирект на страницу со списком сотрудников.
     */
    @RequestMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable(name = "id") Long id) {
        service.deleteEmp(id);
        return "redirect:/employee";
    }
}
