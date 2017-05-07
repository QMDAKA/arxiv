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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "article", cascade = CascadeType.ALL)
    List<ArtAuthor> artAuthors;

    public List<ArtAuthor> getArtAuthors() {
        return artAuthors;
    }

    public void setArtAuthors(List<ArtAuthor> artAuthors) {
        this.artAuthors = artAuthors;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Subject.class)
    Subject subject;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, targetEntity = Journal.class)
    Journal journal;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "article", cascade = CascadeType.ALL)
    List<Artkeyword> artkeywords;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL)
    List<CoAuthorship> coAuthorships;

    public List<CoAuthorship> getCoAuthorships() {
        return coAuthorships;
    }

    public void setCoAuthorships(List<CoAuthorship> coAuthorships) {
        this.coAuthorships = coAuthorships;
    }

    public List<Artkeyword> getArtkeywords() {
        return artkeywords;
    }

    public void setArtkeywords(List<Artkeyword> keywords) {
        this.artkeywords = keywords;
    }

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
        this.artAuthors=new ArrayList<>();
    }
    public Article(SearchHit hit){
        this.setTitle(hit.getSource().get("title").toString());
        this.setAbastract(hit.getSource().get("abstractPost").toString());
        this.setUrl(hit.getSource().get("url").toString());
        this.setYear((Integer) hit.getSource().get("year"));
        this.setCode(hit.getSource().get("code").toString());
        this.artAuthors=new ArrayList<>();

    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
