package com.eyuel.berga.restdesignpatterndemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RequestMapping(path = "/api/news", produces="application/json" )
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private NewsRepository newsRepo;

    @GetMapping
    public List<NewsDomain> getAllNews(){
        List<NewsDomain> all = (List<NewsDomain>) newsRepo.findAll();
        return all;

    }
    @GetMapping("/{id}")
    public ResponseEntity<NewsDomain> getById(@PathVariable("id") Long id){
        NewsDomain news  = newsRepo.findById(id).get();

        if(news != null) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        }
        return new ResponseEntity<>(news, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDomain save(@RequestBody NewsDomain news) {
        return newsRepo.save(news);
    }

    @GetMapping("/recent")
    public Iterable<NewsDomain> recentNews(@RequestParam(name="page",defaultValue="0") int page, @RequestParam(name="size", defaultValue="3") int size) {

        PageRequest pageRequest = PageRequest.of(page,size, Sort.by("createdAt").descending());
        return newsRepo.findAll(pageRequest).getContent();
    }
}
