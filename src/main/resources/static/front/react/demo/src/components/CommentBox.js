import React from 'react'

class CommentBox extends React.Component {
  constructor (props) {
    super(props)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleSubmit (event) {
    this.props.onAddComment(this.textInput.value)
    event.preventDefault()
  }

  render () {
    return (
      <form className='p-2 px-5' onSubmit={this.handleSubmit}>
        <div className='form-group'>
          <label>留言内容</label>
          <input
            type='text'
            className='form-control'
            placeholder='请输入内容'
            ref={(textInput) => { this.textInput = textInput }}
          />
        </div>
        <button type='submit' className='btn btn-primary'>
          留言
        </button>
        <p>已有{this.props.commentsLength}条评论</p>
      </form>
    )
  }
}

export default CommentBox
