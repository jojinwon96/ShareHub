import React from 'react'
import '../assets/scss/sign.scss'

export default function SignUpInit() {
  return (
    <div className='sign-init-wrap'>
      <div className="sign-init-body">
        <h1 className='ubuntu-bold'>가입 유형 선택</h1>
        <div className="type-select">
          <button className='ubuntu-bold'>일반회원</button>
          <button className='ubuntu-bold'>관리자</button>
        </div>
      </div>
    </div>
  )
}
