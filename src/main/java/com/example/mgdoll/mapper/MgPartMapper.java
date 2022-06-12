package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgPart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgPartMapper {
    int deleteByPrimaryKey(Integer partId);

    int insert(MgPart record);

    MgPart selectByPrimaryKey(Integer partId);

    List<MgPart> selectAll();

    int updateByPrimaryKey(MgPart record);

    List<MgPart> queryPartListByDollId(Integer dollId);
}