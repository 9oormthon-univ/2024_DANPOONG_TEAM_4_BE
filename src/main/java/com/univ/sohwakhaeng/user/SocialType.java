package com.univ.sohwakhaeng.user;

public enum SocialType {

  KAKAO("kakao");

  SocialType(String type) {
  }

  public static SocialType of(String type) {
    if (type.equals("kakao")) {
      return KAKAO;
    }
    return null;
  }

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
