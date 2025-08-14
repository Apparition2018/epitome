# 对比 .NET 和 Python

---
## 正则表达式
| 功能     | Java                                      | .NET                               | Python                                                  |
|--------|-------------------------------------------|------------------------------------|---------------------------------------------------------|
| 编译正则   | Pattern.compile(pattern)                  | new Regex(pattern)                 | re.compile(pattern)                                     |
| 从开头匹配  | matcher.lookingAt()                       | Regex.Match(text, "^pattern")      | re.match(pattern, text)<br>re.search(r'^pattern', text) |
| 任意位置搜索 | matcher.find()                            | Regex.Match(text, pattern)         | re.search(pattern, text)                                |
| 完全匹配   | matcher.matches()                         | Regex.Match(text, "^pattern$")     | re.fullmatch(pattern, text)                             |
| 查找所有匹配 | while(matcher.find()) { matcher.group() } | Regex.Matches(text, pattern)       | re.findall(pattern, text)<br>re.finditer(pattern, text) |
| 替换     | matcher.replaceAll(repl)                  | Regex.Replace(text, pattern, repl) | re.sub(pattern, repl, text)                             |
| 是否匹配   | matcher.find()                            | Regex.IsMatch(text, pattern)       | bool(re.search(pattern, text))                          |
---
## 同步锁
| .NET           | Python                                                 | Java                          | 适用场景   |
|----------------|--------------------------------------------------------|-------------------------------|--------|
| lock (Monitor) | RLock                                                  | ReentrantLock                 | 可重入    |
| Mutex          | portalocker（第三方）<br>multiprocessing.Lock（仅限同一程序的多个进程间） | FileLock                      | 跨进程    |
| SpinLock       | SpinLock.spin（第三方）                                     | AtomicBoolean + CAS           | 极短临界区  |
| AutoRestEvent  | Event + Lock<br>Condition                              | Semaphore<br>Lock + Condition | 单次线程唤醒 |
