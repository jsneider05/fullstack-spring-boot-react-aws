import PropTypes from "prop-types";
import { Table } from "antd";

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

const TableUser = ({ users }) => {
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

TableUser.propTypes = {
  users: PropTypes.array.isRequired,
};

export default TableUser;
