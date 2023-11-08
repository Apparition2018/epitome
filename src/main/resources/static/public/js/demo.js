const demo_url = 'https://dog.ceo/api/breeds/image/random';
/* 调节centered居中 */
$('.css-live-wrap').each(function () {
    let $this = $(this),
        captionNum = $this.children('.caption').length,
        allCaptionHeight = 38 + (captionNum - 1) * 40,
        wrapHeight = parseInt($this.css('height')),
        top = ((wrapHeight - allCaptionHeight) / 2 + allCaptionHeight) / wrapHeight * 100 + '%';
    $this.find('.centered').css('top', top);
});
