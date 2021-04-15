import { useContext } from "react";
import { Table } from "antd";
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

const TableUser = () => {
  const users = useContext(UserContext);
  return (
    <Table
      dataSource={users}
      columns={columns}
      bordered
      title={() => "Users"}
      scroll={{ y: 240 }}
      rowKey={(user) => user.id}
    />
  );
};

export default TableUser;
