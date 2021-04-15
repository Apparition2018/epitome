import React from 'react'

const CommentList = ({comments}) => {
    return (
        <div className="px-5 mb-2">
            <label>评论列表</label>
            <ul className="list-group">
                {
                    comments.map((comment, index) =>
                        <li key={index} className="list-group-item list-group-item-success">{comment}</li>
                    )
                }
            </ul>
        </div>
    )
}

export default CommentList