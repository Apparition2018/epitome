self.onmessage = function (e) {

    milliseconds = startMilliseconds = e.data.milliseconds;
    startTime = e.data.date.getTime();

    setTimeout(countTime, 10);
};

let startTime;
let milliseconds;
let startMilliseconds;
let error = 0;

function countTime() {
    milliseconds += 1;
    postMessage(milliseconds);
    let offset = new Date().getTime() - (startTime + (milliseconds - startMilliseconds) * 10);
    let nextTime = 10 - (offset + error);
    if (nextTime < 0) {
        error = 0 - nextTime;
        console.log(error);
        nextTime = 0;
    }
    setTimeout("countTime()", nextTime);
}