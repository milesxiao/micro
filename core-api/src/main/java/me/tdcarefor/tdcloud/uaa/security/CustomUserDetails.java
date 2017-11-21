package me.tdcarefor.tdcloud.uaa.security;

import me.tdcarefor.tdcloud.user.model.UserModel;
import me.tdcarefor.util.CachedBeanCopier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserModel implements UserDetails {

    private static final long serialVersionUID = 1L;
    private List<String> userRoles;
    private Collection<? extends GrantedAuthority> authList;


    public CustomUserDetails(UserModel user, List<String> userRoles) {
        CachedBeanCopier.copy(user, this);
        this.userRoles = userRoles;
        this.authList=getAuthoritiesFor(userRoles);

    }
    public CustomUserDetails(UserModel user, Collection<? extends GrantedAuthority> authorities) {
        CachedBeanCopier.copy(user, this);
        this.authList=authorities;

    }

    //角色列表转换为角色表达式
    private Collection<? extends GrantedAuthority> getAuthoritiesFor(List<String> userRoles){
           String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authList;
    }

    // 将数据库中用户的password映射到security的password
    @Override
    public String getPassword() {
        return super.getPassword();
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
        return true;
    }

    // 将数据库中用户的userName映射到security的username
    @Override
    public String getUsername() {
        return super.getUserName();
    }

    public String getUserId() {
        return super.getId();
    }

}
