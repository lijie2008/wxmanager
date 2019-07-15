import React, {Component} from 'react'
import {List, DatePicker, WhiteSpace} from "antd-mobile";
const Item = List.Item;
function formatDate(date) {
    /* eslint no-confusing-arrow: 0 */
    const pad = n => n < 10 ? `0${n}` : n;
    const dateStr = `${date.getFullYear()}-${pad(date.getMonth() + 1)}`;
    return `${dateStr}`;
}
class Salary extends Component {
    constructor(props) {
        super(props);
        this.state = {date: null}
    }
    getSalary = async ()=>{
    }
    onChange=(date)=>{
        this.setState({date},this.getSalary)
    }
    render() {
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
            <List renderHeader={() => '查询结果'} className="my-list">
                <Item extra={1000}>	工资总额:FPA1006</Item>
                <Item extra={1001}>	应发工资:FPA15</Item>
                <Item extra={1002}>	实发工资FPA17</Item>
            </List>
            <List renderHeader={() => '工资明细'} className="my-list">
                <Item extra={1003}>基本工资: FPA19</Item>
                <Item extra={1004}>职位工资: FPA1007</Item>
                <Item extra={1005}>加班工资: FPA1008</Item>
                <Item extra={1006}>考核工资: FPA1009</Item>
                <Item extra={1007}>提成: FPA1010</Item>
                <Item extra={1008}>试用期工资: FPA1011</Item>
                <Item extra={1009}>试用期工资技术津贴: FPA1012</Item>
                <Item extra={1010}>医疗补助: FPA1013</Item>
                <Item extra={1011}>误餐补助: FPA1014</Item>
                <Item extra={1012}>交通补助：FPA1015</Item>
                <Item extra={1013}>生活补助：FPA1016</Item>
                <Item extra={1014}>考情、加班费：FPA1017</Item>
                <Item extra={1015}>其他补扣款：FPA23</Item>
                <Item extra={1016}>住房公积金: FPA22</Item>
                <Item extra={1017}>养老金：FPA1018</Item>
                <Item extra={1018}>失业保险金：FPA1019</Item>
                <Item extra={1019}>医疗保险金：FPA1020</Item>
                <Item extra={1020}>大病医疗：FPA1024</Item>
                <Item extra={1021}>合并扣税部分：FPA1021</Item>
                <Item extra={1022}>应纳税所得额：FPA1022</Item>
                <Item extra={1023}>本期预扣预缴税费：FPA18</Item>
                <Item extra={1024}>补退个税差额：FPA1023</Item>
            </List>
        </div>);
    }
}

export default Salary;