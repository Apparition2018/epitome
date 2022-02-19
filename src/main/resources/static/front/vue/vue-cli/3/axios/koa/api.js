const Api = require('koa-router');
const router = new Api();
const KoaBody = require('koa-body')({
    // 允许上传多个文件
    multipart: true,
});
let id = 3
let data = [
    {
        name: '张三',
        tel: '13000000000',
        id: 1
    },
    {
        name: '李四',
        tel: '13000000001',
        id: 2
    },
    {
        name: '王五',
        tel: '13000000002',
        id: 3
    }
];
router.get('/contactList', async (ctx) => {
    ctx.body = {
        code: 200,
        data: data
    }
});
// form-data
router.post('/contact/new/form', KoaBody, async (ctx) => {
    const newData = ctx.request.body
    id++
    newData.id = id
    data.push(newData)
    ctx.body = {
        code: 200,
        data: newData
    }
});
router.post('/contact/new/json', async (ctx) => {
    const newData = ctx.request.body
    id++
    newData.id = id
    data.push(newData)
    ctx.body = {
        code: 200,
        data: newData
    }
});
router.put('/contact/edit', async (ctx) => {
    const newData = ctx.request.body
    data.map((item, index) => {
        if (item.id === newData.id) {
            data[index] = newData
        }
    })
    ctx.body = {
        code: 200,
        data: newData
    }
});
router.patch('/contact/edit', async (ctx) => {
    const newData = ctx.request.body
    let patchData;
    data.map(item => {
        if (item.id === newData.id) {
            patchData = item
            Object.getOwnPropertyNames(newData).map(property => {
                item[property] = newData[property]
            })
        }
    })
    ctx.body = {
        code: 200,
        data: patchData
    }
});
router.del('/contact', async (ctx) => {
    const id = getQueryVariable(ctx.request.url, 'id')
    data = data.filter(item => item.id !== Number(id))
    ctx.body = {
        code: 200,
        message: '删除成功'
    }
});
router.get('/longtime', async (ctx) => {
    const query = () => {
        return new Promise((resolve, reject) => {
            setTimeout(function () {
                resolve('请求成功');
            }, 2000)
        })
    }
    const result = await query();
    ctx.body = {
        code: 200,
        message: result
    }
});

function getQueryVariable (url, variable) {
    const query = url.split('?')[1];
    const vars = query.split("&");
    for (let i = 0; i < vars.length; i++) {
        const pair = vars[i].split("=");
        if (pair[0] === variable) {
            return pair[1];
        }
    }
    return false;
}

module.exports = router;