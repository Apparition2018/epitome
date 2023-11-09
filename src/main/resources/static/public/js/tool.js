/** 获取 url 中的查询参数，并转化成对象 */
function getUrlSearchObj() {
    const search = location.search;
    const searchObj = {};
    if (search.indexOf("?") !== -1) {
        const paramStr = search.substring(1);
        const params = paramStr.split("&");
        for (let i = 0, len = params.length; i < len; i++) {
            searchObj[params[i].split("=")[0]] = decodeURI(params[i].split("=")[1]);
        }
    }
    return searchObj;
}

// 对Date的扩展，增加format方法，格式化日期
function addDateFormat() {
    Date.prototype.format = function (format) {
        const o = {
            "M+": this.getMonth() + 1,                      // 月份
            "d+": this.getDate(), 			                // 日
            "h+": this.getHours(), 		                    // 小时
            "m+": this.getMinutes(), 		                // 分
            "s+": this.getSeconds(), 		                // 秒
            "S": this.getMilliseconds(),                    // 毫秒
            "q+": Math.floor((this.getMonth() + 3) / 3)     // 季度
        };
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substring(4 - RegExp.$1.length));
        }
        for (const k in o) {
            if (new RegExp(`(${k})`).test(format))
                // RegExp 是 javascript 中的一个内置对象，RegExp.$1 是 RegExp的 一个属性，指匹配到的第一个字符串
                format = format.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substring(("" + o[k]).length)));
        }
        return format;
    }
}

/** 生成 UUID */
function generateUUID() {
    let time = new Date().getTime();
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        const random = (time + Math.random() * 16) % 16 | 0;
        time = Math.floor(time / 16);
        return (c === 'x' ? random : (random & 0x3 | 0x8)).toString(16);
    });
}

/** 生成 UUID */
function guid() {
    function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }

    return `${S4()}${S4()}-${S4()}-${S4()}-${S4()}-${S4()}${S4()}${S4()}`;
}

/** sleep */
function sleep(s) {
    for (let t = Date.now(); Date.now() - t <= s;) ;
}