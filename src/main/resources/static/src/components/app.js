import React, {Component} from 'react';
import {Toast} from "antd-mobile";
import history from '../common/history';
class App extends Component {
  componentWillMount() {
    this.queryURL();
    let pathname = window.location.pathname;
    if(pathname.endsWith("register")) {
      history.push("/register");
    } else if(pathname.endsWith("salary")) {
      history.push("/salary");
    } else {
      history.push("/register");
    }
  }
  queryURL() {
    let arr1 = window.location.search.substring(1);
    var params = arr1.split("&");//进行分割成数组
    var obj = {};//声明对象
    for(let i = 0; i < params.length; i++) {
      let param = params[i].split("=");//进行分割成数组
      obj[param[0]] = param[1];//为对象赋值
    }
    if(!obj.openid) {
      Toast.info("路径不正确！");
    }
    window.sessionStorage.setItem("wxinfo",JSON.stringify(obj));
  }
  render() {
    return (
      <div className="wx-app">{this.props.children}</div>
    );
  }
}

export default App;
