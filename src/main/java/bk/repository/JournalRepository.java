package bk.repository;

import bk.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by quangminh on 19/04/2017.
 */
@RepositoryRestResource
public interface JournalRepository extends JpaRepository<Journal,Long>{
    Journal findByName(String name);
}
