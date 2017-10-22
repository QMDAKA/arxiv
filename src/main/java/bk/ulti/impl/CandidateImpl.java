package bk.ulti.impl;

import bk.model.Candidate;
import bk.model.CoAuthorship;
import bk.repository.CandidateRepository;
import bk.repository.CoAuthorshipRepository;
import bk.ulti.CandidateService;
import bk.ulti.CoAuthorShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by quangminh on 18/09/2017.
 */
@Service
public class CandidateImpl implements CandidateService {
    @Autowired
    CoAuthorShipService coAuthorShipService;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CoAuthorshipRepository coAuthorshipRepository;

    @Override
    public void saveCandidateFromPartnerResult(long id,int yearStart,int yearEnd) {
        List<Long> coAuthorshipList = coAuthorShipService.getCandidateAuthorBetweenYear(id,yearStart,yearEnd);
        for(long idCandidate: coAuthorshipList){
            if(candidateRepository.findByAuthorId1OrAuthorId2AndYearStart(id,idCandidate,yearStart).size()==0){
                Candidate candidate = new Candidate();
                candidate.setAuthorId1(id);
                candidate.setAuthorId2(idCandidate);
                candidate.setYearStart(yearStart);
                candidate.setYearEnd(yearEnd);
                candidateRepository.save(candidate);
            }
            else
            {
                System.out.println("Yo Yo");
            }

        }
    }

    @Override
    public double CNMeasure(long id,int year1,int year2) {
        Candidate candidate = candidateRepository.findOne(id);
        return coAuthorShipService.getCommonNeighbours(candidate.getAuthorId1(),candidate.getAuthorId2(),year1,year2).size();
    }

    @Override
    public double AAMeasure(long id, int year1, int year2) {
        Candidate candidate = candidateRepository.findOne(id);
        List<Long> listCommon=coAuthorShipService.getCommonNeighbours(candidate.getAuthorId1(),candidate.getAuthorId2(),year1,year2);
        double result=0;
        for(long idC:listCommon){
            int countFriend = coAuthorShipService.getPartnerAuthorBetweenYear(idC,year1,year2).size();
            result += 1/(Math.log(countFriend));
        }
        return result;
    }

    @Override
    public double JCMeasure(long id, int year1, int year2) {
        Candidate candidate = candidateRepository.findOne(id);
        long author1 = candidate.getAuthorId1();
        long author2 = candidate.getAuthorId2();
        double common = coAuthorShipService.getCommonNeighbours(author1,author2,year1,year2).size();
        double total = coAuthorShipService.getTotalNeighbours(author1,author2,year1,year2).size();
        return common/total;
    }

    @Override
    public double WCNMeasure(long id, int year1, int year2) {
        Candidate candidate = candidateRepository.findOne(id);
        long author1 = candidate.getAuthorId1();
        long author2 = candidate.getAuthorId2();
        double wcn =0;
        for(long idCommon: coAuthorShipService.getCommonNeighbours(candidate.getAuthorId1(),candidate.getAuthorId2(),year1,year2)){
            wcn += (coAuthorshipRepository.findCommonArticle(author1,idCommon,year1,year2)+coAuthorshipRepository.findCommonArticle(author2,idCommon,year1,year2))/2.0;
        }
        return wcn;
    }

    @Override
    public double WAAMeasure(long id, int year1, int year2) {
        Candidate candidate = candidateRepository.findOne(id);
        long author1 = candidate.getAuthorId1();
        long author2 = candidate.getAuthorId2();
        double waa =0;
        for(long idCommon: coAuthorShipService.getCommonNeighbours(candidate.getAuthorId1(),candidate.getAuthorId2(),year1,year2)){
            double logTotal=0;
            for(long idOfPartnerCommon:coAuthorShipService.getPartnerAuthorBetweenYear(idCommon,year1,year2)){
                logTotal += coAuthorshipRepository.findCommonArticle(idCommon,idOfPartnerCommon,year1,year2);
            }
            logTotal = 1/(Math.log(logTotal));
            double averageOfTwoWeight =((coAuthorshipRepository.findCommonArticle(author1,idCommon,year1,year2)+coAuthorshipRepository.findCommonArticle(author2,idCommon,year1,year2))/2.0);
            waa +=  averageOfTwoWeight*logTotal;
        }
        return waa;
    }

    @Override
    public double WJCMeasure(long id, int year1, int year2) {
        Candidate candidate = candidateRepository.findOne(id);
        long author1 = candidate.getAuthorId1();
        long author2 = candidate.getAuthorId2();
        double wcn=this.WCNMeasure(id,year1,year2);
        double totalA=0;
        double totalB=0;
        for(long idOfPartner:coAuthorShipService.getPartnerAuthorBetweenYear(author1,year1,year2)){
            totalA += coAuthorshipRepository.findCommonArticle(author1,idOfPartner,year1,year2);
        }
        for(long idOfPartner:coAuthorShipService.getPartnerAuthorBetweenYear(author2,year1,year2)){
            totalB += coAuthorshipRepository.findCommonArticle(author2,idOfPartner,year1,year2);
        }
        double wjc = wcn/(totalA+totalB);
        return wjc;
    }


}
