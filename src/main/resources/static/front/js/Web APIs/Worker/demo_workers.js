self.onmessage = function (e) {

    millisecond = startMillisecond = e.data.millisecond;
    startTime = e.data.date.getTime();

    setTimeout(countTime, 10);
};

let startTime;
let millisecond;
let startMillisecond;
let error = 0;

function countTime() {
    millisecond += 1;
    postMessage(millisecond);
    let offset = new Date().getTime() - (startTime + (millisecond - startMillisecond) * 10);
    let nextTime = 10 - (offset + error);
    if (nextTime < 0) {
        error = 0 - nextTime;
        console.log(error);
        nextTime = 0;
    }
    setTimeout("countTime()", nextTime);
}