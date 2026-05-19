package org.ccq.register.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 注册、成功对象 users
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
public class Users extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户唯一标识，主键 */
    private Long id;

    /** 手机号，全局唯一，符合11位数字格式 */
    @Excel(name = "手机号，全局唯一，符合11位数字格式")
    private String phone;

    /** 密码哈希值（含盐），不可逆加密存储 */
    @Excel(name = "密码哈希值", readConverterExp = "含=盐")
    private String passwordHash;

    /** 密码加盐随机字符串 */
    @Excel(name = "密码加盐随机字符串")
    private String salt;

    /** 用户创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用户创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

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

    public void setPasswordHash(String passwordHash) 
    {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() 
    {
        return passwordHash;
    }

    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("phone", getPhone())
            .append("passwordHash", getPasswordHash())
            .append("salt", getSalt())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
