package org.ccq.register.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 注册、成功对象 user_sessions
 * 
 * @author ruoyi
 * @date 2026-05-20
 */
public class UserSessions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会话唯一标识，主键 */
    private Long id;

    /** 关联用户ID（引用 users.id） */
    @Excel(name = "关联用户ID", readConverterExp = "引=用,u=sers.id")
    private Long userId;

    /** JWT或随机Token字符串，用于客户端鉴权 */
    @Excel(name = "JWT或随机Token字符串，用于客户端鉴权")
    private String token;

    /** Token过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Token过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiresAt;

    /** 会话创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "会话创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 最后访问时间（用于刷新/续期） */
    @Excel(name = "最后访问时间", readConverterExp = "用=于刷新/续期")
    private Date lastAccessedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }

    public void setExpiresAt(Date expiresAt) 
    {
        this.expiresAt = expiresAt;
    }

    public Date getExpiresAt() 
    {
        return expiresAt;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setLastAccessedAt(Date lastAccessedAt) 
    {
        this.lastAccessedAt = lastAccessedAt;
    }

    public Date getLastAccessedAt() 
    {
        return lastAccessedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("token", getToken())
            .append("expiresAt", getExpiresAt())
            .append("createdAt", getCreatedAt())
            .append("lastAccessedAt", getLastAccessedAt())
            .toString();
    }
}
