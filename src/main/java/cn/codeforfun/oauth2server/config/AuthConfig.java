package cn.codeforfun.oauth2server.config;

import cn.codeforfun.oauth2server.client.ClientServiceImpl;
import cn.codeforfun.oauth2server.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.annotation.Resource;

/**
 * 认证配置
 *
 * @author wangbin
 */
@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private ClientServiceImpl clientServiceImpl;
    @Resource
    private UserServiceImpl userServiceImpl;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private CustomTokenEnhancer tokenEnhancer;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置自带endpoint权限
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll");
        security.checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置client
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 自定义client实现类
        clients.withClientDetails(clientServiceImpl);
    }

    /**
     * 配置endpoint
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 自定义user实现类
        endpoints.userDetailsService(userServiceImpl);
        // 自定义tokenStore
        endpoints.tokenStore(tokenStore());
        // 密码模式需要
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenEnhancer(tokenEnhancer);
    }

    private TokenStore tokenStore() {
        // 暂时用内存级存储，之后需要改成redis
        return new InMemoryTokenStore();
    }

}
