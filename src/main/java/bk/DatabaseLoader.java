package bk;

import bk.model.*;
import bk.repository.*;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 19/04/2017.
 */
@Component
public class DatabaseLoader implements CommandLineRunner {
    /*  private final AuthorRepository authorRepository;
      private final ArticleRepository articleRepository;

      @Autowired
      public DatabaseLoader(AuthorRepository authorRepository, ArticleRepository articleRepository) {
          this.authorRepository = authorRepository;
          this.articleRepository = articleRepository;
      }*/
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

    @Override
    @Transactional
    public void run(String... string) {

        Client client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
        if (articleRepository.count() != 0) {

            for (int i = 1999; i < 2000; i++) {
                try {
                    SearchResponse response = client.prepareSearch("arxiv2").setTypes("arxiv" + i).setSize(2000).execute().actionGet();
                    SearchHits searchHits = response.getHits();
                    for (SearchHit hit : searchHits) {
                        Subject subject = getSubjectByName(hit.getSource().get("subject").toString());
                        Journal journal = new Journal();
                        if (hit.getSource().get("journal").toString().length() > 2) {
                            journal = getJournalByName(hit.getSource().get("journal").toString());
                        } else {
                            journal = getJournalByName("blank journal");
                        }

                        Article article = new Article(hit);
                        if(article.getTitle().length()>254)
                        {
                            continue;
                        }
                        articleRepository.save(article);


                        ArrayList<String> authorList = (ArrayList<String>) hit.getSource().get("authors");
                        article.setTotalAuthor(authorList.size());

                        //ArrayList<Author> authors=new ArrayList<>();
                        for (int j = 0; j < 3; j++) {
                            if (j + 1 > authorList.size())
                                break;
                            else {
                                Author author = getAuthorByName(authorList.get(j));
                                author.getArticleList().add(article);
                                authorRepository.save(author);
                                article.getAuthorList().add(author);
                                articleRepository.saveAndFlush(article);


                            }

                        }
                        article.setSubject(subject);
                        subject.getArticleList().add(article);
                        subjectRepository.save(subject);
                        article.setJournal(journal);
                        journal.getArticleList().add(article);
                        journalRepository.save(journal);

                        Url url = new Url(hit);
                        urlRepository.save(url);
                        if (articleRepository.findAll().size() % 100 == 0) {
                            Thread.sleep(10000);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }


    }

    Subject getSubjectByName(String name) {
        if (subjectRepository.findByName(name) != null) {
            return subjectRepository.findByName(name);
        } else {
            Subject subject = new Subject();
            subject.setName(name);
            return subject;
        }
    }

    Author getAuthorByName(String name) {
        if (authorRepository.findByName(name) != null) {
            return authorRepository.findByName(name);
        } else {

            Author author = new Author();
            author.setName(name);
            return author;
        }
    }

    Journal getJournalByName(String name) {
        if (journalRepository.findByName(name) != null) {
            return journalRepository.findByName(name);
        } else {

            Journal journal = new Journal();
            journal.setName(name);
            return journal;
        }
    }

}
