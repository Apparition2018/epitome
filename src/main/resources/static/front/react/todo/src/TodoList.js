import React, {Component, Fragment} from 'react';
import TodoItem from "./TodoItem";


// 定义一个 React 组件
class TodoList extends Component {
    constructor (props) {
        super(props);
        this.state = {
            list: [],
            inputValue: ''
        }

        // 可提升性能 ?
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleBtnClick = this.handleBtnClick.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleBtnClick () {
        if (this.state.inputValue) {
            this.setState({
                list: [...this.state.list, this.state.inputValue],
                inputValue: ''
            })
        }
    }

    handleInputChange (e) {
        this.setState({
            inputValue: e.target.value
        })
    }

    handleItemClick (index) {
        // 建议复制副本操作
        const list = [...this.state.list];
        list.splice(index, 1);
        this.setState({list})
    }

    handleDelete (index) {
        const list = [...this.state.list];
        list.splice(index, 1);
        this.setState({list})
    }

    getTodoItems () {
        return (
            this.state.list.map((item, index) => {
                // 父组件通过属性的形式向子组件传递参数
                // 子组件通过 props 接受父组件传递过来的参数
                return (
                    <TodoItem
                        delete_={this.handleDelete}
                        key={index}
                        content={item}
                        index={index}
                    />
                )
                // return <li key={index} onClick={this.handleItemClick.bind(this, index)}>{item}</li>
            })
        )
    }

    render () {
        return (
            <Fragment>
                <div>
                    <input value={this.state.inputValue} onChange={this.handleInputChange}/>
                    <button className='red-btn' onClick={this.handleBtnClick}>add</button>
                </div>
                <ul>
                    {this.getTodoItems()}
                </ul>
            </Fragment>
        );
    }
}

export default TodoList;
