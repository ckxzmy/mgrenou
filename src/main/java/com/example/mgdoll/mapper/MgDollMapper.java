package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgDoll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgDollMapper {
    int deleteByPrimaryKey(Integer dollId);

    int insert(MgDoll record);

    MgDoll selectByPrimaryKey(Integer dollId);

    List<MgDoll> selectAll();

    int updateByPrimaryKey(MgDoll record);

    List<MgDoll> queryDoll(MgDoll mgDoll);
}