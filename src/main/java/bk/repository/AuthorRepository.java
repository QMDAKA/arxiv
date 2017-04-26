package bk.repository;

import bk.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by quangminh on 19/04/2017.
 */
@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByName(String name);
}
