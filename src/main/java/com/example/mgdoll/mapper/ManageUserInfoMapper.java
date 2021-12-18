package com.example.mgdoll.mapper;

import com.example.mgdoll.model.ManageUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ManageUserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(ManageUserInfo record);

    ManageUserInfo selectByPrimaryKey(Integer userId);

    List<ManageUserInfo> selectAll();

    int updateByPrimaryKey(ManageUserInfo record);

    ManageUserInfo selectByInfo(ManageUserInfo userInfo);
}