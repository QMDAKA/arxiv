package bk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.elasticsearch.search.SearchHit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by quangminh on 19/04/2017.
 */
@Entity
public class Article {

    @Id
    @GeneratedValue
    Long id;
    @Column(columnDefinition = "TEXT",unique = true)
    String title;

    Date time;
    int year;
    String code;
    @Column(columnDefinition = "TEXT")
    String Abastract;

    int totalAuthor;
    String url;

    int idiot;

    public int getIdiot() {
        return idiot;
    }

    public void setIdiot(int idiot) {
        this.idiot = idiot;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "article_authors", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    List<Author> authors;

    public List<Author> getAuthorList() {
        return authors;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authors = authorList;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Subject.class)
    Subject subject;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Journal.class)
    Journal journal;

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAbastract() {
        return Abastract;
    }

    public void setAbastract(String abastract) {
        Abastract = abastract;
    }

    public int getTotalAuthor() {
        return totalAuthor;
    }

    public void setTotalAuthor(int totalAuthor) {
        this.totalAuthor = totalAuthor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Article() {
        this.authors=new ArrayList<>();
    }
    public Article(SearchHit hit){
        this.setTitle(hit.getSource().get("title").toString());
        this.setAbastract(hit.getSource().get("abstractPost").toString());
        this.setUrl(hit.getSource().get("url").toString());
        this.setYear((Integer) hit.getSource().get("year"));
        this.setCode(hit.getSource().get("code").toString());
        this.authors=new ArrayList<>();

    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
