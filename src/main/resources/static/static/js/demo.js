/* 调节centered居中 */
$('.css-live-wrap').each(function () {
    var $this = $(this),
        captionLen = $this.children('.caption').length,
        clwHeight = parseInt($this.css('height')),
        top;
    if (captionLen === 1) {
        top = (clwHeight + 38) / clwHeight / 2;
        $this.find('.centered').css('top', top * 100 + '%');
    }
    else if (captionLen === 2) {
        top = (clwHeight + 78) / clwHeight / 2;
        $this.find('.centered').css('top', top * 100 + '%');
    }
});

function demoLog(dom, msg) {
    dom.innerHTML += msg + '<br/>';
}