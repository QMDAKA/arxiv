package bk.repository;

import bk.model.Candidate;
import bk.model.CoAuthorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by quangminh on 14/09/2017.
 */
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    @Query(value = "select * from candidate where ((author_id1=?1 and author_id2= ?2)or(author_id1=?2 and author_id2=?1)) and (year_start=?3)",
            nativeQuery = true)
    List<Candidate> findByAuthorId1OrAuthorId2AndYearStart(long id, long id2, int year);


    List<Candidate> findByYearStart(int year);
}
