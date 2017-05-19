package bk.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 26/04/2017.
 */
@Entity
public class Keyword {

    @Id
    @GeneratedValue
    Long id;
    int status;

    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "keyword", cascade = CascadeType.ALL)
    List<Artkeyword> artkeywords;

    public List<Artkeyword> getArtkeywords() {
        return artkeywords;
    }

    public void setArtkeywords(List<Artkeyword> artkeywords) {
        this.artkeywords = artkeywords;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Keyword() {
        this.artkeywords=new ArrayList<>();
    }
}
