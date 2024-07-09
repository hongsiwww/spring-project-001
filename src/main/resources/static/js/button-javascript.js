/*實現按下"點我登入"後的動作*/

$(function(){
    $(".login-button a").click(function(){
        $(".login").animate({height:"toggle",opacity:"toggle"},"slow");
    });
    $(".closediv a").click(function(){
        $(".login").animate({height:"toggle",opacity:"toggle"},"slow");
    });

    $("a[href*=#]").click(function(){
        var target=$(this.hash);
        $("html,body").animate({
            scrollTop:target.offset().top
        },"slow");
        return false;
    });
})

