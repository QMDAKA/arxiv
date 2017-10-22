package bk;

import bk.model.Candidate;
import bk.repository.ArticleRepository;
import bk.repository.CandidateRepository;
import bk.repository.CoAuthorshipRepository;
import bk.repository.KeywordRepository;
import bk.ulti.CandidateService;
import bk.ulti.CoAuthorShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by quangminh on 18/09/2017.
 */
@Component
public class DatabaseLoader5  implements CommandLineRunner {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    KeywordRepository keywordRepository;
    @Autowired
    CoAuthorShipService coAuthorShipService;
    @Autowired
    CoAuthorshipRepository coAuthorshipRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CandidateService candidateService;
    @Override
    public void run(String... args) throws Exception {
        if(true){
            for(Candidate candidate:candidateRepository.findAll()) {
                if (candidate.getCN()==0) {
                    candidate.setAA(candidateService.AAMeasure(candidate.getId(), candidate.getYearStart(), candidate.getYearEnd() ));
                    candidate.setCN(candidateService.CNMeasure(candidate.getId(), candidate.getYearStart(), candidate.getYearEnd()));
                    candidate.setJC(candidateService.JCMeasure(candidate.getId(), candidate.getYearStart(), candidate.getYearEnd()));
                    candidate.setWAA(candidateService.WAAMeasure(candidate.getId(), candidate.getYearStart(), candidate.getYearEnd()));
                    candidate.setWCN(candidateService.WCNMeasure(candidate.getId(), candidate.getYearStart(), candidate.getYearEnd()));
                    candidate.setWJC(candidateService.WJCMeasure(candidate.getId(), candidate.getYearStart(), candidate.getYearEnd()));
                    candidate.setWeight(coAuthorshipRepository.findCommonArticle(candidate.getAuthorId1(), candidate.getAuthorId2(), candidate.getYearStart(), candidate.getYearEnd()));
                    if(coAuthorshipRepository.findCommonArticle(candidate.getAuthorId1(),candidate.getAuthorId2(),1999,2006)>=1){
                        candidate.setLabel(true);
                    }
                    else
                    {
                        candidate.setLabel(false);
                    }
                    candidateRepository.save(candidate);
                    Thread.sleep(500);





                }
            }


        }
    }
}
