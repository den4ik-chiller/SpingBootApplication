package com.example.documentregistration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Интерфейс репозитория для документов.
 */
public interface DocumentRepository extends JpaRepository<Document, Long>{
    /**
     * Поиск документов по ключевому слову, которое ищется в разных полях документа.
     * @param keyword Ключевое слово, которое ищется в разных полях документа.
     * @return Список документов, удовлетворяющих критериям поиска.
     */
    @Query("select d from Document d where concat(d.author, ' ', d.category, ' ', d.doc_description, ' ', d.doc_file, ' '," +
            "d.doc_name, ' ', d.doc_number, ' ', d.recipient, ' ', d.registration_date, ' ') LIKE %?1%")
    List<Document> search(String keyword);

    /**
     * Поиск документов по имени документа.
     * @param keyword Ключевое слово, которое ищется в имени документа.
     * @return Список документов, удовлетворяющих критериям поиска.
     */
    @Query("select d from Document d where d.doc_name like %?1%")
    List<Document> searchByDocName(String keyword);

    /**
     * Поиск документов по дате регистрации.
     * @param keyword Ключевое слово, которое ищется в дате регистрации документа.
     * @return Список документов, удовлетворяющих критериям поиска.
     */
    @Query("select d from Document d where d.registration_date like %?1%")
    List<Document> searchByRegistrationDate(String keyword);

    /**
     * Поиск документов по номеру документа.
     * @param keyword Ключевое слово, которое ищется в номере документа.
     * @return Список документов, удовлетворяющих критериям поиска.
     */
    @Query("select d from Document d where d.doc_number like %?1%")
    List<Document> searchByDocNumber(String keyword);

    /**
     * Поиск документов по описанию документа.
     * @param keyword Ключевое слово, которое ищется в описании документа.
     * @return Список документов, удовлетворяющих критериям поиска.
     */
    @Query("select d from Document d where d.doc_description like %?1%")
    List<Document> searchByDocDescription(String keyword);

    /**
     * Поиск документов по категории документа.
     * @param keyword Ключевое слово, которое ищется в категории документа.
     * @return Список документов, удовлетворяющих критериям поиска.
     */
    @Query("select d from Document d where d.category like %?1%")
    List<Document> searchByCategory(String keyword);

    /**
     * Поиск документов по автору документа.
     * @param keyword Ключевое слово, которое ищется в авторе документа.
     * @return Список документов, удовлетворяющих критериям поиска.
     */
    @Query("select d from Document d where d.author like %?1%")
    List<Document> searchByAuthor(String keyword);

    /**
     * Поиск документов по идентификатору автора.
     * @param keyword ключевое слово для поиска по идентификатору автора
     * @return список документов, удовлетворяющих условию поиска
     */
    @Query("select d from Document d where d.author_id like %?1%")
    List<Document> searchByAuthorId(String keyword);

    /**
     * Поиск документов по получателю.
     * @param keyword ключевое слово для поиска по получателю
     * @return список документов, удовлетворяющих условию поиска
     */
    @Query("select d from Document d where d.recipient like %?1%")
    List<Document> searchByRecipient(String keyword);

    /**
     * Поиск документов по имени файла.
     * @param keyword ключевое слово для поиска по имени файла
     * @return список документов, удовлетворяющих условию поиска
     */
    @Query("select d from Document d where d.doc_file like %?1%")
    List<Document> searchByDocFile(String keyword);

    /**
     * Поиск документов по состоянию документа.
     * @param keyword ключевое слово для поиска по состоянию документа
     * @return список документов, удовлетворяющих условию поиска
     */
    @Query("select d from Document d where d.doc_condition like %?1%")
    List<Document> searchByCondition(String keyword);
}
