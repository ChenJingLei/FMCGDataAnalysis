/**
 * Created by cjl20 on 2016/5/17.
 */
$(document).ready(function () {
    //focusblur
    jQuery.focusblur = function (focusid) {
        var focusblurid = $(focusid);
        var defval = focusblurid.val();
        focusblurid.focus(function () {
            var thisval = $(this).val();
            if (thisval == defval) {
                $(this).val("");
            }
        });
        focusblurid.blur(function () {
            var thisval = $(this).val();
            if (thisval == "") {
                $(this).val(defval);
            }
        });
    };

    $("#login").click(function () {
        $.ajax({
            type: "post",
            url: "http://localhost:8088/user/login",
            data: JSON.stringify(
                {
                    username: $("#username").val(),
                    password: $("#password").val()
                }),
            contentType: 'application/json',
            async: true,
            success: function (data) {
                if (data == "login") {
                    alert("用户名或密码错误");
                } else {
                    alert("登陆成功");
                }
                window.location.href = "/" + data;
            },
            error: function (data, status, e) {
                alert(e);
            }
        });
    });

    $("#username").blur(function () {
        var username = $('#username').val();
        if (username != "") {
            var data = {username: username};
            AjaxPost("http://localhost:8088/user/checkExists", data, $("#username"));
        }
    });

    $("#password").blur(function () {
        var username = $('#username').val();
        var password = $("#password").val();
        var data = {username: username, password: password};
        AjaxPost("http://localhost:8088/user/checkExists", data, $("#password"));
    });
    var AjaxPost = function (url, jsonStr, op) {
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(jsonStr),
            contentType: 'application/json',
            async: true,
            success: function (data) {
                if (!data) {
                    if(op == $("#username")) op.title = "用户不存在";
                    else op.title = "用户名或密码错误";
                    op.tipso('show');
                } else {
                    if(op == $("#username")) op.title = "用户存在";
                    else op.title = "用户名或密码正确";
                    op.tipso('hide');
                }
            },
            error: function (data, status, e) {
            }
        });
    }

});