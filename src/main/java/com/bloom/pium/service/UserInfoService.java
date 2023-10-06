package com.bloom.pium.service;

import com.bloom.pium.data.dto.UserInfoDto;

public interface UserInfoService {

    // 이름 유일성 확인
    public boolean isUsernameUnique(String username );

    // 회원가입
    public UserInfoDto join(UserInfoDto userInfoDto);

    // 회원 ID 조회
    UserInfoDto findUsername(String username);

    // 비밀번호 일치 확인
}
