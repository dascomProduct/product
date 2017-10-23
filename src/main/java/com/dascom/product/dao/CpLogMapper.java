package com.dascom.product.dao;

import com.dascom.product.entity.CpLog;
import com.dascom.product.entity.CpLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int countByExample(CpLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int deleteByExample(CpLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int insert(CpLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int insertSelective(CpLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    List<CpLog> selectByExample(CpLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    CpLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByExampleSelective(@Param("record") CpLog record, @Param("example") CpLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByExample(@Param("record") CpLog record, @Param("example") CpLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByPrimaryKeySelective(CpLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_log
     *
     * @mbggenerated Mon Oct 16 14:54:31 CST 2017
     */
    int updateByPrimaryKey(CpLog record);
}