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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    List<ArtAuthor> artAuthors;

    /**
     *
     *
     * @return artAuthors
     */
    public List<ArtAuthor> getArtAuthors() {
        return artAuthors;
    }

    public void setArtAuthors(List<ArtAuthor> artAuthors) {
        this.artAuthors = artAuthors;
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
        this.artAuthors=new ArrayList<>();
    }
}
