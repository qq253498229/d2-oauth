package cn.codeforfun.oauth2server.user;

import cn.codeforfun.oauth2server.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户实体
 *
 * @author wangbin
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"users", "user"})
@Table(name = "t_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    private Date createAt = new Date();

    private Date updateAt = new Date();

    @Column(unique = true, nullable = false, length = 64)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User createBy;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User updateBy;
    /**
     * 用户名
     */
    @Column(unique = true, nullable = false, length = 64)
    private String username;

    /**
     * 手机
     */
    @Column(unique = true, length = 20)
    private String phoneId;

    /**
     * 密码
     */
    @Column(nullable = false, length = 128)
    private String password;

    /**
     * 性别，0女，1男，2其它
     */
    private Integer sex;

    @Column
    private Boolean enabled = true;

    @ManyToMany
    @JoinTable(
            name = "t_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(role::getName));
        return authorities;
    }

    public User(String username, String password, Boolean enabled, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    String toJson() {
        return JsonUtil.toJson(this);
    }
}
