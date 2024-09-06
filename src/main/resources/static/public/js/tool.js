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
  for (let t = Date.now(); Date.now() - t <= s;) { /* empty */ }
}

/** 验证居民身份证：http://www.ip33.com/shenfenzheng.html */
function isValidChineseIdCard (idCard) {
  // 校验身份证号码长度
  if (idCard.length !== 18) {
    return false
  }

  // 系数数组
  const coefficients = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]
  // 校验码对照表
  const checkCodes = '10X98765432'

  // 计算前17位乘积和
  let sum = 0
  for (let i = 0; i < 17; i++) {
    sum += parseInt(idCard[i]) * coefficients[i]
  }

  // 计算校验码
  const calculatedCheckCode = checkCodes[sum % 11]

  // 比较计算出的校验码与身份证上的校验码是否一致
  return calculatedCheckCode.toUpperCase() === idCard[17].toUpperCase()
}

/** 验证香港永久性居民身份证：C668668(E) */
function isValidHongKongIdCard (idCard) {
  return /^[A-Z]{1,2}\d{6,10}[(\d)|A]{3}$/.test(idCard)
}

/** 验证澳门永久性居民身份证：5215299(8) */
function isValidMacaoIdCard (idCard) {
  return /^[157][0-9]{6}[(\d)]{3}$/.test(idCard)
}

function getAgeByIdCard (idCard) {
  const birthYear = parseInt(idCard.slice(6, 10))
  const birthMonth = parseInt(idCard.slice(10, 12))
  const birthDay = parseInt(idCard.slice(12, 14))

  const now = new Date()
  const currentYear = now.getFullYear()
  const currentMonth = now.getMonth() + 1
  const currentDay = now.getDate()

  let age = currentYear - birthYear
  if (currentMonth < birthMonth || (currentMonth === birthMonth && currentDay < birthDay)) {
    age--
  }

  return age
}
