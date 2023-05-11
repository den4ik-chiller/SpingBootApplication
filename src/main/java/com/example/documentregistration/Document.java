package com.example.documentregistration;
import jakarta.persistence.*;

/**
 * Класс представляет сущность "Документ" в базе данных
 */
@Entity
public class Document {
    /**
     * Идентификатор документа
    */
    @Column(name = "id")
    private Long id;

    /**
     * Название документа
     */
    @Column(name = "doc_name")
    private String doc_name;

    /**
     * Дата регистрации документа
     */
    @Column(name = "registration_date")
    private String registration_date;

    /**
     * Номер документа
     */
    @Column(name = "doc_number")
    private String doc_number;

    /**
     * Описание документа
     */
    @Column(name = "doc_description")
    private String doc_description;

    /**
     * Категория документа
     */
    @Column(name = "category")
    private String category;

    /**
     * Автор документа
     */
    @Column(name = "author")
    private String author;

    /**
     * Получатель документа
     */
    @Column(name = "recipient")
    private String recipient;

    /**
     * ссылка на файл документа
     */
    @Column(name = "doc_file")
    private String doc_file;

    /**
     * Состояние документа
     */
    @Column(name = "doc_condition")
    private String doc_condition;

    /**
     * Идентификатор автора документа
     */
    @Column (name = "author_id")
    private String author_id;

    /**
     * Конструктор по умолчанию
     */
    protected Document() {
    }

    /**
     * Id документа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     * Получить Id
     */
    public Long getId() {
        return id;
    }

    /**
    * Установить уникальный идентификатор документа.
    * @param id уникальный идентификатор документа
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить название документа.
     * @return название документа
     */
    public String getDoc_name() {
        return doc_name;
    }

    /**
     * Установить название документа.
     * @param doc_name название документа
     */
    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    /**
     * Получить дату регистрации документа.
     * @return дата регистрации документа
     */
    public String getRegistration_date() {
        return registration_date;
    }

    /**
     * Установить дату регистрации документа.
     * @param registration_date дата регистрации документа
     */
    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    /**
     * Возвращает номер документа.
     * @return номер документа
     */
    public String getDoc_number() {
        return doc_number;
    }

    /**
     * Устанавливает номер документа.
     * @param doc_number номер документа
     */
    public void setDoc_number(String doc_number) {
        this.doc_number = doc_number;
    }

    /**
     * Возвращает описание документа.
     * @return описание документа
     */
    public String getDoc_description() {
        return doc_description;
    }

    /**
     * Устанавливает описание документа.
     * @param doc_description описание документа
     */
    public void setDoc_description(String doc_description) {
        this.doc_description = doc_description;
    }

    /**
     * Возвращает категорию документа.
     * @return категория документа
     */
    public String getCategory() {
        return category;
    }

    /**
     * Устанавливает категорию документа.
     * @param category категория документа
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Возвращает автора документа.
     * @return автор документа
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Устанавливает автора документа.
     * @param author автор документа
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * Метод получения получателя документа.
     * @return получатель документа.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Метод установки получателя документа.
     * @param recipient получатель документа.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Метод получения пути к файлу документа.
     * @return путь к файлу документа.
     */
    public String getDoc_file() {
        return doc_file;
    }

    /**
     * Метод установки пути к файлу документа.
     * @param doc_file путь к файлу документа.
     */
    public void setDoc_file(String doc_file) {
        this.doc_file = doc_file;
    }

    /**
     * Метод получения состояния документа.
     * @return состояние документа.
     */
    public String getDoc_condition() {
        return doc_condition;
    }

    /**
     * Метод установки состояния документа.
     * @param doc_condition состояние документа.
     */
    public void setDoc_condition(String doc_condition) {
        this.doc_condition = doc_condition;
    }

    /**
     * Метод получения идентификатора автора документа.
     * @return идентификатор автора документа.
     */
    public String getAuthor_id() {
        return author_id;
    }

    /**
     * Метод установки идентификатора автора документа.
     * @param author_id идентификатор автора документа.
     */
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }
}
