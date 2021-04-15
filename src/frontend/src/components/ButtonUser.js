import { Button } from "antd";
import { PlusOutlined } from "@ant-design/icons";

const ButtonUser = () => {
  return (
    <Button type="primary" shape="round" icon={<PlusOutlined />} size="small">
      Add New User
    </Button>
  );
};

export default ButtonUser;
