package org.ccq.register.service;

import java.util.List;
import org.ccq.register.domain.SmsVerifications;

/**
 * 注册、成功Service接口
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
public interface ISmsVerificationsService 
{
    /**
     * 查询注册、成功
     * 
     * @param id 注册、成功主键
     * @return 注册、成功
     */
    public SmsVerifications selectSmsVerificationsById(Long id);

    /**
     * 查询注册、成功列表
     * 
     * @param smsVerifications 注册、成功
     * @return 注册、成功集合
     */
    public List<SmsVerifications> selectSmsVerificationsList(SmsVerifications smsVerifications);

    /**
     * 新增注册、成功
     * 
     * @param smsVerifications 注册、成功
     * @return 结果
     */
    public int insertSmsVerifications(SmsVerifications smsVerifications);

    /**
     * 修改注册、成功
     * 
     * @param smsVerifications 注册、成功
     * @return 结果
     */
    public int updateSmsVerifications(SmsVerifications smsVerifications);

    /**
     * 批量删除注册、成功
     * 
     * @param ids 需要删除的注册、成功主键集合
     * @return 结果
     */
    public int deleteSmsVerificationsByIds(Long[] ids);

    /**
     * 删除注册、成功信息
     * 
     * @param id 注册、成功主键
     * @return 结果
     */
    public int deleteSmsVerificationsById(Long id);
}
