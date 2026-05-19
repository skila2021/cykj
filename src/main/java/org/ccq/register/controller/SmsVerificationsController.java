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
import org.ccq.register.domain.SmsVerifications;
import org.ccq.register.service.ISmsVerificationsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 注册、成功Controller
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
@RestController
@RequestMapping("/register/verifications")
public class SmsVerificationsController extends BaseController
{
    @Autowired
    private ISmsVerificationsService smsVerificationsService;

    /**
     * 查询注册、成功列表
     */
    @PreAuthorize("@ss.hasPermi('register:verifications:list')")
    @GetMapping("/list")
    public TableDataInfo list(SmsVerifications smsVerifications)
    {
        startPage();
        List<SmsVerifications> list = smsVerificationsService.selectSmsVerificationsList(smsVerifications);
        return getDataTable(list);
    }

    /**
     * 导出注册、成功列表
     */
    @PreAuthorize("@ss.hasPermi('register:verifications:export')")
    @Log(title = "注册、成功", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsVerifications smsVerifications)
    {
        List<SmsVerifications> list = smsVerificationsService.selectSmsVerificationsList(smsVerifications);
        ExcelUtil<SmsVerifications> util = new ExcelUtil<SmsVerifications>(SmsVerifications.class);
        util.exportExcel(response, list, "注册、成功数据");
    }

    /**
     * 获取注册、成功详细信息
     */
    @PreAuthorize("@ss.hasPermi('register:verifications:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsVerificationsService.selectSmsVerificationsById(id));
    }

    /**
     * 新增注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:verifications:add')")
    @Log(title = "注册、成功", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsVerifications smsVerifications)
    {
        return toAjax(smsVerificationsService.insertSmsVerifications(smsVerifications));
    }

    /**
     * 修改注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:verifications:edit')")
    @Log(title = "注册、成功", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsVerifications smsVerifications)
    {
        return toAjax(smsVerificationsService.updateSmsVerifications(smsVerifications));
    }

    /**
     * 删除注册、成功
     */
    @PreAuthorize("@ss.hasPermi('register:verifications:remove')")
    @Log(title = "注册、成功", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsVerificationsService.deleteSmsVerificationsByIds(ids));
    }
}
