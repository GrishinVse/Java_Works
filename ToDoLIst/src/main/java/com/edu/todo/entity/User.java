package com.edu.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String lastName;
    private String firstName;
    private String fatherName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    // Связи с другими таблицами

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Task> taskSet;

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
    }

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate creationDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate updateDate;

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public LocalDate getUpdateDate(){
        return updateDate;
    }

}
