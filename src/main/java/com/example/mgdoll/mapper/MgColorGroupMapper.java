package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgColorGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgColorGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(MgColorGroup record);

    MgColorGroup selectByPrimaryKey(Integer groupId);

    List<MgColorGroup> selectAll();

    int updateByPrimaryKey(MgColorGroup record);
}