package com.example.mgdoll.controller;

import com.alibaba.fastjson.JSON;
import com.example.mgdoll.model.MgColorGroup;
import com.example.mgdoll.model.MgDoll;
import com.example.mgdoll.model.MgPart;
import com.example.mgdoll.model.ResContent;
import com.example.mgdoll.service.MgColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dollManage")
public class DollManageController {

    @Autowired
    private MgColorService colorService;

    @PostMapping("/insert")
    @ResponseBody
    public ResContent insert(@RequestBody MgDoll doll){
        ResContent resContent = new ResContent();
        if(doll != null){
            List<MgColorGroup> colorGroupList = doll.getColorList();
            if(colorGroupList != null && colorGroupList.size()>0){
                colorService.insertColor(colorGroupList);
            }
            List<MgPart> partList = doll.getPartList();
        }

        System.out.println(JSON.toJSON(doll));
        resContent.setStatusCode("OK");
        resContent.setModel(doll);

        return resContent;
    }

}
