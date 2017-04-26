package bk.repository;

import bk.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by quangminh on 19/04/2017.
 */
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article,Long>{

    List<Article> findByYear(int year);
}
