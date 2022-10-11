package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgWaterMark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgWaterMarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MgWaterMark record);

    MgWaterMark selectByPrimaryKey(Integer id);

    List<MgWaterMark> selectAll();

    int updateByPrimaryKey(MgWaterMark record);

    List<MgWaterMark> queryWaterListByUserId(String userId);

    int updateStatus(MgWaterMark waterMark);

    List<MgWaterMark> queryWaterListByStatus(Integer status);
}