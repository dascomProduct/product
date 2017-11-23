package com.dascom.product.dao;

import com.dascom.product.entity.CpUser;
import com.dascom.product.entity.CpUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CpUserMapper {
    int countByExample(CpUserExample example);

    int deleteByExample(CpUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CpUser record);

    int insertSelective(CpUser record);

    List<CpUser> selectByExample(CpUserExample example);

    CpUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CpUser record, @Param("example") CpUserExample example);

    int updateByExample(@Param("record") CpUser record, @Param("example") CpUserExample example);

    int updateByPrimaryKeySelective(CpUser record);

    int updateByPrimaryKey(CpUser record);
    /**
     * 查询用户
     * @param username
     * @return
     */
	CpUser selectCpUserByUsername(String username);
	/**
	 * 模糊查询 管理员
	 * @param like
	 * @return
	 */
	List<CpUser> selectByName(@Param("like")String like);
}