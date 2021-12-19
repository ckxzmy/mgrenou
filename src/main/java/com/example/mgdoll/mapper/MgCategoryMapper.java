package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(MgCategory record);

    MgCategory selectByPrimaryKey(Integer categoryId);

    List<MgCategory> selectAll();

    int updateByPrimaryKey(MgCategory record);
}