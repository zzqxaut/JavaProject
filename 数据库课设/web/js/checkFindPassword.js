

$(document).ready(function (){
    $("#cancel").click(function () {
        location.href = "login.jsp";
    });

    $("#last").click(function () {
        location.href = "checkphonenumber.jsp";
    });

    $("#submit").click(function () {
        var username = $("#username");
        var password1 = $("#password1");
        var password2 = $("#password2");
        var inform = $("#inform");

        var regpassword = /^[0-9a-zA-Z.+*_]{6,15}$/;

        if (password1.val() == "")
        {
            inform[0].innerHTML = "密码不能为空";
            inform[0].style.display = "block";
            password1.focus();
            return false;
        }else if (!regpassword.test(password1.val())){
            inform[0].innerHTML = "密码应为6-15位数字、字母或字符";
            inform[0].style.display = "block";
            password1.focus();
            return false;
        }else {
            inform[0].style.display = "none";
        }

        if (password2.val() == "")
        {
            inform[0].innerHTML = "请再次填写密码";
            inform[0].style.display = "block";
            password2.focus();
            return false;
        } else if (password1.val() != password2.val())
        {
            inform[0].innerHTML = "两次填写密码不同";
            inform[0].style.display = "block";
            password2.focus();
            return false;
        }else {
            inform[0].style.display = "none";
        }

        $.post("UpdatePasswordServlet", {
            username: username.val(),
            password: password1.val()
        }, function (data){
            if (data == 1) {
                location.href = "login.jsp";
            }else if (data == 0){
                alert("修改密码失败！")
            }
        });

    });

    $("#next").click(function () {
        var username = $("#username");
        var phonenumber = $("#phonenumber");
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

        if (phonenumber.val() == "")
        {
            inform[0].innerHTML = "手机号码不能为空";
            inform[0].style.display = "block";
            phonenumber.focus();
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

        $.post("FindPasswordServlet", {
            username: username.val(),
            phonenumber: phonenumber.val(),
            checkCode: checkCode.val()
        }, function (data){
            if (data == 1) {
                location.href = "findpassword.jsp?username=" + username.val() + "&phonenumber=" + phonenumber.val();
            } else if (data == -1){
                inform[0].innerHTML = "验证码错误";
                inform[0].style.display = "block";
                checkCode.focus();
            } else if (data == 0){
                inform[0].innerHTML = "用户名或手机号输入错误";
                inform[0].style.display = "block";
                username.focus();
            }
        });

    });
});
