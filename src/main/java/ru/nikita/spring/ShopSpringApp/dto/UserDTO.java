package ru.nikita.spring.ShopSpringApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserDTO {

    @Size(min = 2, max = 20,
            message = "Длина имени должна быть от 2-ух до 20-ти символов")
    private String firstName;
    @Size(min = 2, max = 20,
            message = "Длина фамилии должна быть от 2-ух до 20-ти символов")
    private  String secondName;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy"
    )
    @NotNull(message = "Необходимо указать дату рождения")
    private Date dateOfBirth;

    public UserDTO() {
    }

    public UserDTO(String firstName, String secondName, Date dateOfBirth) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
