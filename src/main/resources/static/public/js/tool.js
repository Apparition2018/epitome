/** 生成 UUID */
function generateUUID () {
  let time = new Date().getTime()
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    const random = (time + Math.random() * 16) % 16 | 0
    time = Math.floor(time / 16)
    return (c === 'x' ? random : (random & 0x3 | 0x8)).toString(16)
  })
}

/** 生成 UUID */
function guid () {
  function S4 () {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
  }

  return `${S4()}${S4()}-${S4()}-${S4()}-${S4()}-${S4()}${S4()}${S4()}`
}

/** sleep */
function sleep (s) {
  for (let t = Date.now(); Date.now() - t <= s;) ;
}
