import React from "react";
import {Route, Router} from "react-router-dom";
import App from "../components/app";
import Register from "../components/register";
import Salary from "../components/salary-slip";
import history from "../common/history";
const RoutesMap = () => {
    return (
        <Router history={history}>
                <Route path="/"
                    render={(params) => (
                        <App {...params}>
                            {/* <Redirect exact
                                from="/"
                                to="/register"
                            /> */}
                            <Route path="/register"
                                component={Register}
                            />
                            <Route path="/salary"
                                component={Salary}
                            />
                        </App>
                    )}
                />
        </Router>);
}


export default RoutesMap;
