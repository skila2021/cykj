package org.ccq.register.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ccq.register.mapper.UserSessionsMapper;
import org.ccq.register.domain.UserSessions;
import org.ccq.register.service.IUserSessionsService;

/**
 * 注册、成功Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
@Service
public class UserSessionsServiceImpl implements IUserSessionsService 
{
    @Autowired
    private UserSessionsMapper userSessionsMapper;

    /**
     * 查询注册、成功
     * 
     * @param id 注册、成功主键
     * @return 注册、成功
     */
    @Override
    public UserSessions selectUserSessionsById(Long id)
    {
        return userSessionsMapper.selectUserSessionsById(id);
    }

    /**
     * 查询注册、成功列表
     * 
     * @param userSessions 注册、成功
     * @return 注册、成功
     */
    @Override
    public List<UserSessions> selectUserSessionsList(UserSessions userSessions)
    {
        return userSessionsMapper.selectUserSessionsList(userSessions);
    }

    /**
     * 新增注册、成功
     * 
     * @param userSessions 注册、成功
     * @return 结果
     */
    @Override
    public int insertUserSessions(UserSessions userSessions)
    {
        return userSessionsMapper.insertUserSessions(userSessions);
    }

    /**
     * 修改注册、成功
     * 
     * @param userSessions 注册、成功
     * @return 结果
     */
    @Override
    public int updateUserSessions(UserSessions userSessions)
    {
        return userSessionsMapper.updateUserSessions(userSessions);
    }

    /**
     * 批量删除注册、成功
     * 
     * @param ids 需要删除的注册、成功主键
     * @return 结果
     */
    @Override
    public int deleteUserSessionsByIds(Long[] ids)
    {
        return userSessionsMapper.deleteUserSessionsByIds(ids);
    }

    /**
     * 删除注册、成功信息
     * 
     * @param id 注册、成功主键
     * @return 结果
     */
    @Override
    public int deleteUserSessionsById(Long id)
    {
        return userSessionsMapper.deleteUserSessionsById(id);
    }
}
