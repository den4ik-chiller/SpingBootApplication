package com.example.documentregistration;
import jakarta.persistence.*;

/**
 * Класс представляет объект сотрудника, хранящийся в базе данных.
 * Объекты класса используются для представления информации о сотрудниках.
 */
@Entity
public class Employee {

    /**
     *  Уникальный идентификатор сотрудника.
     */
    @Column(name = "id")
    private Long id;

    /**
     *  Имя сотрудника.
     */
    @Column(name = "first_name")
    private String first_name;

    /**
     *  Фамилия сотрудника.
     */
    @Column(name = "second_name")
    private String second_name;

    /**
     *  Должность сотрудника.
     */
    @Column(name = "position")
    private String position;

    /**
     *  Конструктор по умолчанию для создания объектов класса.
     */
    protected Employee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     *  Возвращает идентификатор сотрудника
     *  @return идентификатор сотрудника
     */
    public Long getId() {
        return id;
    }

    /**
     *  Устанавливает идентификатор сотрудника
     *  @param id идентификатор сотрудника
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  Возвращает имя сотрудника
     *  @return имя сотрудника
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Устанавливает имя сотрудника
     * @param first_name имя сотрудника
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Возвращает фамилию сотрудника
     * @return фамилию сотрудника
     */
    public String getSecond_name() {
        return second_name;
    }

    /**
     * Устанавливает фамилию сотрудника
     * @param second_name фамилия сотрудника
     */
    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    /**
     * Возвращает должность сотрудника
     * @return должность сотрудника
     */
    public String getPosition() {
        return position;
    }

    /**
     * Устанавливает должность сотрудника
     * @param position должность сотрудника
     */
    public void setPosition(String position) {
        this.position = position;
    }
}
