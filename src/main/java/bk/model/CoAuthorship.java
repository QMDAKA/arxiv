package bk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by quangminh on 06/05/2017.
 */
@Entity
public class CoAuthorship {
    @Id
    @GeneratedValue
    Long id;

    Long authorId1;
    Long authorId2;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, targetEntity = Article.class)
    Article article;

    int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId1() {
        return authorId1;
    }

    public void setAuthorId1(Long authorId1) {
        this.authorId1 = authorId1;
    }

    public Long getAuthorId2() {
        return authorId2;
    }

    public void setAuthorId2(Long authorId2) {
        this.authorId2 = authorId2;
    }

    public CoAuthorship() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
