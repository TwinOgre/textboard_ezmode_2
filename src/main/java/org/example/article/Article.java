package org.example.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Article {
    int id;
    String title;
    String content;
    String regDate;
    String author;

}
