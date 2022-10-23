package com.example.mgdoll.mapper;

import com.example.mgdoll.model.MgSuitDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MgSuitDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(MgSuitDetail record);

    MgSuitDetail selectByPrimaryKey(Integer detailId);

    List<MgSuitDetail> selectAll();

    int updateByPrimaryKey(MgSuitDetail record);
}