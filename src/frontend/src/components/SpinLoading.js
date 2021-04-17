import { Spin } from "antd";
import "./SpinLoading.css";

const SpinLoading = () => {
  return (
    <div className="spin">
      <Spin size="large" />
    </div>
  );
};

export default SpinLoading;
