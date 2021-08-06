# Element-UI

---
## 参考网站
1. [Element - 网站快速成型工具](https://element.eleme.cn/#/zh-CN)
---
## Basic
### Layout 布局
>#### Row Attributes
>```
>   gutter               栅格间隔
>```
>#### Col Attributes
>```
>   span                 栅格占据的列数
>```
### Dialog 对话框

---
## Notice 
### Message 消息提示
    this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
    });
### MessageBox 弹框
    this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(() => {
        this.$message({
            type: 'success',
            message: '删除成功!'
        });
    }).catch(() => {
        this.$message({
            type: 'info',
            message: '已取消删除'
        });          
    });
---