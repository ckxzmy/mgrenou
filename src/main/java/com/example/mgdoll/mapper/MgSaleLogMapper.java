package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgSaleLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgSaleLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(MgSaleLog record);

    MgSaleLog selectByPrimaryKey(Integer logId);

    List<MgSaleLog> selectAll();

    int updateByPrimaryKey(MgSaleLog record);
}