package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgConfigData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgConfigDataMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(MgConfigData record);

    MgConfigData selectByPrimaryKey(Integer configId);

    List<MgConfigData> selectAll();

    int updateByPrimaryKey(MgConfigData record);
}