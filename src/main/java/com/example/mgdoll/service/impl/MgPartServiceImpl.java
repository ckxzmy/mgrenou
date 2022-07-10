package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.*;
import com.example.mgdoll.model.*;
import com.example.mgdoll.service.MgPartService;
import com.example.mgdoll.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MgPartServiceImpl implements MgPartService {
    @Autowired
    private MgPartMapper partMapper;
    @Autowired
    private MgCategoryMapper categoryMapper;
    @Autowired
    private MgMaterialMapper materialMapper;
    @Autowired
    private MgColorMapper colorMapper;
    @Autowired
    private MgColorGroupMapper colorGroupMapper;

    @Override
    public void insertPart(List<MgPart> partList, String token, MgDoll doll) {
        String userId = getUserIdByToken(token);
        Map<String, Integer> colorGroupMap = new HashMap<>();
        Map<String, Integer> colorMap = new HashMap<>();
        if(doll.getColorList() != null && doll.getColorList().size()>0){
            for(MgColorGroup mgColorGroup: doll.getColorList()){
                colorGroupMap.put(mgColorGroup.getGroupName(), mgColorGroup.getGroupId());
                if(mgColorGroup.getColorArray()!=null&&mgColorGroup.getColorArray().size()>0){
                    for(MgColor mgColor: mgColorGroup.getColorArray()){
                        String key = mgColorGroup.getGroupId() + mgColor.getColorName();
                        colorMap.put(key, mgColor.getColorId());
                    }
                }
            }
        }
        for(MgPart part: partList){
            this.addNewPart(part,userId,doll,colorGroupMap,colorMap);
        }
    }

    private void addNewPart(MgPart part, String userId, MgDoll doll, Map<String, Integer> colorGroupMap, Map<String, Integer> colorMap) {
        part.setInsertTime(new Date());
        part.setInsertBy(userId);
        part.setDollId(doll.getDollId());
        part.setColorGroupId(colorGroupMap.get(part.getColorGroupName()));
        int partNum = partMapper.insert(part);
        if(partNum > 0){
            if(part.getCategoryList() != null && part.getCategoryList().size() > 0){
                for(MgCategory mgCategory: part.getCategoryList()){
                    this.addNewCategory(mgCategory,part,userId,colorMap);

                }
            }
        }
    }

    private void addNewCategory(MgCategory mgCategory, MgPart part, String userId, Map<String, Integer> colorMap) {
        mgCategory.setPartId(part.getPartId());
        mgCategory.setInsertTime(new Date());
        mgCategory.setInsertBy(userId);
        mgCategory.setOwnerId(userId);
        int categoryNum = categoryMapper.insert(mgCategory);
        if(categoryNum > 0 && mgCategory.getMaterialList() != null && mgCategory.getMaterialList().size() >0){
            for(MgMaterial mgMaterial: mgCategory.getMaterialList()){
                this.addNewMaterical(mgMaterial,mgCategory,part,userId,colorMap);
            }
        }
    }

    private void addNewMaterical(MgMaterial mgMaterial,MgCategory mgCategory, MgPart part, String userId, Map<String, Integer> colorMap) {
        mgMaterial.setCategoryId(mgCategory.getCategoryId());
        mgMaterial.setInsertTime(new Date());
        mgMaterial.setInsertBy(userId);
        String key = part.getColorGroupId() + mgMaterial.getColorName();
        mgMaterial.setColorId(colorMap.get(key));
        materialMapper.insert(mgMaterial);
    }

    @Override
    public List<MgPart> queryPartListByDollId(Integer dollId) {
        List<MgPart> partList = new ArrayList<>();
        partList = partMapper.queryPartListByDollId(dollId);
        int layerId = 0;
        if(partList != null&& partList.size()>0){
            for(MgPart part: partList){
                List<MgCategory> categoryList = categoryMapper.queryCategoryListByPartId(part.getPartId());
                if(categoryList != null&& categoryList.size()>0){
                    for(MgCategory mgCategory: categoryList){
                        List<MgMaterial> mgMaterialList = materialMapper.querymgMaterialListByCategoryId(mgCategory.getCategoryId());
                        if(mgMaterialList != null&& mgMaterialList.size()>0){
                            for(MgMaterial mgMaterial: mgMaterialList){
                                if(layerId < mgMaterial.getLayerLevel()){
                                    layerId = mgMaterial.getLayerLevel();
                                }
                                if(mgMaterial.getColorId() != null){
                                    MgColor mgColor = colorMapper.selectByPrimaryKey(mgMaterial.getColorId());
                                    mgMaterial.setColorName(mgColor.getColorName());
                                }
                            }
                        }
                        mgCategory.setMaterialList(mgMaterialList);
                    }
                }
                List<LayerModel> layerList = new ArrayList<>();
                for(int i=1; i<=layerId; i++){
                    LayerModel layer = new LayerModel();
                    layer.setId(i+"");
                    layer.setValue(i+"");
                    layerList.add(layer);
                }
                if(part.getColorGroupId() != null){
                    MgColorGroup mgColorGroup = colorGroupMapper.selectByPrimaryKey(part.getColorGroupId());
                    part.setColorGroupName(mgColorGroup.getGroupName());
                }
                part.setCategoryList(categoryList);
                part.setLayerList(layerList);
            }
        }
        return partList;
    }

    @Override
    public void updatePart(List<MgPart> partList, String token, MgDoll doll) {
        String userId = getUserIdByToken(token);
        Map<String, Integer> colorGroupMap = new HashMap<>();
        Map<String, Integer> colorMap = new HashMap<>();
        if(doll.getColorList() != null && doll.getColorList().size()>0){
            for(MgColorGroup mgColorGroup: doll.getColorList()){
                colorGroupMap.put(mgColorGroup.getGroupName(), mgColorGroup.getGroupId());
                if(mgColorGroup.getColorArray()!=null&&mgColorGroup.getColorArray().size()>0){
                    for(MgColor mgColor: mgColorGroup.getColorArray()){
                        String key = mgColorGroup.getGroupId() + mgColor.getColorName();
                        colorMap.put(key, mgColor.getColorId());
                    }
                }
            }
        }
        for(MgPart part: partList){
            if(part.getPartId() == null){
                this.addNewPart(part,userId,doll,colorGroupMap,colorMap);
            }else {
                partMapper.updateByPrimaryKey(part);
                if(part.getCategoryList() != null && part.getCategoryList().size() > 0) {
                    for (MgCategory mgCategory : part.getCategoryList()) {
                        if(mgCategory.getCategoryId() == null){
                            this.addNewCategory(mgCategory,part,userId,colorMap);
                        }else {
                            categoryMapper.updateByPrimaryKey(mgCategory);
                            if(mgCategory.getMaterialList() != null && mgCategory.getMaterialList().size() >0){
                                for(MgMaterial mgMaterial: mgCategory.getMaterialList()){
                                    if(mgMaterial.getMaterialId() == null){
                                        this.addNewMaterical(mgMaterial,mgCategory,part,userId,colorMap);
                                    }else {
                                        materialMapper.updateByPrimaryKey(mgMaterial);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String getUserIdByToken(String token) {
        return JwtUtil.getUserId(token);
    }
}
