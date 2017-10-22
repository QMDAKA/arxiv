package bk.controller;

import bk.model.Author;
import bk.ulti.CoAuthorShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by quangminh on 23/05/2017.
 */
@RestController
public class AuthorController {

    @Autowired
    CoAuthorShipService coAuthorShipService;

    @RequestMapping(path = "/authors/co/{id}/{year1}/{year2}", method = RequestMethod.GET)
    ResponseEntity<?> getCoAuthorById(
        @PathVariable(value = "id") long id,
        @PathVariable(value = "year1") int year1,
        @PathVariable(value = "year2") int year2

    ){
        //CoAuthorshipImpl coAuthorshipUlti=new CoAuthorshipImpl();
        List<Long> authorList=coAuthorShipService.getCandidateAuthorBetweenYear(id,year1,year2);
        if(authorList.size()==0)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(authorList);
    }


}
