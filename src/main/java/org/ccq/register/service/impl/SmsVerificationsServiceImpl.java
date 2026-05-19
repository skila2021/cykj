package org.ccq.register.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ccq.register.mapper.SmsVerificationsMapper;
import org.ccq.register.domain.SmsVerifications;
import org.ccq.register.service.ISmsVerificationsService;

/**
 * 注册、成功Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
@Service
public class SmsVerificationsServiceImpl implements ISmsVerificationsService 
{
    @Autowired
    private SmsVerificationsMapper smsVerificationsMapper;

    /**
     * 查询注册、成功
     * 
     * @param id 注册、成功主键
     * @return 注册、成功
     */
    @Override
    public SmsVerifications selectSmsVerificationsById(Long id)
    {
        return smsVerificationsMapper.selectSmsVerificationsById(id);
    }

    /**
     * 查询注册、成功列表
     * 
     * @param smsVerifications 注册、成功
     * @return 注册、成功
     */
    @Override
    public List<SmsVerifications> selectSmsVerificationsList(SmsVerifications smsVerifications)
    {
        return smsVerificationsMapper.selectSmsVerificationsList(smsVerifications);
    }

    /**
     * 新增注册、成功
     * 
     * @param smsVerifications 注册、成功
     * @return 结果
     */
    @Override
    public int insertSmsVerifications(SmsVerifications smsVerifications)
    {
        return smsVerificationsMapper.insertSmsVerifications(smsVerifications);
    }

    /**
     * 修改注册、成功
     * 
     * @param smsVerifications 注册、成功
     * @return 结果
     */
    @Override
    public int updateSmsVerifications(SmsVerifications smsVerifications)
    {
        return smsVerificationsMapper.updateSmsVerifications(smsVerifications);
    }

    /**
     * 批量删除注册、成功
     * 
     * @param ids 需要删除的注册、成功主键
     * @return 结果
     */
    @Override
    public int deleteSmsVerificationsByIds(Long[] ids)
    {
        return smsVerificationsMapper.deleteSmsVerificationsByIds(ids);
    }

    /**
     * 删除注册、成功信息
     * 
     * @param id 注册、成功主键
     * @return 结果
     */
    @Override
    public int deleteSmsVerificationsById(Long id)
    {
        return smsVerificationsMapper.deleteSmsVerificationsById(id);
    }
}
