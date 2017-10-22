package bk.ulti.impl;

import bk.model.Author;
import bk.model.CoAuthorship;
import bk.repository.ArticleRepository;
import bk.repository.AuthorRepository;
import bk.repository.CoAuthorshipRepository;
import bk.ulti.CoAuthorShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 08/09/2017.
 */
@Service
public class CoAuthorshipImpl implements CoAuthorShipService {

    @Autowired
    CoAuthorshipRepository coAuthorshipRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ArticleRepository articleRepository;


    @Override
    public List<Author> getPartnerAuthor(long id, int year) {
        List<Author> authors = new ArrayList<>();
        for (CoAuthorship coAuthorship : coAuthorshipRepository.findByAuthorId1OrAuthorId2AndYear(id, id, year)) {
            Author author;
            if (coAuthorship.getAuthorId1() != id) {
                author = authorRepository.findOne(coAuthorship.getAuthorId1());
            } else {
                author = authorRepository.findOne(coAuthorship.getAuthorId2());
            }
            if (authors.indexOf(author) == -1) {
                authors.add(author);
            }
        }
        return authors;
    }

    @Override
    public List<Long> getPartnerAuthorBetweenYear(long id, int year1, int year2) {
        List<Long> authors = new ArrayList<>();
        Long idPartner;
        for (CoAuthorship coAuthorship : coAuthorshipRepository.findByAuthorId1OrAuthorId2AndYearBetween(id, id, year1, year2)) {
            Author author;
            if (coAuthorship.getAuthorId1() != id) {
                idPartner = coAuthorship.getAuthorId1();
            } else {
                idPartner = coAuthorship.getAuthorId2();
            }
            if (authors.indexOf(idPartner) == -1) {
                authors.add(idPartner);
            }
        }
        return authors;
    }

    @Override
    public List<Long> getCandidateAuthorBetweenYear(long id, int year1, int year2) {
        List<Long> authors = new ArrayList<>();
        List<Long> candidateAuthors = new ArrayList<>();

        for (CoAuthorship coAuthorship : coAuthorshipRepository.findByAuthorId1OrAuthorId2AndYearBetween(id, id, year1, year2)) {
            Author author;
            long idPartner;
            if (coAuthorship.getAuthorId1() != id) {
                idPartner = coAuthorship.getAuthorId1();
            } else {
                idPartner = coAuthorship.getAuthorId2();
            }
            if (authors.indexOf(idPartner) == -1) {
                Long idCandidate = null;
                authors.add(idPartner);
                for (CoAuthorship coAuthorshipCandidate : coAuthorshipRepository.findByAuthorId1OrAuthorId2AndYearBetween(idPartner, idPartner, year1, year2)) {
                    if (coAuthorshipCandidate.getAuthorId1() != id && coAuthorshipCandidate.getAuthorId1() != idPartner) {
                        idCandidate = coAuthorshipCandidate.getAuthorId1();
                    }
                    else if (coAuthorshipCandidate.getAuthorId2() != id && coAuthorshipCandidate.getAuthorId2() != idPartner) {
                        idCandidate = coAuthorshipCandidate.getAuthorId2();
                    }
                    else{
                        continue;
                    }
                    if(!candidateAuthors.contains(idCandidate)){
                        candidateAuthors.add(idCandidate);
                    }
                }
            }
        }


        return candidateAuthors;
    }

    @Override
    public List<CoAuthorship> getByBetweenYear(int year1, int year2) {
        return coAuthorshipRepository.findByYearBetween(year1, year2);
    }

    @Override
    public List<Long> getCommonNeighbours(long id1, long id2, int year1, int year2) {

        List<Long> common =new ArrayList<>();
        List<Long> listFriendA = this.getPartnerAuthorBetweenYear(id1,year1,year2);
        List<Long> listFriendB = this.getPartnerAuthorBetweenYear(id2,year1,year2);
        for(long idA:listFriendA)
        {
            if(listFriendB.contains(idA)){
                common.add(idA);
            }
        }
        return common;
    }

    @Override
    public List<Long> getTotalNeighbours(long id1, long id2, int year1, int year2) {
        List<Long> common =new ArrayList<>();
        List<Long> total =new ArrayList<>();

        List<Long> listFriendA = this.getPartnerAuthorBetweenYear(id1,year1,year2);
        List<Long> listFriendB = this.getPartnerAuthorBetweenYear(id2,year1,year2);
        listFriendA.removeAll(listFriendB);
        listFriendA.addAll(listFriendB);

        return listFriendA;
    }
}
