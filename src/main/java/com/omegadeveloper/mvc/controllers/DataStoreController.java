package com.omegadeveloper.mvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liviu on 2/23/2016.
 */

@Controller
@Component
public class DataStoreController {

    @Autowired
    ConfigurableApplicationContext context;

    @RequestMapping(value="/storeEntity", method = RequestMethod.POST)
    public String soreEntity(Model model){

        //returns the view name
        return "datastore";
    }

    @RequestMapping(value="/getEntity", method = RequestMethod.GET)
    public String soreEntity(@RequestParam String entityId){

        //returns the view name
        return "datastore";
    }

}
