package bk.ulti;

import bk.model.Author;
import bk.model.CoAuthorship;

import java.util.List;

/**
 * Created by quangminh on 08/09/2017.
 */
public interface CoAuthorShipService {
    List<Author> getPartnerAuthor(long id,int year);
    List<Long> getPartnerAuthorBetweenYear(long id,int year1,int year2);
    List<Long> getCandidateAuthorBetweenYear(long id,int year1,int year2);
    List<CoAuthorship> getByBetweenYear(int year1,int year2);
    List<Long> getCommonNeighbours(long id1,long id2,int year1,int year2);
    List<Long> getTotalNeighbours(long id1,long id2,int year1,int year2);
}
