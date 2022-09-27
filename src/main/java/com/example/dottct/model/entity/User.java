package com.example.dottct.model.entity;

import com.example.dottct.model.dto.UserDto;
import com.example.dottct.util.DBAttributeConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Convert(converter = DBAttributeConverter.class)
    private String email;

    @Column
    private String pw;

    @Builder
    public User(Long idx, String email, String pw) {
        this.idx = idx;
        this.email = email;
        this.pw = pw;
    }

    public void encodePw(String pw) {
        this.pw = pw;
    }

    public UserDto toDto() {
        return UserDto.builder()
                .idx(this.idx)
                .email(this.email)
                .pw(this.pw)
                .build();
    }

}
