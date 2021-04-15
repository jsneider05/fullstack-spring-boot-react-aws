import { useContext } from "react";
import PropTypes from "prop-types";
import { Table } from "antd";
import ButtonUser from "./ButtonUser.js";
import UserContext from "../hooks/userContext";

const columns = [
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
];

const TableUser = ({ buttonUserOnClick }) => {
  const users = useContext(UserContext);
  return (
    <Table
      dataSource={users}
      columns={columns}
      bordered
      title={() => <ButtonUser onClick={() => buttonUserOnClick()} />}
      scroll={{ y: 240 }}
      rowKey={(user) => user.id}
    />
  );
};

TableUser.propTypes = {
  buttonUserOnClick: PropTypes.func.isRequired,
};

export default TableUser;
