package cn.codeforfun.oauth2server.config;

import cn.codeforfun.oauth2server.user.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangbin
 */
@Component
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>(2);
        additionalInfo.put("id", user.getId());
        additionalInfo.put("nickname", user.getNickname());
        ((DefaultOAuth2AccessToken) accessToken)
                .setAdditionalInformation(additionalInfo);
        // 返回token
        accessToken = super.enhance(accessToken, authentication);
        return accessToken;
    }
}
