import React, {Component} from 'react'
import {List, DatePicker, WhiteSpace, Toast} from "antd-mobile";
import instance from "../../common/instance";
const Item = List.Item;
function formatDate(date) {
    const pad = n => n < 10 ? `0${n}` : n;
    const dateStr = `${date.getFullYear()}-${pad(date.getMonth() + 1)}`;
    return `${dateStr}`;
}
class Salary extends Component {
    constructor(props) {
        super(props);
        this.state = {
            baseInfo: this.getPrams(),
            date: new Date(),
            result: {}
        }
    }
    componentDidMount() {
        this.getSalary();
    }
    getPrams() {
        let wxparams = {};
        try {
            let pstr = window.sessionStorage.getItem("wxinfo") || "{}"
            wxparams = JSON.parse(pstr);
        } catch(e) {
            console.log(e);
        }
        return wxparams;
    }
    getSalary = async ()=>{
        let {date, baseInfo} = this.state;
        let body = instance.post("/wx/salary/query", Object.assign({},baseInfo,{date}));
        let res = body.data;
        if(res.code === "000") {
            this.setState({result: res.data || {}});
        } else {
            Toast.info(res.message || "查询失败");
        }
    }
    onChange=(date)=>{
        this.setState({date}, this.getSalary)
    }
    render() {
        let {result} = this.state;
        result = result || {};
        return (<div>
            <List className="date-picker-list" renderHeader={() => '工资查询'}  style={{backgroundColor: 'white'}}>
                <DatePicker
                    mode="month"
                    value={this.state.date}
                    format={val => `${formatDate(val)}`}
                    onChange={this.onChange}
                >
                    <List.Item arrow="horizontal">选择日期</List.Item>
                </DatePicker>
            </List>
            <WhiteSpace />
            <List renderHeader={() => '查询结果'}>
                <Item extra={result["fpa1006"] || 0}>工资总额</Item>
                <Item extra={result["fpa15"] || 0}>	应发工资</Item>
                <Item extra={result["fpa17"] || 0}>	实发工资</Item>
            </List>
            <WhiteSpace />
            <List renderHeader={() => '工资明细'}>
                <Item extra={result["fpa19"] || 0}>基本工资</Item>
                <Item extra={result["fpa1007"] || 0}>职位工资</Item>
                <Item extra={result["fpa1008"] || 0}>加班工资</Item>
                <Item extra={result["fpa1009"] || 0}>考核工资</Item>
                <Item extra={result["fpa1010"] || 0}>提成</Item>
                <Item extra={result["fpa1011"] || 0}>试用期工资</Item>
                <Item extra={result["fpa1012"] || 0}>试用期工资技术津贴</Item>
                <Item extra={result["fpa1013"] || 0}>医疗补助</Item>
                <Item extra={result["fpa1014"] || 0}>午餐补助</Item>
                <Item extra={result["fpa1015"] || 0}>交通补助：</Item>
                <Item extra={result["fpa1016"] || 0}>生活补助：</Item>
                <Item extra={result["fpa1017"] || 0}>考勤/加班费：</Item>
                <Item extra={result["fpa23"] || 0}>其他补扣款：</Item>
                <Item extra={result["fpa22"] || 0}>住房公积金</Item>
                <Item extra={result["fpa1018"] || 0}>养老金</Item>
                <Item extra={result["fpa1019"] || 0}>失业保险金</Item>
                <Item extra={result["fpa1020"] || 0}>医疗保险金</Item>
                <Item extra={result["fpa1024"] || 0}>大病医疗</Item>
                <Item extra={result["fpa1021"] || 0}>合并扣税部分</Item>
                <Item extra={result["fpa1022"] || 0}>应纳税所得额</Item>
                <Item extra={result["fpa18"] || 0}>本期预扣预缴税费</Item>
                <Item extra={result["fpa1023"] || 0}>补退个税差额</Item>
            </List>
        </div>);
    }
}

export default Salary;