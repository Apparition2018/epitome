const DEMO_URL = 'https://dog.ceo/api/breeds/image/random'

/** 调节centered居中 */
$('.css-live-wrap').each(function () {
  const $this = $(this)
  const captionNum = $this.children('.caption').length
  const allCaptionHeight = 38 + (captionNum - 1) * 40
  const wrapHeight = parseInt($this.css('height'))
  const top = (((wrapHeight - allCaptionHeight) / 2 + allCaptionHeight) / wrapHeight) * 100 + '%'
  $this.find('.centered').css('top', top)
})
