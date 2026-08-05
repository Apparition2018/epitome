import React from 'react'
import PropTypes from 'prop-types'

const CommentList = ({ comments }) => {
  return (
    <div className='px-5 mb-2'>
      <label>评论列表</label>
      <ul className='list-group'>
        {comments.map((comment, index) => (
          <li key={index} className='list-group-item list-group-item-success'>
            {comment}
          </li>
        ))}
      </ul>
    </div>
  )
}

CommentList.propTypes = {
  comments: PropTypes.array.isRequired,
}

export default CommentList
