package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgSuit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgSuitMapper {
    int deleteByPrimaryKey(Integer suitId);

    int insert(MgSuit record);

    MgSuit selectByPrimaryKey(Integer suitId);

    List<MgSuit> selectAll();

    int updateByPrimaryKey(MgSuit record);
}