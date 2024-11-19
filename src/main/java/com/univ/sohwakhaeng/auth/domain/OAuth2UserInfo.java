package com.univ.sohwakhaeng.auth.domain;

import java.util.Map;

public interface OAuth2UserInfo {
    Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getName();
    String getProfileImage();
}
