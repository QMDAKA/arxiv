package bk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by quangminh on 26/04/2017.
 */
@Entity
public class Artkeyword  {

    @Id
    @GeneratedValue
    Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, targetEntity = Keyword.class)
    Keyword keyword;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, targetEntity = Article.class)
    Article article;

    int frequency;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Artkeyword() {
    }
}
