package org.ccq.register.mapper;

import java.util.List;
import org.ccq.register.domain.Users;

/**
 * 注册、成功Mapper接口
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
public interface UsersMapper 
{
    /**
     * 查询注册、成功
     * 
     * @param id 注册、成功主键
     * @return 注册、成功
     */
    public Users selectUsersById(Long id);

    /**
     * 查询注册、成功列表
     * 
     * @param users 注册、成功
     * @return 注册、成功集合
     */
    public List<Users> selectUsersList(Users users);

    /**
     * 新增注册、成功
     * 
     * @param users 注册、成功
     * @return 结果
     */
    public int insertUsers(Users users);

    /**
     * 修改注册、成功
     * 
     * @param users 注册、成功
     * @return 结果
     */
    public int updateUsers(Users users);

    /**
     * 删除注册、成功
     * 
     * @param id 注册、成功主键
     * @return 结果
     */
    public int deleteUsersById(Long id);

    /**
     * 批量删除注册、成功
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUsersByIds(Long[] ids);
}
