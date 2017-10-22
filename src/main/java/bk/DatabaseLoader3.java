package bk;

import bk.model.*;
import bk.repository.ArticleRepository;
import bk.repository.KeywordRepository;
import com.uttesh.exude.ExudeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by quangminh on 11/05/2017.
 */
@Component

public class DatabaseLoader3 implements CommandLineRunner{
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    KeywordRepository keywordRepository;
    @Override
    @javax.transaction.Transactional
    public void run(String... args) throws Exception {
        if (false) {
            int pivot =0 ;
            for(int i=2005;i<2006;i++) {
                for (Article article : articleRepository.findByYear(i)) {
                    if (article.getArtkeywords().size() == 0) {
                        String masterString = article.getTitle() + article.getAbastract();
                        Pattern pattern = Pattern.compile("[^a-z A-Z]");
                        Matcher matcher = pattern.matcher(masterString);
                        String nonString = matcher.replaceAll("");
                        String nonStopWords = ExudeData.getInstance().filterStoppingsKeepDuplicates(nonString);
                        List<String> stringList = Arrays.asList(nonStopWords.split(" "));

                        HashMap<String, Integer> map = exchangeMap(stringList);

                        Iterator it = map.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            Keyword keyword = getKeywordbyname(pair.getKey().toString());
                            Artkeyword artkeyword = new Artkeyword();

                            artkeyword.setFrequency((Integer) pair.getValue());
                            artkeyword.setArticle(article);
                            artkeyword.setKeyword(keyword);
                            article.getArtkeywords().add(artkeyword);
                            articleRepository.save(article);

                            it.remove(); // avoids a ConcurrentModificationException
                        }

                    }
                    Thread.sleep(2000);

                }

            }
        }
    }



    Keyword getKeywordbyname(String name) {
        if (keywordRepository.findByName(name) != null) {
            return keywordRepository.findByName(name);
        } else {
            Keyword keyword = new Keyword();
            keyword.setName(name);
            return keyword;
        }
    }

    HashMap<String,Integer> exchangeMap(List<String> stringList){
        HashMap<String, Integer> map = new HashMap<>();

        for (String charc : stringList) {
            if (charc.length() > 1) {
                Integer val = map.get(charc);
                if (val != null) {
                    map.put(charc, new Integer(val + 1));
                } else {
                    map.put(charc, 1);
                }
            }
        }

        return map;
    }

}
