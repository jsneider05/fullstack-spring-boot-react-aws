import { useContext } from "react";
import PropTypes from "prop-types";
import { Badge, Popconfirm, Radio, Table, Tag } from "antd";
import ButtonUser from "./ButtonUser";
import AvatarUser from "./AvatarUser";
import UserContext from "../hooks/userContext";
import UserRepository from "../services/user.repository";
import { successNotification, errorNotification } from "./Notification";
import "./TableUser.css";

const renderAvatar = (text, user) => <AvatarUser name={user.name} />;

const deleteUser = async (userId, callback) => {
  await UserRepository.deleteUser(userId)
    .then(() => {
      successNotification("User deleted successfully", `User with id ${userId} deleted`);
      callback();
    })
    .catch(({ response }) => {
      const messages = response.data.message;
      messages.forEach((msg) => {
        errorNotification("There was an issue", `${msg}`);
      });
    });
};

const renderActions = (user, fetchUsers) => {
  return (
    <Radio.Group size="middle">
      <Popconfirm
        title={`Are you sure to delete ${user.name}?`}
        onConfirm={() => deleteUser(user.id, fetchUsers)}
        okText="Yes"
        cancelText="No"
      >
        <Radio.Button>Delete</Radio.Button>
      </Popconfirm>
      <Radio.Button>Edit</Radio.Button>
    </Radio.Group>
  );
};

const columns = (fetchUsers) => [
  {
    title: "",
    dataIndex: "avatar",
    key: "avatar",
    render: renderAvatar,
  },
  {
    title: "Id",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "Name",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Email",
    dataIndex: "email",
    key: "email",
  },
  {
    title: "Gender",
    dataIndex: "gender",
    key: "gender",
  },
  {
    title: "Actions",
    dataIndex: "actions",
    key: "actions",
    render: (text, user) => renderActions(user, fetchUsers),
  },
];

const TableUser = ({ buttonUserOnClick, fetchUsers }) => {
  const users = useContext(UserContext);
  return (
    <Table
      dataSource={users}
      columns={columns(fetchUsers)}
      bordered
      title={() => (
        <>
          <Tag>Number of users</Tag>
          <Badge count={users.length} className="site-badge-count-4" />
          <br />
          <br />
          <ButtonUser onClick={() => buttonUserOnClick()} />
        </>
      )}
      scroll={{ y: 240 }}
      rowKey={(user) => user.id}
    />
  );
};

TableUser.propTypes = {
  buttonUserOnClick: PropTypes.func.isRequired,
  fetchUsers: PropTypes.func.isRequired,
};

export default TableUser;
