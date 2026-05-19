package org.ccq.register.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 注册、成功对象 sms_verifications
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
public class SmsVerifications extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 验证码记录唯一标识，主键 */
    private Long id;

    /** 绑定的手机号，用于验证与限流 */
    @Excel(name = "绑定的手机号，用于验证与限流")
    private String phone;

    /** 6位数字验证码 */
    @Excel(name = "6位数字验证码")
    private String code;

    /** 验证码过期时间（通常为5分钟） */
    @Excel(name = "验证码过期时间", readConverterExp = "通=常为5分钟")
    private Date expiresAt;

    /** 是否已被使用（0:未使用, 1:已使用） */
    @Excel(name = "是否已被使用", readConverterExp = "0=:未使用,,1=:已使用")
    private Long isUsed;

    /** 验证码生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "验证码生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 当日该手机号发送次数（用于单日上限校验） */
    @Excel(name = "当日该手机号发送次数", readConverterExp = "用=于单日上限校验")
    private String attemptCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setExpiresAt(Date expiresAt) 
    {
        this.expiresAt = expiresAt;
    }

    public Date getExpiresAt() 
    {
        return expiresAt;
    }

    public void setIsUsed(Long isUsed) 
    {
        this.isUsed = isUsed;
    }

    public Long getIsUsed() 
    {
        return isUsed;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setAttemptCount(String attemptCount) 
    {
        this.attemptCount = attemptCount;
    }

    public String getAttemptCount() 
    {
        return attemptCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("phone", getPhone())
            .append("code", getCode())
            .append("expiresAt", getExpiresAt())
            .append("isUsed", getIsUsed())
            .append("createdAt", getCreatedAt())
            .append("attemptCount", getAttemptCount())
            .toString();
    }
}
