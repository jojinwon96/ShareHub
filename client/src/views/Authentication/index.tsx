import React, { useState } from "react";
import "./style.scss";
import InputBox from "components/InputBox";

export default function Authentication() {
  const [view, setView] = useState<"sign-in" | "sign-up">("sign-in");

  const [value, setValue] = useState<string>("");

  const SignIn = () => {
    return (
      <div className="sign-in">
        <h1>ShareHub</h1>
        <div className="line"></div>
        <InputBox type="text" placeholder="이메일 또는 아이디" value={value} setValue={setValue} error={false} />
        <InputBox type="password" placeholder="비밀번호" value={value} setValue={setValue} error={false} />
        <p className="error">아이디와 비밀번호를 입력해주세요</p>
        <button className="ubuntu-bold login-btn" type="button">
          로그인
        </button>
        <div className="line"></div>
        <div className="google-login">
          <div className="google-logo"></div>
          <p>구글 계정으로 로그인</p>
        </div>
        <div className="login-util">
          <span>아이디 찾기</span>
          <span>비밀번호 찾기</span>
          <span>회원가입</span>
        </div>
      </div>
    );
  };

  const SignUp = () => {
    return (
      <div className="sign-up">
        <div className="sign-up-header">
          <h1>회원가입</h1>
        </div>
        <div className="sign-up-body">
          <InputBox label="닉네임" type="text" value={value} setValue={setValue} error={false} message="닉네임을 입력해주세요." />
          <InputBox isButton={true} buttonName="인증" label="이메일" type="text" value={value} setValue={setValue} error={false} message="이메일을 입력해주세요." />
          <InputBox isButton={true} buttonName="확인" label="인증코드" type="text" value={value} setValue={setValue} error={false} />
          <InputBox label="비밀번호 확인" type="password" value={value} setValue={setValue} error={false} message="비밀번호가 일치하지 않습니다." />
        </div>
        <div className="sign-up-footer">
          <button>취소</button>
          <button>완료</button>
        </div>
      </div>
    );
  };

  return (
    <div className={`auth-wrap ${view === "sign-in" ? "sign-in-bg" : "sign-up-bg"}`}>
      <div className="auth-container">
        {view === "sign-in" && <SignIn />}
        {view === "sign-up" && <SignUp />}
      </div>
    </div>
  );
}
