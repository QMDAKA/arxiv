package bk;

import bk.model.ArtAuthor;
import bk.model.Article;
import bk.model.CoAuthorship;
import bk.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 07/05/2017.
 */
@Component

public class DatabaseLoader2 implements CommandLineRunner {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    CoAuthorshipRepository coAuthorshipRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (false) {
            for (Article article : articleRepository.findAll()) {
                if (article.getCoAuthorships().size() == 0) {
                    List<Long> listAuthor = new ArrayList<>();
                    for (ArtAuthor artAuthor : article.getArtAuthors()) {
                        listAuthor.add(artAuthor.getAuthor().getId());
                    }
                    makeCoRelashionship(listAuthor, article);
                    Thread.sleep(1000);

                }
            }
        }
    }
    void makeCoRelashionship(List<Long> list,Article article){
        if(list.size()==1){
            return;
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    CoAuthorship coAuthorship = new CoAuthorship();
                    coAuthorship.setAuthorId1(list.get(i));
                    coAuthorship.setAuthorId2(list.get(j));
                    coAuthorship.setYear(article.getYear());
                    article.getCoAuthorships().add(coAuthorship);
                    coAuthorship.setArticle(article);
                    coAuthorshipRepository.save(coAuthorship);
                }
            }
        }
    }
}
