import React, { Component } from 'react'
import {createForm, formShape } from 'rc-form';
import {List, InputItem, WhiteSpace, Button} from 'antd-mobile';
import instance from "../../common/instance";
import history from "../../common/history";
class Register extends Component {
    static propTypes = {
        form: formShape,
    };
    constructor(props) {
        super(props);
        this.state = {
            hasError: false
         }
    }
    submitData() {

    }
    submit = () => {
        this.props.form.validateFields(async (error, value) => {
            console.log(error, value);
            // let res = await instance.post("/", value)
            // console.log(res);
            history.push("/salary")
        });
    }
    render() {
        let errors;
        const {getFieldProps, getFieldError} = this.props.form;
        return (<div>
            <List renderHeader={() => '基本信息'}>
                <InputItem
                    placeholder="姓名"
                    {...getFieldProps('fnname', {
                        rules: [{required: true,message: "请输入姓名"}]
                    })}
                    clear
                >姓名</InputItem>
                <InputItem
                    {...getFieldProps('fnnumber')}
                    clear
                    placeholder="职工代码"
                >职工代码</InputItem>
                <InputItem
                    {...getFieldProps('fid', {
                        rules: [{required: true, message: "请输入身份证号"}]
                    })}
                    clear
                    placeholder="身份证号"
                >身份证号</InputItem>
                <InputItem
                    {...getFieldProps('fphone')}
                    clear
                    placeholder="手机号"
                    type="phone"
                >手机号</InputItem>
            </List>
            <WhiteSpace />
            <WhiteSpace />
            {(errors = getFieldError('required')) ? errors.join(',') : null}
            <Button type="primary" onClick={this.submit}>提交</Button>
        </div>);
    }
}
export default createForm()(Register);