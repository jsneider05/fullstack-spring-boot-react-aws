import { useState } from "react";
import PropTypes from "prop-types";
import { Drawer, Input, Col, Select, Form, Row, Button } from "antd";
import UserRepository from "../services/user.repository";
import SpinLoading from "./SpinLoading";
import { successNotification, errorNotification } from "./Notification";

const { Option } = Select;

const UserDrawerForm = (props) => {
  const { showDrawer, setShowDrawer, fetchUsers } = props;

  const [submitting, setSubmitting] = useState(false);

  const addUser = async (user) => {
    setSubmitting(true);
    await UserRepository.addUser(user)
      .then(() => {
        successNotification("User added successfully", `${user.name} was added to the system`);
        fetchUsers();
      })
      .catch(({ response }) => {
        const messages = response.data.message;
        messages.forEach((msg) => {
          errorNotification("There was an issue", `${msg}`);
        });
      })
      .finally(() => {
        setSubmitting(false);
        onCLose();
      });
  };

  const onCLose = () => setShowDrawer(false);

  const onFinish = async (user) => await addUser(user);

  const onFinishFailed = (errorInfo) => alert(JSON.stringify(errorInfo, null, 2));

  return (
    <Drawer
      title="Create new user"
      width={720}
      onClose={onCLose}
      visible={showDrawer}
      bodyStyle={{ paddingBottom: 80 }}
      footer={
        <div
          style={{
            textAlign: "right",
          }}
        >
          <Button onClick={onCLose} style={{ marginRight: 8 }}>
            Cancel
          </Button>
        </div>
      }
    >
      <Form layout="vertical" onFinishFailed={onFinishFailed} onFinish={onFinish} hideRequiredMark>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="name" label="Name" rules={[{ required: true, message: "Please enter user name" }]}>
              <Input placeholder="Please enter user name" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="email" label="Email" rules={[{ required: true, message: "Please enter user email" }]}>
              <Input placeholder="Please enter user email" />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16}>
          <Col span={12}>
            <Form.Item name="gender" label="gender" rules={[{ required: true, message: "Please select a gender" }]}>
              <Select placeholder="Please select a gender">
                <Option value="MALE">MALE</Option>
                <Option value="FEMALE">FEMALE</Option>
                <Option value="OTHER">OTHER</Option>
              </Select>
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                Submit
              </Button>
            </Form.Item>
          </Col>
        </Row>
        <Row>{submitting && <SpinLoading />}</Row>
      </Form>
    </Drawer>
  );
};

UserDrawerForm.propTypes = {
  showDrawer: PropTypes.bool.isRequired,
  setShowDrawer: PropTypes.func.isRequired,
  fetchUsers: PropTypes.func.isRequired,
};

export default UserDrawerForm;
