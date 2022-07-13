package com.sparta.finalproject6.model;


import com.sparta.finalproject6.dto.requestDto.SignUpRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private String userImgUrl;

    @Column
    private Long kakaoId;

    @Column
    private String userInfo;

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    public User(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
    public User(SignUpRequestDto dto){
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
    }

    public User(String username, String nickname, String password, String thumbnailImage, Long kakaoId) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.userImgUrl = thumbnailImage;
        this.kakaoId = kakaoId;
    }
}
