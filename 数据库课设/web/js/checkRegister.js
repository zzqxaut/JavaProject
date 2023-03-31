
//检查登录
function change(){
    var pic=document.getElementById("picture");
    var i=Math.random();//目的是使页面不一样
    pic.src="validate.jsp?id="+i;
}

$(document).ready(function (){
    $("#cancel").click(function () {
        location.href = "login.jsp";
    });
    $("#submit").click(function () {
        var username = $("#username");
        var password1 = $("#password1");
        var password2 = $("#password2");
        var truename = $("#truename");
        var phonenumber = $("#phonenumber");
        var email = $("#email");
        var checkCode = $("#checkCode");
        var inform = $("#inform");

        var regusername = /^[0-9a-zA-Z]{6,15}$/;
        var regpassword = /^[0-9a-zA-Z.+*_]{6,15}$/;
        var regtruename = /^([\u0391-\uFFE5]{2,4}|[a-zA-Z\\.\s]{3,50})$/;
        var regphonenumber = /^1[3456789]\d{9}$/;
        var regemail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/;

        if (username.val() == "")
        {
            inform[0].innerHTML = "用户名不能为空";
            inform[0].style.display = "block";
            username.focus();
            return false;
        }else if (!regusername.test(username.val())){
            inform[0].innerHTML = "用户名应为6-15位数字或字母";
            inform[0].style.display = "block";
            username.focus();
            return false;
        }else {
            inform[0].style.display = "none";
        }

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

        if (truename.val() == "")
        {
            inform[0].innerHTML = "姓名不能为空";
            inform[0].style.display = "block";
            truename.focus();
            return false;
        }else if (!regtruename.test(truename.val())){
            inform[0].innerHTML = "姓名格式错误";
            inform[0].style.display = "block";
            truename.focus();
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
        }else if (!regphonenumber.test(phonenumber.val())){
            inform[0].innerHTML = "手机号应为11位数字";
            inform[0].style.display = "block";
            phonenumber.focus();
            return false;
        }else {
            inform[0].style.display = "none";
        }

        if (email.val() == "")
        {
            inform[0].innerHTML = "邮箱不能为空";
            inform[0].style.display = "block";
            email.focus();
            return false;
        }else if (!regemail.test(email.val())){
            inform[0].innerHTML = "邮箱格式错误";
            inform[0].style.display = "block";
            email.focus();
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

        $.post("RegisterServlet", {
            username: username.val(),
            password: password1.val(),
            truename: truename.val(),
            phonenumber: phonenumber.val(),
            email: email.val(),
            usertype: $('input[name="usertype"]:checked').val(),
            checkCode: checkCode.val()
        }, function (data){
            if (data == 1) {
                location.href = "login.jsp";
            } else if (data == -1) {
                inform[0].innerHTML = "验证码错误";
                inform[0].style.display = "block";
                checkCode.focus();
            } else if (data == 0){
                inform[0].innerHTML = "用户名已存在";
                inform[0].style.display = "block";
                username.focus();
            }
        });
    });
});
