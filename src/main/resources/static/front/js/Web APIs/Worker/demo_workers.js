self.onmessage = function (e) {

    millisecond = startMillisecond = e.data.millisecond;
    startTime = e.data.date.getTime();

    setTimeout(countTime, 10);
};

var startTime;
var millisecond;
var startMillisecond;
var error = 0;

function countTime() {
    millisecond += 1;
    postMessage(millisecond);
    var offset = new Date().getTime() - (startTime + (millisecond - startMillisecond) * 10);
    var nextTime = 10 - (offset + error);
    if (nextTime < 0) {
        error = 0 - nextTime;
        console.log(error);
        nextTime = 0;
    }
    setTimeout("countTime()", nextTime);
}