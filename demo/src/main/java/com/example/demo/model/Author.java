package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Author")
public class Author {
    @Id
    private Integer id;
    private String authorName;
    private Integer age;
    private String bookName;

}