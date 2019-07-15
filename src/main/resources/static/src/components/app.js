import React, {Component} from 'react';
import history from "../common/history";
class App extends Component {
  componentWillMount() {
    if(true) {
      history.push("/register");
    }
  }
  render() {
    return (
      <div className="wx-app">{this.props.children}</div>
    );
  }
}

export default App;
