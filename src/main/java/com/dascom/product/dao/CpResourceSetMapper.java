package com.dascom.product.dao;

import com.dascom.product.entity.CpResourceSet;
import com.dascom.product.entity.CpResourceSetExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CpResourceSetMapper {
    int countByExample(CpResourceSetExample example);

    int deleteByExample(CpResourceSetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CpResourceSet record);

    int insertSelective(CpResourceSet record);

    List<CpResourceSet> selectByExample(CpResourceSetExample example);

    CpResourceSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CpResourceSet record, @Param("example") CpResourceSetExample example);

    int updateByExample(@Param("record") CpResourceSet record, @Param("example") CpResourceSetExample example);

    int updateByPrimaryKeySelective(CpResourceSet record);

    int updateByPrimaryKey(CpResourceSet record);
    /**
     * 查询 资源类型
     * @param title
     * @return
     */
	CpResourceSet selectByTitle(String title);
}