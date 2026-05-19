package org.ccq.register.service;

import java.util.List;
import org.ccq.register.domain.UserSessions;

/**
 * 注册、成功Service接口
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
public interface IUserSessionsService 
{
    /**
     * 查询注册、成功
     * 
     * @param id 注册、成功主键
     * @return 注册、成功
     */
    public UserSessions selectUserSessionsById(Long id);

    /**
     * 查询注册、成功列表
     * 
     * @param userSessions 注册、成功
     * @return 注册、成功集合
     */
    public List<UserSessions> selectUserSessionsList(UserSessions userSessions);

    /**
     * 新增注册、成功
     * 
     * @param userSessions 注册、成功
     * @return 结果
     */
    public int insertUserSessions(UserSessions userSessions);

    /**
     * 修改注册、成功
     * 
     * @param userSessions 注册、成功
     * @return 结果
     */
    public int updateUserSessions(UserSessions userSessions);

    /**
     * 批量删除注册、成功
     * 
     * @param ids 需要删除的注册、成功主键集合
     * @return 结果
     */
    public int deleteUserSessionsByIds(Long[] ids);

    /**
     * 删除注册、成功信息
     * 
     * @param id 注册、成功主键
     * @return 结果
     */
    public int deleteUserSessionsById(Long id);
}
