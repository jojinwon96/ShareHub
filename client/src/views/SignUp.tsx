import React from "react";
import "../assets/scss/sign.scss";

export default function SignUp() {
  return (
    <div className="sign-up-wrap">
      <div className="sign-up">
        <div className="sign-up-header">
          <h1>회원가입</h1>
        </div>
        <div className="sign-up-body">
          <div className="input-form">
            <p className="ubuntu-bold">닉네임</p>
            <input type="text" />
          </div>
          <div className="input-form">
            <p className="ubuntu-bold">이메일</p>
            <div className="btn-form">
              <input type="text" />
              <button className="ubuntu-bold" type="button">인증</button>
            </div>
          </div>
          <div className="input-form">
            <p className="ubuntu-bold">인증코드</p>
            <div className="btn-form">
              <input type="text" />
              <button className="ubuntu-bold" type="button">확인</button>
            </div>
          </div>
          <div className="input-form">
            <p className="ubuntu-bold">비밀번호</p>
            <input type="password" />
          </div>
          <div className="input-form">
            <p className="ubuntu-bold">비밀번호 확인</p>
            <input type="password" />
          </div>
        </div>
        <div className="sign-up-footer">
          <button className="ubuntu-bold">취소</button>
          <button className="ubuntu-bold">완료</button>
        </div>
      </div>
    </div>
  );
}
