import PropTypes from "prop-types";
import { Result } from "antd";

const ResultError = (props) => {
  const { status: statusError, title: titleError, subTitle: subTitleError } = props;
  return <Result status={statusError} title={titleError} subTitle={subTitleError} />;
};

ResultError.propTypes = {
  status: PropTypes.oneOfType([PropTypes.number, PropTypes.oneOf(["success", "error", "info", "warning"])]).isRequired,
  title: PropTypes.string.isRequired,
  subTitle: PropTypes.string.isRequired,
};

export default ResultError;
