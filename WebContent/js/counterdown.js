(function($){
    var countdown = function(item, config)
    {
        var seconds = parseInt($(item).attr(config.attribute));
        var timer = null;
        var doWork = function()
        {
            if(seconds >= 0)
            {
                if(typeof(config.callback) == "function")
                {
                    var data = {
                        total : seconds ,
                        second : Math.floor(seconds % 60) ,
                        minute : Math.floor((seconds / 60) % 60) ,
                        hour : Math.floor((seconds / 3600) % 24) ,
                        day : Math.floor(seconds / 86400)
                    };
                    config.callback.call(item, seconds, data, item);
                }
                seconds --;
            }else{
                window.clearInterval(timer);
            }
        }
        timer = window.setInterval(doWork, 1000);
        doWork();
    };
    var main = function()
    {
        var args = arguments;
        var config = { attribute : 'data-seconds', callback : null };
        if(args.length == 1)
        {
            if(typeof(args[0]) == "function") config.callback = args[0];
            if(typeof(args[0]) == "object") $.extend(config, args[0]);
        }else{
            config.attribute = args[0];
            config.callback = args[1];
        }
        $(this).each(function(index, item){
            countdown.call(item, item, config);
        });
    };
    $.fn.countdown = main;
})(jQuery);