package bk;

import bk.model.CoAuthorship;
import bk.repository.ArticleRepository;
import bk.repository.CandidateRepository;
import bk.repository.CoAuthorshipRepository;
import bk.repository.KeywordRepository;
import bk.ulti.CandidateService;
import bk.ulti.CoAuthorShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 13/09/2017.
 */
@Component
public class DatabaseLoader4 implements CommandLineRunner {
        @Autowired
        ArticleRepository articleRepository;
        @Autowired
        KeywordRepository keywordRepository;
        @Autowired
        CoAuthorShipService coAuthorShipService;
        @Autowired
        CoAuthorshipRepository coAuthorshipRepository;
        @Autowired
        CandidateService candidateService;
        @Override
        public void run(String... args) throws Exception {
            if (false) {
                List<Long> checkedIdAuthor = new ArrayList<>();
                int yearStart=1992;
                int yearEnd = 1998;
                List<CoAuthorship> coAuthorshipList = coAuthorShipService.getByBetweenYear(yearStart,yearEnd);
                for (CoAuthorship coAuthorship : coAuthorshipList) {
                    if (!checkedIdAuthor.contains(coAuthorship.getAuthorId1())) {
                        checkedIdAuthor.add(coAuthorship.getAuthorId1());
                        candidateService.saveCandidateFromPartnerResult(coAuthorship.getAuthorId1(),yearStart,yearEnd);

                    }
                    if (!checkedIdAuthor.contains(coAuthorship.getAuthorId2())) {
                        checkedIdAuthor.add(coAuthorship.getAuthorId2());
                        candidateService.saveCandidateFromPartnerResult(coAuthorship.getAuthorId1(),yearStart,yearEnd);
                    }
                    Thread.sleep(1000);
                }

            }
        }


    }

