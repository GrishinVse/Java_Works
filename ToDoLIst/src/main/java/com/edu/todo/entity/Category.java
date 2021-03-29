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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Связи с другими таблицами

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorySet") // <-
    private Set<Task> taskSet;

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
