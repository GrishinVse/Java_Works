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
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfCompletion;
    private Boolean status;

    // Связи с другими таблицами

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany
    @JoinTable(name="category_task",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="task_id", referencedColumnName="id")
    )
    private Set<Category> categorySet;

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
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
