import { useState } from "react";
import { Layout, Menu, Breadcrumb, Empty } from "antd";
import { DesktopOutlined, PieChartOutlined, FileOutlined, TeamOutlined, UserOutlined } from "@ant-design/icons";
import "./App.css";
import SpinLoading from "./components/SpinLoading.js";
import TableUser from "./components/TableUser.js";
import UserDraweForm from "./components/UserDraweForm.js";
import ResultError from "./components/ResultError";
import ButtonUser from "./components/ButtonUser";
import useFetchUsers from "./hooks/useFetchUsers.js";
import { UserProvider } from "./hooks/userContext";

const { Header, Content, Footer, Sider } = Layout;
const { SubMenu } = Menu;

const App = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [users, loading, isError, error, fetchUsers] = useFetchUsers();
  const [showDrawer, setShowDrawer] = useState(false);

  const renderUsers = () => {
    if (loading) {
      return <SpinLoading />;
    }
    if (isError) {
      const { statusCode = "500", message = ["Please contact the administrator"] } = error;
      const [messageSubtitle] = message;
      return (
        <ResultError status={"error"} title="There was an issue." subTitle={`[${statusCode}] ${messageSubtitle}`} />
      );
    }
    if (users.length <= 0) {
      return (
        <>
          <ButtonUser onClick={() => setShowDrawer(!showDrawer)} />
          <UserDraweForm showDrawer={showDrawer} setShowDrawer={setShowDrawer} fetchUsers={fetchUsers} />
          <Empty />
        </>
      );
    }
    return (
      <>
        <UserDraweForm showDrawer={showDrawer} setShowDrawer={setShowDrawer} fetchUsers={fetchUsers} />
        <TableUser buttonUserOnClick={() => setShowDrawer(!showDrawer)} fetchUsers={fetchUsers} />
      </>
    );
  };

  return (
    <UserProvider value={users}>
      <Layout style={{ minHeight: "100vh" }}>
        <Sider collapsible collapsed={collapsed} onCollapse={setCollapsed}>
          <div className="logo" />
          <Menu theme="dark" defaultSelectedKeys={["1"]} mode="inline">
            <Menu.Item key="1" icon={<PieChartOutlined />}>
              Option 1
            </Menu.Item>
            <Menu.Item key="2" icon={<DesktopOutlined />}>
              Option 2
            </Menu.Item>
            <SubMenu key="sub1" icon={<UserOutlined />} title="User">
              <Menu.Item key="3">Tom</Menu.Item>
              <Menu.Item key="4">Bill</Menu.Item>
              <Menu.Item key="5">Alex</Menu.Item>
            </SubMenu>
            <SubMenu key="sub2" icon={<TeamOutlined />} title="Team">
              <Menu.Item key="6">Team 1</Menu.Item>
              <Menu.Item key="8">Team 2</Menu.Item>
            </SubMenu>
            <Menu.Item key="9" icon={<FileOutlined />}>
              Files
            </Menu.Item>
          </Menu>
        </Sider>
        <Layout className="site-layout">
          <Header className="site-layout-background" style={{ padding: 0 }} />
          <Content style={{ margin: "0 16px" }}>
            <Breadcrumb style={{ margin: "16px 0" }}>
              <Breadcrumb.Item>User</Breadcrumb.Item>
              <Breadcrumb.Item>Bill</Breadcrumb.Item>
            </Breadcrumb>
            <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
              {renderUsers()}
            </div>
          </Content>
          <Footer style={{ textAlign: "center" }}>By JoanSneider ©2021</Footer>
        </Layout>
      </Layout>
    </UserProvider>
  );
};

export default App;
