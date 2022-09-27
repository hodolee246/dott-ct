package com.example.dottct.model.dto;

import com.example.dottct.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {

    private Long idx;
    private String email;
    private String pw;

    @Builder
    public UserDto(Long idx, String email, String pw) {
        this.idx = idx;
        this.email = email;
        this.pw = pw;
    }

    public User toEntity() {
        return User.builder()
                .idx(this.idx)
                .email(this.email)
                .pw(this.pw)
                .build();
    }

}
