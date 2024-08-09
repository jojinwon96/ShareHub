import React from 'react'
import { Outlet, useLocation } from 'react-router-dom'
import Header from '../Header'
import { AUTH_PATH } from '../../constants';

export default function Container() {

  // state : 현재 페이지의 path name 상태
  const { pathname } = useLocation();

  return (
    <>
      {pathname !== AUTH_PATH() && <Header/>}
      <Outlet/>
    </>
  )
}
