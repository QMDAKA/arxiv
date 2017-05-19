package bk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by quangminh on 06/05/2017.
 */
@Entity
public class ArtAuthor {
    @Id
    @GeneratedValue
    Long id;

    int orderAuthor;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, targetEntity = Author.class)
    Author author;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, targetEntity = Article.class)
    Article article;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }


    public int getOrderAuthor() {
        return orderAuthor;
    }

    public void setOrderAuthor(int orderAuthor) {
        this.orderAuthor = orderAuthor;
    }

    public ArtAuthor() {
    }
}
