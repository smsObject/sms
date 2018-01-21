(function ($) {
    var $loadMaskDiv;
    var $loadMaskMsgDiv;

    var settings = {
        left: document.documentElement.clientWidth / 2 - 100,
        top: document.documentElement.clientHeight / 2 - 50,
        text: '加载中,请稍候...'
    };

    var loadMask = {
        show: function () {
            if (!$loadMaskDiv || !$loadMaskMsgDiv) {
                setTimeout(show, 10);
            } else {
                show();
            }
        },
        hide: function () {
            if (!$loadMaskDiv || !$loadMaskMsgDiv) {
                setTimeout(hide,10)
            } else {
                hide();
            }
        }
    };


    function show() {
        $loadMaskDiv = $loadMaskDiv ? $loadMaskDiv : $('.ext-loading-mask');
        $loadMaskMsgDiv = $loadMaskMsgDiv ? $loadMaskMsgDiv : $('.ext-loading-mask-msg');
        $loadMaskMsgDiv.text(settings.text).css({left: settings.left, top: settings.top}).show();
        $loadMaskDiv.show();
    }

    function hide() {
        $loadMaskDiv = $loadMaskDiv ? $loadMaskDiv : $('.ext-loading-mask');
        $loadMaskMsgDiv = $loadMaskMsgDiv ? $loadMaskMsgDiv : $('.ext-loading-mask-msg');
        $loadMaskMsgDiv.hide();
        $loadMaskDiv.hide();
    }

    $.extend({
        loadingMask: function (setting) {
            settings = $.extend({}, settings, setting);
            var $loadMaskDivTemp = $('.ext-loading-mask');
            if ($loadMaskDivTemp.length > 0) {
                $loadMaskDiv = $loadMaskDivTemp;
                $loadMaskMsgDiv = $loadMaskDiv.next(".ext-loading-mask-msg");
                return loadMask;
            }

            importCss();
            create();

            return loadMask;
        }
    });

    function create() {
        $(document.body).append('<div class="ext-loading-mask"></div>');
        $(document.body).append('<div class="ext-loading-mask-msg">' + settings.text + '</div>');
    }

    function importCss() {
        var fileref = document.createElement('link');
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href", '../css/load-mask.css');

        if (typeof fileref != "undefined") {
            document.getElementsByTagName("head")[0].appendChild(fileref);
        }
    }
})
($);