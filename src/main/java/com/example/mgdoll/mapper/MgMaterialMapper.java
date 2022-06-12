package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgMaterial;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgMaterialMapper {
    int deleteByPrimaryKey(Integer materialId);

    int insert(MgMaterial record);

    MgMaterial selectByPrimaryKey(Integer materialId);

    List<MgMaterial> selectAll();

    int updateByPrimaryKey(MgMaterial record);

    List<MgMaterial> querymgMaterialListByCategoryId(Integer categoryId);
}