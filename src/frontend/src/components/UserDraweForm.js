import PropTypes from "prop-types";
import { Drawer, Input, Col, Select, Form, Row, Button } from "antd";

const { Option } = Select;

const UserDrawerForm = ({ showDrawer, setShowDrawer }) => {
  const onCLose = () => setShowDrawer(false);

  const onFinish = (values) => {
    alert(JSON.stringify(values, null, 2));
  };

  const onFinishFailed = (errorInfo) => {
    alert(JSON.stringify(errorInfo, null, 2));
  };

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
      </Form>
    </Drawer>
  );
};

UserDrawerForm.propTypes = {
  showDrawer: PropTypes.bool.isRequired,
  setShowDrawer: PropTypes.func.isRequired,
};

export default UserDrawerForm;
