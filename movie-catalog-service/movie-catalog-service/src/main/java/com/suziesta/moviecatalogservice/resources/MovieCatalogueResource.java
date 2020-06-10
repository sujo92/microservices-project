package com.suziesta.moviecatalogservice.resources;


import com.suziesta.moviecatalogservice.models.CatalogueItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogueResource {

    @RequestMapping("/{userId}")
    public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId){
        return Collections.singletonList(
                new CatalogueItem("Transformers","Test",4)
        );
    }
}
