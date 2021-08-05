/* 调节centered居中 */
$('.css-live-wrap').each(function () {
    var $this = $(this),
        captionLen = $this.children('.caption').length,
        clwHeight = parseInt($this.css('height')),
        top;
    if (captionLen === 1) {
        // top = (38 + (clwHeight - 38) / 2) / clwHeight; // 简化得到下式
        top = (clwHeight + 38) / clwHeight / 2;
        $this.find('.centered').css('top', top * 100 + '%');
    }
    else if (captionLen === 2) {
        top = (clwHeight + 78) / clwHeight / 2;
        $this.find('.centered').css('top', top * 100 + '%');
    }
    else if (captionLen === 3) {
        top = (clwHeight + 118) / clwHeight / 2;
        $this.find('.centered').css('top', top * 100 + '%');
    }
});