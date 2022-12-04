package com.example.mgdoll.mapper;

import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.vo.AppUserInfoVO;
import com.example.mgdoll.vo.UserSearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppUserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(AppUserInfoVO record);

    AppUserInfo selectByPrimaryKey(String userId);

    List<AppUserInfo> selectAll();

    int updateByPrimaryKey(AppUserInfoVO record);

    AppUserInfo selectByInfo(AppUserInfoVO userInfo);

    int selectExistUserByMobile(AppUserInfoVO userInfo);

    List<AppUserInfo> queryBySearchCondition(UserSearchVO userSearchVO);
}