import PropTypes from "prop-types";
import { UserOutlined } from "@ant-design/icons";
import { Avatar } from "antd";

const AvatarUser = ({ name }) => {
  const nameTrim = name.trim();
  if (nameTrim.length === 0) {
    return <Avatar icon={<UserOutlined />} />;
  }
  const initials = nameTrim
    .split(" ")
    .map((str) => str.charAt(0).toUpperCase())
    .join("");
  return <Avatar>{initials}</Avatar>;
};

AvatarUser.propTypes = {
  name: PropTypes.string.isRequired,
};

export default AvatarUser;
