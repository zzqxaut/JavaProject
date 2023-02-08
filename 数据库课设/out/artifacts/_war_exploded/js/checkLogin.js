$(document).ready(function (){
    $("#register").click(function () {
       location.href = "register.jsp";
    });
    $("#submit").click(function () {
        var username = $("#username");
        var password = $("#password");
        var checkCode = $("#checkCode");
        var inform = $("#inform");

        if (username.val() == "")
        {
            inform[0].innerHTML = "用户名不能为空";
            inform[0].style.display = "block";
            username.focus();
            return false;
        }else {
            inform[0].style.display = "none";
        }

        if (password.val() == "")
        {
            inform[0].innerHTML = "密码不能为空";
            inform[0].style.display = "block";
            password.focus();
            return false;
        }else {
            inform[0].style.display = "none";
        }

        if (checkCode.val() == "")
        {
            inform[0].innerHTML = "验证码不能为空";
            inform[0].style.display = "block";
            checkCode.focus();
            return false;
        } else {
            inform[0].style.display = "none";
        }

        $.post("LoginServlet", {
            username: username.val(),
            password: password.val(),
            usertype: $('input[name="usertype"]:checked').val(),
            checkCode: checkCode.val()
        }, function (data){
            if (data == 2){
                location.href = "bookmanagersystem.jsp";
            } else if (data == 1) {
                location.href = "booksystem.jsp";
            } else if (data == 0){
                inform[0].innerHTML = "验证码错误";
                inform[0].style.display = "block";
                checkCode.focus();
            } else if (data == -1){
                inform[0].innerHTML = "用户名或密码错误";
                inform[0].style.display = "block";
                username.focus();
             }else if (data == -2){
                inform[0].innerHTML = "用户类型错误";
                inform[0].style.display = "block";
            }
        });

    });
});
