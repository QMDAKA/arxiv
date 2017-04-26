package bk.model;

import org.elasticsearch.search.SearchHit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by quangminh on 19/04/2017.
 */
@Entity
public class Url {
    @Id
    @GeneratedValue
    Long id;

    String title;
    String url;
    String subject;
    String journal;
    int year;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Url(SearchHit hit){
        this.setTitle(hit.getSource().get("title").toString());
        this.setJournal(hit.getSource().get("journal").toString());
        this.setUrl(hit.getSource().get("url").toString());
        this.setYear((Integer) hit.getSource().get("year"));
        this.setSubject(hit.getSource().get("subject").toString());
    }
}
