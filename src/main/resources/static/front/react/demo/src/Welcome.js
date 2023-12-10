import React from 'react'

class Welcome extends React.Component {
  render () {
    const test = <h1>Hello React</h1>
    console.log(test)
    return (
      React.createElement('div', null, /* #__PURE__ */React.createElement('h1', null, 'Hello React'))
    )
  }
}

export default Welcome
