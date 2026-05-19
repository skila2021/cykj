package org.ccq.register.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.ccq.register.domain.Users;
import org.ccq.register.service.IUsersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 注册、成功Controller
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
@RestController
@RequestMapping("/register/users")
public class UsersController extends BaseController
{
    @Autowired
    private IUsersService usersService;

    /**
     * 查询注册、成功列表
     */
    @PreAuthorize("@ss.hasPermi('register:users:list')")
    @GetMapping("/list")
    public TableDataInfo list(Users users)
    {
        startPage();
        List<Users> list = usersService.selectUsersList(users);
        return getDataTable(list);
    }

    /**
     * 导出注册、成功列表
     */
    @PreAuthorize("@ss.hasPermi('register:users:export')")
    @Log(title = "注册、成功", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Users users)
    {
        List<Users> list = usersService.selectUsersList(users);
        ExcelUtil<Users> util = new ExcelUtil<Users>(Users.class);
        util.exportExcel(response, list, "注册、成功数据");
    }

    /**
     * 获取注册、成功详细信息
     */
    @PreAuthorize("@ss.hasPermi('register:users:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(usersService.selectUsersById(id));
    }

    /**
     * 新增注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:users:add')")
    @Log(title = "注册、成功", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Users users)
    {
        return toAjax(usersService.insertUsers(users));
    }

    /**
     * 修改注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:users:edit')")
    @Log(title = "注册、成功", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Users users)
    {
        return toAjax(usersService.updateUsers(users));
    }

    /**
     * 删除注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:users:remove')")
    @Log(title = "注册、成功", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(usersService.deleteUsersByIds(ids));
    }
}
