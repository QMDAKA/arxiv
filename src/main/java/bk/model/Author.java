package bk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 19/04/2017.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    List<Article> articles;

    public List<Article> getArticleList() {
        return articles;
    }

    public void setArticleList(List<Article> articleList) {
        this.articles = articleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Author(){
        this.articles=new ArrayList<>();
    }
}
