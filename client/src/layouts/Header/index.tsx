import React from "react";
import "./style.scss";
import { useNavigate } from "react-router-dom";
import { HOME_PATH } from "../../constants";

export default function Header() {

  const navigate = useNavigate();

  const onLogoClickHandler = () => {
    console.log('0k');
    navigate(HOME_PATH());
  }

  return (
    <div className="header-wrap">
      <div className="header-left">
        <h1 onClick={onLogoClickHandler}>ShareHub</h1>
        <ul>
          <li>게시판</li>
          <li>캘린더</li>
          <li>메세지</li>
          <li>칸보드</li>
        </ul>
      </div>
      <div className="header-right">
        <div className="user-logo"></div>
      </div>
    </div>
  );
}
