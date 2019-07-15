import React, { Component } from 'react'
import {createForm, formShape } from 'rc-form';
import {List, InputItem, WhiteSpace, Button, Toast} from 'antd-mobile';
import instance from "../../common/instance";
class Register extends Component {
    static propTypes = {
        form: formShape,
    };
    constructor(props) {
        super(props);
        this.state = {
            baseInfo: this.getPrams(),
            userData: {}
         }
    }
    componentDidMount() {
        this.getUserInfo();
    }
    getPrams() {
        let wxparams = {};
        try {
            let pstr = window.sessionStorage.getItem("wxinfo") || "{}"
            wxparams = JSON.parse(pstr);
        } catch (e) {
            console.log(e);
        }
        return wxparams;
    }
    async getUserInfo() {
        let {baseInfo} = this.state;
        let body = await instance.post("/wx/emp", baseInfo);
        let res = body.data;
        if(res && res.code === "000") {
            let userData = res.data || {};
            this.props.form.setFieldsValue({
                fname: userData.fname || "",
                fnumber: userData.fnumber || "",
                fid: userData.fid || "",
                fphone: userData.fphone || ""
            });
        } else {
            Toast.info(res ? res.message : "加载失败!");
        }

    }
    submit = () => {
        let {baseInfo} = this.state;
        this.props.form.validateFields(async (error, value) => {
            if(!error) {
                let body = await instance.post("/wx/save", Object.assign({}, {emp: value}, baseInfo));
                let res = body.data;
                if(res && res.code === "000") {
                    Toast.success("注册成功！");
                    window.close();
                } else {
                    Toast.info(res.message || "[未知原因]注册失败!");
                }
            } else {
                try {
                    let message = Object.values(error)[0].errors[0].message;
                    Toast.info(message);
                } catch (error) {
                    console.log(error);
                }
            }
        });
    }
    render() {
        const {getFieldProps} = this.props.form;
        return (<div>
            <List renderHeader={() => '基本信息'}>
                <InputItem
                    placeholder="姓名"
                    {...getFieldProps('fname', {
                        rules: [{required: true, message: "请输入姓名"}]
                    })}
                    clear
                >姓名<span style={{color: "red"}}>*</span></InputItem>
                <InputItem
                    {...getFieldProps('fnumber')}
                    clear
                    placeholder="职工代码"
                >职工代码</InputItem>
                <InputItem
                    {...getFieldProps('fid', {
                        rules: [{required: true, message: "请输入身份证号"},{
                            pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]/,
                            message: "身份证号格式错误"
                        }]
                    })}
                    clear
                    placeholder="身份证号"
                >身份证号<span style={{color: "red"}}>*</span></InputItem>
                <InputItem
                    {...getFieldProps('fphone')}
                    clear
                    placeholder="手机号"
                    type="phone"
                >手机号</InputItem>
            </List>
            <WhiteSpace />
            <WhiteSpace />
            <Button type="primary" style={{margin: 10}} onClick={this.submit}>提交</Button>
        </div>);
    }
}
export default createForm()(Register);