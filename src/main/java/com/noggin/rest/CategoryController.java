package com.noggin.rest;

import com.noggin.models.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public List<Category> getAll() {
        List<Category> list = new ArrayList<Category>();

        Category c1 = new Category();
        c1.setId(1);
        c1.setName("Beletristika");

        Category c2 = new Category();
        c2.setId(2);
        c2.setName("Autobiografija");

        Category c3 = new Category();
        c3.setId(3);
        c3.setName("Akcija");

        Category c4 = new Category();
        c4.setId(4);
        c4.setName("Poezija");

        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);

        return list;
    }

}
