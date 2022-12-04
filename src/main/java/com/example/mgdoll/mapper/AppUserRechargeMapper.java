package com.example.mgdoll.mapper;

import com.example.mgdoll.model.AppUserRecharge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppUserRechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppUserRecharge record);

    AppUserRecharge selectByPrimaryKey(Integer id);

    List<AppUserRecharge> selectAll();

    int updateByPrimaryKey(AppUserRecharge record);
}