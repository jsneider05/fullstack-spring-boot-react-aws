import { useState } from "react";
import { Layout, Menu, Breadcrumb, Empty } from "antd";
import { DesktopOutlined, PieChartOutlined, FileOutlined, TeamOutlined, UserOutlined } from "@ant-design/icons";
import "./App.css";
import SpinFetching from "./components/SpinFetching.js";
import TableUser from "./components/TableUser.js";
import useFetchUsers from "./hooks/useFetchUsers.js";
import { UserProvider } from "./hooks/userContext";

const { Header, Content, Footer, Sider } = Layout;
const { SubMenu } = Menu;

const App = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [users, fetching] = useFetchUsers();

  const renderUsers = () => {
    if (fetching) {
      return <SpinFetching />;
    }
    if (users.length <= 0) {
      return <Empty />;
    }
    return <TableUser />;
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
          <Footer style={{ textAlign: "center" }}>By JoanS ©2021</Footer>
        </Layout>
      </Layout>
    </UserProvider>
  );
};

export default App;
