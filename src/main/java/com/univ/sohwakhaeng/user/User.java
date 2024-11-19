package com.univ.sohwakhaeng.user;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String username; //아이디

    @Column(name = "user_password")
    private String password; //비밀번호

    @Column(name = "user_nickname")
    private String nickname; //닉네임

    @Enumerated(EnumType.STRING)
    private Authority authority;//권한

    @Enumerated(EnumType.STRING)
    private SocialType socialType; //로그인 타입

    private String name; //사용자 이름

    @Column(name = "user_img")
    private String profileImgUrl; //사용자 이미지

    @Builder
    public User(String username, String password, String nickname, Authority authority, SocialType socialType, String name, String profileImgUrl) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
        this.socialType = socialType;
        this.name = name;
        this.profileImgUrl = profileImgUrl;
    }
}