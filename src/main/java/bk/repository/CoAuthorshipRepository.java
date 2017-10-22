package bk.repository;

import bk.model.CoAuthorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by quangminh on 07/05/2017.
 */
public interface CoAuthorshipRepository extends JpaRepository<CoAuthorship, Long> {
    List<CoAuthorship> findByYear(int year);

    List<CoAuthorship> findByYearBetween(int year1, int year2);


    @Query(value = "select * from co_authorship where (author_id1=?1 or author_id2= ?2) and year = ?3",
            nativeQuery = true)
    List<CoAuthorship> findByAuthorId1OrAuthorId2AndYear(long id, long id2, int year);


    @Query(value = "select * from co_authorship where (author_id1=?1 or author_id2= ?2) and (year >= ?3 and year <= ?4 )",
            nativeQuery = true)
    List<CoAuthorship> findByAuthorId1OrAuthorId2AndYearBetween(long id, long id2, int year1, int year2);


    @Query(value = "SELECT count(*) from co_authorship where ((author_id1=?1 and author_id2=?2) or (author_id1=?2 and author_id2=?1)) and (year>=?3 and year<=?4)",
            nativeQuery = true)
    int findCommonArticle(long id, long id2, int year1, int year2);


}
