package com.example.mgdoll.controller;

import com.alibaba.fastjson.JSON;
import com.example.mgdoll.model.MgDoll;
import com.example.mgdoll.model.ResContent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dollManage")
public class DollManageController {

    @PostMapping("/insert")
    @ResponseBody
    public ResContent insert(@RequestBody MgDoll doll){
        ResContent resContent = new ResContent();

        System.out.println(JSON.toJSON(doll));
        resContent.setStatusCode("OK");
        resContent.setModel(doll);

        return resContent;
    }

}
