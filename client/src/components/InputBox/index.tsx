import { ChangeEvent, Dispatch, forwardRef, KeyboardEvent, SetStateAction } from "react";
import "./style.scss";

interface Props {
  label?: string;
  type: "text" | "password";
  placeholder?: string;
  value: string;
  setValue: Dispatch<SetStateAction<string>>;
  message?: string;
  error?: boolean;
  isButton?: boolean;
  buttonName?: string,

  onButtonClick?: () => void;
  onKeyDown?: (event: KeyboardEvent<HTMLInputElement>) => void;
}

const InputBox = forwardRef<HTMLInputElement, Props>((props: Props, ref) => {
  const { isButton, label, type, placeholder, value, error, message, buttonName } = props;
  const { setValue, onButtonClick, onKeyDown } = props;

  // 키 값 변경 이벤트 처리 함수
  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setValue(value);
  };

  // 키 이벤트 처리 함수
  const onKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
    if (!onKeyDown) return;
    onKeyDown(event);
  };

  // 버튼 이벤트 처리 함수

  const ButtonInputForm = () => {
    return (
      <div className={`inputbox ${!placeholder ? 'gap10' : ''}`}>
        <p className="inputbox-label">{label}</p>
        <div className="buttonbox">
          <input ref={ref} type={type} value={value} onChange={onChangeHandler} onKeyDown={onKeyDownHandler} />
          <button>{buttonName}</button>
        </div>
        {error && <div className="inputbox-message">{message}</div>}
      </div>
    );
  };

  const InputForm = () => {
    return (
      <div className={`inputbox ${!placeholder ? 'gap10' : ''}`}>
        <div className="inputbox-label">{label}</div>
        <div className={`inputbox-container ${error && "input-error"}`}>
          <input ref={ref} type={type} placeholder={placeholder} value={value} onChange={onChangeHandler} onKeyDown={onKeyDownHandler} />
        </div>
        {error && <div className="inputbox-message">{message}</div>}
      </div>
    );
  };


  return isButton === undefined ? <InputForm /> : <ButtonInputForm />;
});

export default InputBox;
