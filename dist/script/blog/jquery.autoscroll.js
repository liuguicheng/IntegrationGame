/*
* jquery.autoscroll
* Copyright (c) 2012-2013 CERC HuangAnzhi 2011-07-06
* huanganzhi@cerc.net.cn
* Version 1.0.0 build 20131125
* Requires jquery v1.4.4+
*ʹ��˵����$("#wrap").autoscroll([options]);
*�磺$("#wrap").autoscroll({height:125,slider:"table",node:"tr"});
*����˵��
*height: �߶ȣ���ֵ�ͣ�
*line: ÿ�ι���������Ĭ��ֵ1
*speed: �����ٶȣ���ֵԽ���ٶ�Խ������ֵ�ͣ�Ĭ��1000�����룩
*delay: ������ʱ��������ֵ�ͣ�Ĭ��3000�����룩
*slider: ������Ԫ�أ��ַ����ͣ�Ĭ��ֵ"ul"
 *node: ��Ԫ��DOM���ַ����ͣ�Ĭ��ֵ"li"
*�������⣺
*���������ݲ�����ȫռ������ʱ�����������󣬻����������������
*/

(function ($) {
    $.fn.autoscroll = function (options) {

        var opts = $.extend({}, $.fn.autoscroll.defaults, options);

        var wrap = this.eq(0).find(opts.slider + ":first");
        var lineHeight = wrap.find(opts.node + ":first").height();

        $(this).css({ "height": +opts.height + "px", "overflow": "hidden" });

        var upHeight = 0 - opts.line * lineHeight;

        //��������
        scrollUp = function () {
            wrap.animate({
                marginTop: upHeight
            }, opts.speed, function () {
                for (i = 1; i <= opts.line; i++) {
                    wrap.find(opts.node + ":first").appendTo(wrap);
                }
                wrap.css({ marginTop: 0 });
            });
        };
        //����¼���
        wrap.hover(function () {
            if (delayID != null) {
                clearInterval(delayID);
            }
        }, function () {
            delayID = setInterval("scrollUp()", opts.delay);
        }).mouseout();
    };

    $.fn.autoscroll.defaults = {
        height: 200,
        line: 1,
        speed: 1000,
        delay: 3000,
        slider: "ul",
        node: "li"
    };

})(jQuery);