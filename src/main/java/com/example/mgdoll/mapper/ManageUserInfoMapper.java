package com.example.mgdoll.mapper;

import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.vo.ManageUserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManageUserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(ManageUserInfo record);

    ManageUserInfo selectByPrimaryKey(String userId);

    List<ManageUserInfo> selectAll();

    int updateByPrimaryKey(ManageUserInfo record);

    ManageUserInfo selectByInfo(ManageUserInfoVO userInfo);

    int selectExistUserByMobile(ManageUserInfo userInfo);
}