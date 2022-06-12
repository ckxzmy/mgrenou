package com.example.mgdoll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configDate")
public class ConfigDateController {
    private Logger logger = LoggerFactory.getLogger(ConfigDateController.class);

    final String TYPE_TEXT="TEXT";
    final String TYPE_PIC="PIC";


}
