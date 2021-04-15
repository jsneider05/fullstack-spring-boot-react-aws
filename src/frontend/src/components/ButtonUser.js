import PropTypes from "prop-types";
import { Button } from "antd";
import { PlusOutlined } from "@ant-design/icons";

const ButtonUser = ({ onClick: buttonUserOnClick }) => {
  return (
    <Button onClick={() => buttonUserOnClick()} type="primary" shape="round" icon={<PlusOutlined />} size="small">
      Add New User
    </Button>
  );
};

ButtonUser.propTypes = {
  onClick: PropTypes.func.isRequired,
};

export default ButtonUser;
