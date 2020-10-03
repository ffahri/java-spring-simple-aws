package com.webischia.api.controllers;

import com.webischia.api.services.TestModelService;
import com.webischia.api.services.TestModelServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import testModel.model.TestModel;

@RestController
@RequestMapping("/")
public class GatewayController {
    Logger logger = LoggerFactory.getLogger(GatewayController.class);
    private TestModelService testModelService;

    public GatewayController(TestModelServiceImpl testModelService) {
        this.testModelService = testModelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void welcome() {
    }

    @RequestMapping(method = RequestMethod.POST, path = "/send")
    public String saveMessage(@RequestBody TestModel model) {
        if (testModelService.sendQueue(model)) {
            //TODO return json and create a class like MessageResponse
            return "OK";
        } else {
            return "Nope";
        }
    }
}
