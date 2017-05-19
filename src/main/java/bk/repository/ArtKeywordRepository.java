package bk.repository;

import bk.model.Artkeyword;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by quangminh on 13/05/2017.
 */

@RepositoryRestResource
public interface ArtKeywordRepository extends JpaRepository<Artkeyword,Long>{

}
