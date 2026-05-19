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
import org.ccq.register.domain.UserSessions;
import org.ccq.register.service.IUserSessionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 注册、成功Controller
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
@RestController
@RequestMapping("/register/sessions")
public class UserSessionsController extends BaseController
{
    @Autowired
    private IUserSessionsService userSessionsService;

    /**
     * 查询注册、成功列表
     */
    @PreAuthorize("@ss.hasPermi('register:sessions:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserSessions userSessions)
    {
        startPage();
        List<UserSessions> list = userSessionsService.selectUserSessionsList(userSessions);
        return getDataTable(list);
    }

    /**
     * 导出注册、成功列表
     */
    @PreAuthorize("@ss.hasPermi('register:sessions:export')")
    @Log(title = "注册、成功", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserSessions userSessions)
    {
        List<UserSessions> list = userSessionsService.selectUserSessionsList(userSessions);
        ExcelUtil<UserSessions> util = new ExcelUtil<UserSessions>(UserSessions.class);
        util.exportExcel(response, list, "注册、成功数据");
    }

    /**
     * 获取注册、成功详细信息
     */
    @PreAuthorize("@ss.hasPermi('register:sessions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userSessionsService.selectUserSessionsById(id));
    }

    /**
     * 新增注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:sessions:add')")
    @Log(title = "注册、成功", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserSessions userSessions)
    {
        return toAjax(userSessionsService.insertUserSessions(userSessions));
    }

    /**
     * 修改注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:sessions:edit')")
    @Log(title = "注册、成功", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserSessions userSessions)
    {
        return toAjax(userSessionsService.updateUserSessions(userSessions));
    }

    /**
     * 删除注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:sessions:remove')")
    @Log(title = "注册、成功", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userSessionsService.deleteUserSessionsByIds(ids));
    }
}
