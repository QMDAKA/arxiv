package bk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 19/04/2017.
 */
@Entity
public class Journal {
    @Id
    @GeneratedValue
    Long id;

    String name;
    @JsonIgnore

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "journal", cascade = CascadeType.ALL)
    List<Article> articleList;



    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
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

    public Journal() {
        this.articleList=new ArrayList<>();
    }
}
