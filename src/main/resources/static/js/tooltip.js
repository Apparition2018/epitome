/* tooltip */
var div = $('<div>');
div.attr('id', 'tooltip');
div.attr('class', 'tooltip');
div.css('display', 'none');
$('body').prepend(div);

var flag = 0, $tooltip = $('#tooltip');

; (function ($) {
    $.fn.tooltip = function (data) {
        $(this).each(function () {
            $(this).mouseenter(function (e) {
                $tooltip.empty();
                $tooltip.css('display', '');
                flag = 1
                $.each(data, function (i, v) {
                    let element;
                    if ($.isArray(v)) {
                        element = $('<ul>');
                        v.forEach((e) => {
                            let li = $('<li>');
                            li.text(e);
                            element.append(li);
                        })
                    } else {
                        element = $('<div>');
                        element.text(v);
                    }
                    $tooltip.append(element).append('<hr/>');
                })
                $('#tooltip hr:last-child').remove();
            })
            $(this).mouseleave(function () {
                $tooltip.css('display', 'none');
                flag = 0;
            })
        })
    }
})(jQuery);

$(document).mousemove(function (e) {
    if (flag) {
        e = e || event;

        var top, left;

        if (e.clientY + $tooltip.outerHeight() > $(window).height()) {
            top = e.clientY - $tooltip.outerHeight() + 'px'
        } else {
            top = e.clientY + 'px'
        }

        if (e.clientX + $tooltip.outerWidth() > $(window).width()) {
            left = e.clientX - $tooltip.outerWidth() + 'px'
        } else {
            left = e.clientX + 'px'
        }

        $tooltip.css('top', top);
        $tooltip.css('left', left);
    }
})