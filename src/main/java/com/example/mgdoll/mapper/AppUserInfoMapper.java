package com.example.mgdoll.mapper;

import com.example.mgdoll.model.AppUserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppUserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(AppUserInfo record);

    AppUserInfo selectByPrimaryKey(Integer userId);

    List<AppUserInfo> selectAll();

    int updateByPrimaryKey(AppUserInfo record);
}