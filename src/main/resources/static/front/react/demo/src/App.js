import React, { Component } from 'react'
import logo from './logo.svg'
import './App.css'
import NameCard from './components/NameCard'
import LikesButton from './components/LikesButton'
import DigitalClock from './components/DigitalClock'
import CommentBox from './components/CommentBox'
import CommentList from './components/CommentList'
import ThemContext from './theme-context'
import ThemeBar from './components/ThemeBar'

const themes = {
  light: {
    classnames: 'btn btn-primary',
    bgColor: '#eee',
    color: '#000'
  },
  dark: {
    classnames: 'btn btn-light',
    bgColor: '#222',
    color: '#fff'
  }
}

const tags = ['恐龙', '足球小子']

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      theme: 'light',
      comments: ['this is my first reply']
    }
    this.changeTheme = this.changeTheme.bind(this)
    this.addComment = this.addComment.bind(this)
  }

  changeTheme(theme) {
    this.setState({ theme })
  }

  addComment(comment) {
    this.setState({
      comments: [...this.state.comments, comment]
    })
  }

  render() {
    const { comments } = this.state
    return (
      <ThemContext.Provider value={themes[this.state.theme]}>
        <div className='App'>
          <header className='App-header'>
            <img src={logo} className='App-logo' alt='logo' />
            <p>
              Edit <code>src/App.js</code> and save to reload.
            </p>
            <a className='App-link' href='https://reactjs.org' target='_blank' rel='noopener noreferrer'>
              Learn React
            </a>
          </header>
          <a
            href='#theme-switcher'
            className='btn btn-light'
            onClick={() => {
              this.changeTheme('light')
            }}
          >
            浅色主题
          </a>
          <a
            href='#theme-switcher'
            className='btn btn-secondary'
            onClick={() => {
              this.changeTheme('dark')
            }}
          >
            深色主题
          </a>
          <ThemeBar />
          <hr />
          <NameCard name='King' number={1234567890} isHuman tags={tags} />
          <hr />
          <LikesButton />
          <hr />
          <DigitalClock />
          <hr />
          <CommentList comments={comments} />
          <CommentBox commentsLength={comments.length} onAddComment={this.addComment} />
        </div>
      </ThemContext.Provider>
    )
  }
}

export default App
