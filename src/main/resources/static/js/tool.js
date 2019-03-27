// 获取url中的传的值，并转化成对象
function getQueryStringObj() {
    var query = location.search; // url中?后的字串,包括?
    var theRequest = {};
    if (query.indexOf("?") !== -1) {
        var str = query.substr(1);
        var strs = str.split("&");
        for (var i = 0, len = strs.length; i < len; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

// 对Date的扩展，增加format方法，格式化日期
function addDateFormat() {
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1,                  // 月份 
            "d+": this.getDate(), 			            // 日 
            "h+": this.getHours(), 		                // 小时 
            "m+": this.getMinutes(), 		            // 分 
            "s+": this.getSeconds(), 		            // 秒 
            "S": this.getMilliseconds(), 	            // 毫秒 
            "q+": Math.floor((this.getMonth() + 3) / 3)// 季度 
        };
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format))
                // RegExp是javascript中的一个内置对象，RegExp.$1是RegExp的一个属性，指匹配到的第一个字符串
                format = format.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
        return format;
    }
}

// 生成UUID 1
function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
};

// 生成UUID 2
function guid() {
    function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }
    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}

// sleep
function sleep(s) {
    for (let t = Date.now(); Date.now() - t <= s;);
}