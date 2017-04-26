package bk.controller;

import bk.model.Article;
import bk.model.Author;
import bk.repository.ArticleRepository;
import bk.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by quangminh on 26/04/2017.
 */
@RestController
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    AuthorRepository authorRepository;


    @RequestMapping(path = "/articles", method = RequestMethod.GET)
    ResponseEntity<?> getListNew(
            @RequestParam(value = "year", required = false, defaultValue = "0") int year,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "500") int size    ) {
        Pageable pageable = new PageRequest(page, size);

        List<Article> articles=articleRepository.findByYear(year);
        HashMap<String, Object> content=new HashMap<>();
        ArrayList<HashMap<String, Object>> mapArrayList = new ArrayList<>();

        if(articles.size()>0)
        {
            for(Article article:articles){
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id",article.getId());
                hashMap.put("title",article.getTitle());
                hashMap.put("year",article.getYear());
                hashMap.put("Abastract",article.getAbastract());
                hashMap.put("code",article.getCode());
                mapArrayList.add(hashMap);
            }
            content.put("content", mapArrayList);
            content.put("totalPage",articles.size());
        }
        return ResponseEntity.ok(content);


    }


}
