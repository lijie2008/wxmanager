import React from "react";
import {Route, Router, Switch} from "react-router-dom";
import App from "../components/app";
import Register from "../components/register";
import Salary from "../components/salary-slip";
import history from "../common/history";
const RoutesMap = () => {
    return (
        <Router history={history}>
            <Switch>
                <Route path="/"
                    render={(params) => (
                        <App {...params}>
                            <Route path="/register"
                                component={Register}
                            />
                            <Route path="/salary"
                                component={Salary}
                            />
                        </App>
                    )}
                />
            </Switch>

        </Router>);
}


export default RoutesMap;
