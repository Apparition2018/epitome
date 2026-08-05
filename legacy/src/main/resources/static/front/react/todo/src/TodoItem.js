import React from 'react'
import PropTypes from 'prop-types'

class TodoItem extends React.Component {
  constructor(props) {
    super(props)
    this.handleDelete = this.handleDelete.bind(this)
  }

  // 子组件如果想和父组件通信，子组件要调用父组件传递过来的方法
  handleDelete() {
    const { delete_, index } = this.props
    delete_(index)
  }

  render() {
    const { content } = this.props
    return <div onClick={this.handleDelete}>{content}</div>
  }
}

TodoItem.propTypes = {
  delete_: PropTypes.func.isRequired,
  index: PropTypes.number.isRequired,
  content: PropTypes.string.isRequired,
}

export default TodoItem
