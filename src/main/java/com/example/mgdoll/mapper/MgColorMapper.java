package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgColor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgColorMapper {
    int deleteByPrimaryKey(Integer colorId);

    int insert(MgColor record);

    MgColor selectByPrimaryKey(Integer colorId);

    List<MgColor> selectAll();

    int updateByPrimaryKey(MgColor record);
}