package bk.repository;

import bk.model.Keyword;
import bk.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by quangminh on 13/05/2017.
 */
@RepositoryRestResource
public interface KeywordRepository extends JpaRepository<Keyword,Long>{
    Keyword findByName(String name);

}
