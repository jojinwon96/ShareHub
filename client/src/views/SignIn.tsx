import React from 'react'
import '../assets/scss/sign.scss'
import googleLogo from '../assets/image/google_01.png';

export default function Login() {
  return (
    <div className='login-wrap'>
      <div className="login-body">
        <h1 className='ubuntu-bold'>ShareHub</h1>
        <div className='line'></div>
        <div className='login-input'>
          <input className='ubuntu-medium' type="text" placeholder='이메일 또는 아이디'/>
        </div>
        <div className='login-input'>
          <input className='ubuntu-medium' type="text" placeholder='비밀번호'/>
        </div>
        <button className='ubuntu-bold login-btn' type='button'>로그인</button>
        <div className='line'></div>
        <div className='google-login'>
          <img src={googleLogo} alt="Google logo"/>
          <p className='ubuntu-bold'>구글 계정으로 로그인</p>
        </div>
        <div className='ubuntu-bold login-util'>
          <span>아이디 찾기</span>
          <span>비밀번호 찾기</span>
          <span>회원가입</span>
        </div>
      </div>
    </div>
  )
}
