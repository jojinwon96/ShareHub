import React from "react";
// import SignIn from 'views/SignIn';
// import SignUpInit from 'views/SignUpInit';
// import SignUp from "views/SignUp";
import "./assets/scss/reset.scss";
import "./assets/scss/font.scss";
import "App.css"
import { Navigate, Route, Routes } from "react-router-dom";
import Container from "./layouts/Container";
import Home from "views/Home";
import Authentication from "views/Authentication";
import BoardMain from "views/Board/BoardMain";
import BoardType from "views/Board/ByType";
import BoardMy from "views/Board/My";
import BoardDetail from "views/Board/Detail";
import BoardWrite from "views/Board/Write";
import BoardUpdate from "views/Board/Update";
import { MAIN_PATH, AUTH_PATH, BOARD_PATH, BOARD_DETAIL_PATH, BOARD_WRITE_PATH, BOARD_UPDATE_PATH, HOME_PATH } from "./constants";

function App() {
  return (
    <>
      <Routes>
        <Route element={<Container />}>
          <Route path={MAIN_PATH()} element={<Navigate to={AUTH_PATH()}/>}></Route>
          <Route path={HOME_PATH()} element={<Home />}></Route>
          <Route path={AUTH_PATH()} element={<Authentication />}></Route>
          <Route path={BOARD_PATH()}>
            <Route path="" element={<BoardMain />}></Route>
            <Route path="type" element={<BoardType />}></Route>
            <Route path="my" element={<BoardMy />}></Route>
            <Route path={BOARD_DETAIL_PATH(':boardNumber')} element={<BoardDetail />}></Route>
            <Route path={BOARD_WRITE_PATH()} element={<BoardWrite />}></Route>
            <Route path={BOARD_UPDATE_PATH(':boardNumber')} element={<BoardUpdate />}></Route>
          </Route>
          <Route path="*" element={<h1>404 Not Found</h1>}></Route>
        </Route>
      </Routes>
    </>
  );
}

export default App;
