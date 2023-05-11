package com.example.documentregistration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Репозиторий для управления объектами Employee в базе данных.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    /**
     * Поиск сотрудника по ключевому слову в его имени, фамилии или должности.
     * @param keyword Ключевое слово для поиска.
     * @return Список сотрудников, удовлетворяющих поисковому запросу.
     */
    @Query("select e from Employee e where concat(e.first_name, ' ', e.second_name, ' ', e.position, ' ') LIKE %?1%")
    List<Employee> searchEmp(String keyword);

    /**
     * Поиск сотрудника по ключевому слову в его имени.
     * @param keyword Ключевое слово для поиска.
     * @return Список сотрудников, удовлетворяющих поисковому запросу.
     */
    @Query("select e from Employee e where e.first_name like %?1%")
    List<Employee> searchByFirstName(String keyword);

    /**
     * Поиск сотрудника по ключевому слову в его фамилии.
     * @param keyword Ключевое слово для поиска.
     * @return Список сотрудников, удовлетворяющих поисковому запросу.
     */
    @Query("select e from Employee e where e.second_name like %?1%")
    List<Employee> searchBySecondName(String keyword);

    /**
     * Поиск сотрудника по ключевому слову в его должности.
     * @param keyword Ключевое слово для поиска.
     * @return Список сотрудников, удовлетворяющих поисковому запросу.
     */
    @Query("select e from Employee e where e.position like %?1%")
    List<Employee> searchByPosition(String keyword);
}
