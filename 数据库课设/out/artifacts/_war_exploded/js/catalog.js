function clickInform() {
    document.getElementById("demo2").innerHTML = "<div class=\"layui-bg-white\" style=\"padding: 30px;\" id=\"body\"></div>"
    $.getJSON("InformServlet", function(data) {
        $.each(data, function(i) {
            var t = ""
            t += "<div class=\"layui-row layui-col-space15\">\n" +
                "        <div class=\"layui-col-md6\">\n" +
                "            <div class=\"layui-card\">\n" +
                "                <div class=\"layui-card-header layui-bg-gray\" id=\"inform_title\">\n";
            t += "<div class=\"layui-inline\">";
            t += data[i].title;
            t += "</div> <div class=\"layui-inline\" id=\"data\">" + data[i].infotime;

            t += "</div>\n"
            t += "</div>\n" +
                "                <div class=\"layui-card-body\" id=\"inform_body\">\n";
            t+= data[i].content;
            t+="                </div>\n" +
                "            </div>\n" +
                "    </div>";

            $("#body").append(t);
        });
    });
}

function clickUserInfo() {
    document.getElementById("demo2").innerHTML = "<form class=\"layui-form layui-form-pane\n\" action=\"\">\n" +
        "                    <div class=\"layui-form-item\">\n" +
        "                        <label class=\"layui-form-label\">姓名</label>\n" +
        "                        <div class=\"layui-input-inline\">\n" +
        "                            <input id=\"truename\" type=\"text\" name=\"username\" lay-verify=\"required\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "\n" +
        "                    <div class=\"layui-form-item\">\n" +
        "                        <div class=\"layui-inline\">\n" +
        "                            <label class=\"layui-form-label\">用户名</label>\n" +
        "                            <div class=\"layui-input-inline\">\n" +
        "                                <input id=\"username\" type=\"text\" name=\"email\" lay-verify=\"email\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-inline\">\n" +
        "                            <label class=\"layui-form-label\">手机号</label>\n" +
        "                            <div class=\"layui-input-inline\">\n" +
        "                                <input id=\"phonenumber\" type=\"tel\" name=\"phone\" lay-verify=\"required|phone\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "\n" +
        "                    </div>\n" +
        "\n" +
        "                    <div class=\"layui-form-item\">\n" +
        "                        <div class=\"layui-inline\">\n" +
        "                            <label class=\"layui-form-label\">邮箱</label>\n" +
        "                            <div class=\"layui-input-inline\">\n" +
        "                                <input id=\"email\" type=\"text\" name=\"email\" lay-verify=\"email\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "\n" +
        "                        <div class=\"layui-inline\">\n" +
        "                            <label class=\"layui-form-label\">用户类型</label>\n" +
        "                            <div class=\"layui-input-inline\">\n" +
        "                                <input id=\"usertype\" type=\"text\" name=\"number\" lay-verify=\"required|number\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "\n" +
        "                    <div class=\"layui-form-item\">\n" +
        "                        <div class=\"layui-inline\">\n" +
        "                            <label class=\"layui-form-label\">借书数量</label>\n" +
        "                            <div class=\"layui-input-inline\">\n" +
        "                                <input id=\"borrownum\" type=\"text\" name=\"email\" lay-verify=\"email\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-inline\">\n" +
        "                            <label class=\"layui-form-label\">余额</label>\n" +
        "                            <div class=\"layui-input-inline\" style=\"width: 100px;\">\n" +
        "                                <input id=\"balance\" type=\"text\" name=\"price_min\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </form>"


    $.getJSON("UserinfoServlet?username=" + username,function (data){
        $("#username").val(data[0].username);
        $("#truename").val(data[0].truename);
        $("#phonenumber").val(data[0].phonenumber);
        $("#email").val(data[0].email);
        $("#usertype").val(data[0].usertype);
        $("#borrownum").val(data[0].borrownum)
        $("#balance").val(Math.floor(data[0].balance * 100) / 100);
    })
}

function clickBookList() {
    document.getElementById("demo2").innerHTML =
        "                    <form class=\"layui-form layui-form-pane\" action=\"\" style=\"margin-top: 10px;\">\n" +
        "                        <div class=\"layui-form-item\">\n" +
        "                            <div class=\"layui-inline\" id='firstline'>" +
        "                            </div>\n" +
        "                            <div class=\"layui-inline\" style='float: right'>" +
            "                            <label class=\"layui-form-label\">查询书籍</label>\n" +
            "                            <div class=\"layui-input-block\">\n" +
            "                                <input type=\"text\" name=\"bookname\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入书名\" class=\"layui-input\" style=\"width:200px;float:left\" id=\"seekBookname\">\n" +
            "                                <input type=\"button\" class=\"layui-btn\" lay-submit=\"\" lay-filter=\"demo1\" onclick=\"showlist();\" value=\"查询\"></input>\n" +
            "                            </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </form>\n" +
        "\n" +
        "                    <div class=\"layui-form\" >\n" +
        "                        <table class=\"layui-table\" id=\"booktable\"></table>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    </div>\n"

    showlist()
}

function clickReturnBook() {
    document.getElementById("demo2").innerHTML =
        "                    <form class=\"layui-form layui-form-pane\" action=\"\" style=\"margin-top: 10px;\">\n" +
        "                        <div class=\"layui-form-item\">\n" +
        "                            <div class=\"layui-inline\" id='firstline'>" +
        "                            </div>\n" +
        "                            <div class=\"layui-inline\" style='float: right'>" +
        "                            <label class=\"layui-form-label\">查询书籍</label>\n" +
        "                            <div class=\"layui-input-block\">\n" +
        "                                <input type=\"text\" name=\"bookname\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入书名\" class=\"layui-input\" style=\"width:200px;float:left\" id=\"seekBookname\">\n" +
        "                                <input type=\"button\" class=\"layui-btn\" lay-submit=\"\" lay-filter=\"demo1\" onclick=\"showReturnList();\" value=\"查询\"></input>\n" +
        "                            </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </form>\n" +
        "\n" +
        "                    <div class=\"layui-form\" >\n" +
        "                        <table class=\"layui-table\" id=\"booktable\"></table>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    </div>\n"

    showReturnList()
}

function clickPublishInform() {
    document.getElementById("demo2").innerHTML = "<form class=\"layui-form layui-form-pane\" action=\"\">\n" +
        "    <div class=\"layui-form-item\">\n" +
        "      <label class=\"layui-form-label\">标题</label>\n" +
        "      <div class=\"layui-input-block\">\n" +
        "        <input type=\"text\" name=\"title\" autocomplete=\"off\" placeholder=\"请输入标题\" class=\"layui-input\" style=\"width: 500px\" id='title'>\n" +
        "      </div>\n" +
        "    </div>\n" +
        "\n" +
        "    <div class=\"layui-form-item layui-form-text\" style=\"width: 900px\">\n" +
        "      <label class=\"layui-form-label\">文本域</label>\n" +
        "      <div class=\"layui-input-block\">\n" +
        "        <textarea placeholder=\"请输入内容\" class=\"layui-textarea\" style=\"height: 300px\" id='content'></textarea>\n" +
        "      </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "      <button type=\"button\" class=\"layui-btn\" onclick='clickPublishBtn()'>发布</button>" +
        "    </div>\n" +
        "  </form>";
}

function clickAlertInform() {
    document.getElementById("demo2").innerHTML =
        "            <form class=\"layui-form layui-form-pane\" action=\"\">\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <div class=\"layui-inline\">\n" +
        "                        <label class=\"layui-form-label\">新密码</label>\n" +
        "                        <div class=\"layui-input-inline\">\n" +
        "                            <input id=\"password1\" type=\"password\" name=\"email\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <div class=\"layui-inline\">\n" +
        "                        <label class=\"layui-form-label\">确认密码</label>\n" +
        "                        <div class=\"layui-input-inline\">\n" +
        "                            <input id=\"password2\" type=\"password\" name=\"email\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <label class=\"layui-form-label\">姓名</label>\n" +
        "                    <div class=\"layui-input-inline\">\n" +
        "                        <input id=\"truename\" type=\"text\" name=\"username\" lay-verify=\"required\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <div class=\"layui-inline\">\n" +
        "                        <label class=\"layui-form-label\">手机号</label>\n" +
        "                        <div class=\"layui-input-inline\">\n" +
        "                            <input id=\"phonenumber\" type=\"tel\" name=\"phone\" lay-verify=\"required|phone\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "\n" +
        "                <div class=\"layui-form-item\">\n" +
        "                    <div class=\"layui-inline\">\n" +
        "                        <label class=\"layui-form-label\">邮箱</label>\n" +
        "                        <div class=\"layui-input-inline\">\n" +
        "                            <input id=\"email\" type=\"text\" name=\"email\" lay-verify=\"email\" autocomplete=\"off\" class=\"layui-input\">\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "\n" +
        "                <div class=\"layui-form-item\" style=\"position: relative; height: 19.2px\">\n" +
        "                    <div class=\"layui-inline\">\n" +
        "                       <span id=\"inform\" class=\"inform\"></span>\n" +
        "                    </div>\n" +
        "                    <div class=\"layui-inline\" style='position: absolute;left: 205px'>\n" +
        "                         <button type='button' class=\"layui-btn\" onclick='clickAffirmBtn()' lay-filter=\"demo2\">确认修改</button>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </form>\n"
}

function clickCharge() {
    document.getElementById("demo2").innerHTML = "<form class=\"layui-form\" action=\"\">\n" +
        "    <br>\n" +
        "\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <div class=\"layui-inline\">\n" +
        "            ￥\n" +
        "        </div>\n" +
        "        <div class=\"layui-inline\">\n" +
        "                <input name=\"money\" autocomplete=\"off\" placeholder=\"充值金额\" class=\"layui-input\" id=\"money\">\n" +
        "        </div>\n" +
        "        <div class=\"layui-inline\">\n" +
        "                <button type=\"button\" class=\"layui-btn\" onclick='clickChargeBtn()'>充值</button>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "        <div class=\"layui-inline\">\n" +
        "                <span id=\"inform\" class=\"inform\"></span>\n" +
        "        </div>\n" +
        "</form>\n"
}

// ---------------------------------------------------------------查看记录----------------------------------------------------------------
function clickCheckBorrowBookRecord() {
    document.getElementById("demo2").innerHTML =
        "                    <div class=\"layui-form\">\n" +
        "                        <table class=\"layui-table\" id=\"booktable\"></table>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    <div class=\"layui-form\" id='addbook'>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    </div>\n"

    showBorrowRecord();
}

function clickCheckReturnBookRecord() {
    document.getElementById("demo2").innerHTML =
        "                    <div class=\"layui-form\">\n" +
        "                        <table class=\"layui-table\" id=\"booktable\"></table>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    <div class=\"layui-form\" id='addbook'>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    </div>\n"

    showReturnRecord();
}


//----------------------------------------------------------------------------------------------------------------------------------
function returnBtn() {
    var seekBookname = $("#seekBookname").val();
    $("#seekBookname").val(seekBookname)
    showlist()
}

function returnDeleteBtn() {
    var seekBookname = $("#seekBookname").val();
    $("#seekBookname").val(seekBookname)
    showDeleteList()
}

// --------------------------------------------------------------------------------------------------------------------------------------------
function clickChargeBtn() {
    var money = $("#money").val();
    var inform = $("#inform");

    var regcharge = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;

    if (money == "")
    {
        inform[0].innerHTML = "金额不能为空";
        inform[0].style.display = "block";
        phonenumber.focus();
        return false;
    }else if (!regcharge.test(money)){
        inform[0].innerHTML = "请输入一个正数";
        inform[0].style.display = "block";
        phonenumber.focus();
        return false;
    }else {
        inform[0].style.display = "none";
    }

    $.post("ChargeServlet", {
        username: username,
        money: money
    }, function (data) {
        if (data == 1){
            alert("充值成功！")
            clickCharge();
        }else {
            alert("重置失败！")
        }
    });
}

function clickAffirmBtn() {
    var password1 = $("#password1");
    var password2 = $("#password2");
    var truename = $("#truename");
    var phonenumber = $("#phonenumber");
    var email = $("#email");
    var inform = $("#inform");

    var regpassword = /^[0-9a-zA-Z.+*_]{6,15}$/;
    var regtruename = /^([\u0391-\uFFE5]{2,4}|[a-zA-Z\\.\s]{3,50})$/;
    var regphonenumber = /^1[3456789]\d{9}$/;
    var regemail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/;

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

    $.post("AlertInformServlet", {
        username: username,
        truename: truename,
        password: password1,
        phonenumber: phonenumber,
        email: email,
    }, function (data) {
        if (data == 1){
            alert("修改信息成功！")
            clickUserInfo();
        }else {
            alert("修改信息失败！")
        }
    });
}

function clickPublishBtn() {
    $.post("PublishInformServlet", {
        title: $("#title").val(),
        content: $("#content").val()
    }, function (data) {
        if (data == 1){
            alert("成功发布通知！")
            clickInform()
        }else {
            alert("发布通知失败！")
        }
    });
}

function clickBorrowBtn(book) {
    $.post("BorrowBookServlet", {
        bookno: book.bookno,
        username: book.username,
        bookname: book.bookname
    }, function (data) {
        if (data == 1){
            alert("借阅成功！")
            returnBtn()
        }else {
            alert("余额不足，请充值！")
        }
    });
}

function clickReturnBtn(borrowbook) {
    $.post("FindReturnBookServlet", {
        bookno: borrowbook.bookno,
        bookname: borrowbook.bookname,
        username: borrowbook.username
    }, function (data) {
        if (data == 1){
            alert("还书成功！")
            showReturnList()
        }else {
            alert("还书失败！")
        }
    });
}

function clickAddNewBook() {
    document.getElementById("demo2").innerHTML =
        "                    <div class=\"layui-form\">\n" +
        "                        <table class=\"layui-table\" id=\"booktable\"></table>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    <div class=\"layui-form\" id='addbook'>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    </div>\n"

    var t =
        "                                <thead>\n" +
        "                                <tr>\n" +
        "                                    <th>书名</th>\n" +
        "                                    <th>作者</th>\n" +
        "                                    <th>书籍类型</th>\n" +
        "                                    <th>出版社</th>\n" +
        "                                    <th>数量</th>\n" +
        "                                    <th>价格</th>\n" +
        "                                </tr>\n" +
        "                                </thead>\n";

    t += "<tr>"
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入书名\" class=\"layui-input\" id='bookname1'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入作者\" class=\"layui-input\" id='auther1'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入书籍类型\" class=\"layui-input\" id='booktype1'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入出版社\" class=\"layui-input\" id='publisher1'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入数量\" class=\"layui-input\" id='booknum1'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入价格\" class=\"layui-input\" id='price1'></td></tr>";

    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\" id='addBtn1' onclick='showAddNewList(2)'><i class=\"layui-icon\"></i></button>"

    document.getElementById("booktable").innerHTML += t;

    $("#addBtn1").val(1)

    t = "<div class=\"layui-btn-container\">\n" +
        "  <button type=\"button\" class=\"layui-btn\" style=\"display:block;margin:0 auto; width: 100px\" onclick='clickSubmitBtn()'>提交</button>\n" +
        "</div>"

    document.getElementById("addbook").innerHTML += t;
}

function clickDeleteBook() {
    document.getElementById("demo2").innerHTML =
        "                    <form class=\"layui-form layui-form-pane\" action=\"\" style=\"margin-top: 10px;\">\n" +
        "                        <div class=\"layui-form-item\">\n" +
        "                            <div class=\"layui-inline\" id='firstline'>" +
        "                            </div>\n" +
        "                            <div class=\"layui-inline\" style='float: right'>" +
        "                            <label class=\"layui-form-label\">查询书籍</label>\n" +
        "                            <div class=\"layui-input-block\">\n" +
        "                                <input type=\"text\" name=\"bookname\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入书名\" class=\"layui-input\" style=\"width:200px;float:left\" id=\"seekBookname\">\n" +
        "                                <input type=\"button\" class=\"layui-btn\" lay-submit=\"\" lay-filter=\"demo1\" onclick=\"showDeleteList();\" value=\"查询\"></input>\n" +
        "                            </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </form>\n" +
        "\n" +
        "                    <div class=\"layui-form\" >\n" +
        "                        <table class=\"layui-table\" id=\"booktable\"></table>\n" +
        "                        <div class=\"pagenum\" id=\"pagenum\"></div>\n" +
        "                    </div>\n"

        showDeleteList()
}

// --------------------------------------------------------书籍编号----------------------------------------------------------------------
function clickSubmitBtn() {
    var str = ""
    var num = $("#addBtn1").val();
    var booklist = []
    $.ajaxSettings.async = false;
    for (i=1; i<=num; i++){
        $.post("AddNewBookServlet", {
            bookname: $("#bookname"+i).val(),
            auther: $("#auther"+i).val(),
            booktype: $("#booktype"+i).val(),
            publisher: $("#publisher"+i).val(),
            booknum: $("#booknum"+i).val(),
            price: $("#price"+i).val(),
        }, function (data){
            if (data != null) {
                str += data;
            } else {
            }
        });
    }
    if (str == ""){
        alert("全部添加成功！")
    }else {
        alert(str)
    }

    clickAddNewBook();
}

function clickBtn(book) {

    $("#firstline").html("  <button type=\"button\" class=\"layui-btn\"><i class=\"layui-icon\" onclick='returnBtn()'>返回</i></button>");
    $("#booktable").html("");

    $.getJSON("FindBookNoServlet?seekBookname=" + book.bookname, function(data) {
        var len = 0
        var nowpage = 1
        $.each(data, function(i) {
            len++
        });
        const currentPage = 1
        // 页面尺寸
        const pageSize = 10
        // 页面数量
        const pageNum = Math.ceil(len / pageSize)
        var leftpage = 5
        var beginpage = 1
        var rightpage = pageNum
        var endpage = pageNum
        // 截取到相应的页码数据
        let pageList = []

        // 刚开始显示的是第一页，所以先截取第一页的数据
        pageList = data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        List(pageList)

        var t = ""
        function List(pageData) {
            document.getElementById("booktable").innerHTML = "";

            t =
                "                                <thead>\n" +
                "                                <tr>\n" +
                "                                    <th>编号</th>\n" +
                "                                    <th>书名</th>\n" +
                "                                    <th>作者</th>\n" +
                "                                    <th>书籍类型</th>\n" +
                "                                    <th>出版社</th>\n" +
                "                                    <th>价格</th>\n" +
                "                                    <th align='center'>操作</th>\n" +
                "                                </tr>\n" +
                "                                </thead>\n";

            document.getElementById("booktable").innerHTML += t;

            $.each(pageData, function(i) {
                var borrowBook = {
                    bookno: pageData[i].bookno,
                    bookname: book.bookname,
                    username: username
                }

                t = "<tr>"
                t += "<td>" + pageData[i].bookno + "</td>";
                t += "<td>" + book.bookname + "</td>";
                t += "<td>" + book.auther + "</td>";
                t += "<td>" + book.booktype + "</td>";
                t += "<td>" + book.publisher + "</td>";
                t += "<td>" + book.price + "</td>";
                t += "<td align='center'><button type=\"button\" class=\"layui-btn layui-btn-primary\" id='btn" + (i+1) + "' " +
                    "onclick='clickBorrowBtn("+ JSON.stringify(borrowBook).replace(/"/g, '&quot;') +")'>借阅</button></td>";
                t += "</tr>";
                document.getElementById("booktable").innerHTML += t;
            });

            document.getElementById("pagenum").innerHTML = "";

            t = "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-radius\">第" + nowpage + "页</button>"
            t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>";
            if (pageNum <= 10) {
                for (i = 0; i < pageNum; i++) {
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + (i + 1) + "</button>"
                }
            }else {

                if (nowpage == leftpage && leftpage != beginpage && nowpage != (beginpage+2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = leftpage-2; i <= leftpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage -= 2
                    rightpage = leftpage + 4
                }else if (nowpage == rightpage && rightpage != endpage && nowpage != (endpage-2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = rightpage-2; i <= rightpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    rightpage += 2
                    leftpage = rightpage-4
                }else if (nowpage == beginpage){
                    for (i = beginpage; i <= beginpage+4; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = beginpage + 4
                    rightpage = pageNum
                }else if (nowpage == endpage){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    leftpage = beginpage
                    rightpage = pageNum - 4
                }else if (nowpage < beginpage+4){
                    for (i = 1; i <= 5; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                }else if (nowpage > endpage-4){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                }else if (nowpage >= (beginpage+4) && nowpage <= (endpage-4)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = nowpage-2; i <= nowpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = nowpage - 2
                    rightpage = nowpage + 2
                }
            }
            t += "  <button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>"


            document.getElementById("pagenum").innerHTML += t;
        }



        // 给按钮注册点击事件
        const btn = document.getElementsByClassName("pagenum")

        btn[0].onclick = function (e) {
            let newpageList = []
            newCurrentPage = Number(e.target.innerText)
            if (!isNaN(newCurrentPage)) {
                nowpage = newCurrentPage
                newpageList = data.slice((newCurrentPage - 1) * pageSize, newCurrentPage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage > 1){
                nowpage = nowpage - 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage < pageNum){
                nowpage = nowpage + 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            }
        }
    });
}

function clickDeleteBtn(deleteBook) {
    $.post("DeleteBookServlet", {
        bookno: deleteBook.bookno,
        bookname: deleteBook.bookname
    }, function (data) {
        if (data == 1){
            alert("删除成功！")
            returnDeleteBtn()
        }else {
            alert("删除失败！")
        }
    });
}

function clickAllDeleteBtn(deleteBook) {
    $.post("DeleteAllBookServlet", {
        bookno: deleteBook.bookno,
        bookname: deleteBook.bookname
    }, function (data) {
        if (data == 1){
            alert("下架成功！")
            returnDeleteBtn()
        }else {
            alert("此类书籍正在出借，下架失败！")
        }
    });
}

function clickManagerBtn(book) {
    $("#firstline").html("  <button type=\"button\" class=\"layui-btn\"><i class=\"layui-icon\" onclick='returnDeleteBtn()'>返回</i></button>");
    $("#booktable").html("");

    $.getJSON("FindBookNoServlet?seekBookname=" + book.bookname, function(data) {
        var len = 0
        var nowpage = 1
        $.each(data, function(i) {
            len++
        });
        const currentPage = 1
        // 页面尺寸
        const pageSize = 10
        // 页面数量
        const pageNum = Math.ceil(len / pageSize)
        var leftpage = 5
        var beginpage = 1
        var rightpage = pageNum
        var endpage = pageNum
        // 截取到相应的页码数据
        let pageList = []

        // 刚开始显示的是第一页，所以先截取第一页的数据
        pageList = data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        List(pageList)

        var t = ""
        function List(pageData) {
            document.getElementById("booktable").innerHTML = "";

            t =
                "                                <thead>\n" +
                "                                <tr>\n" +
                "                                    <th>编号</th>\n" +
                "                                    <th>书名</th>\n" +
                "                                    <th>作者</th>\n" +
                "                                    <th>书籍类型</th>\n" +
                "                                    <th>出版社</th>\n" +
                "                                    <th>价格</th>\n" +
                "                                    <th align='center'>操作</th>\n" +
                "                                </tr>\n" +
                "                                </thead>\n";

            document.getElementById("booktable").innerHTML += t;

            $.each(pageData, function(i) {
                var deleteBook = {
                    bookno: pageData[i].bookno,
                    bookname: book.bookname,
                    username: username
                }

                t = "<tr>"
                t += "<td>" + pageData[i].bookno + "</td>";
                t += "<td>" + book.bookname + "</td>";
                t += "<td>" + book.auther + "</td>";
                t += "<td>" + book.booktype + "</td>";
                t += "<td>" + book.publisher + "</td>";
                t += "<td>" + book.price + "</td>";
                t += "<td align='center'><button type=\"button\" class=\"layui-btn layui-btn-primary\" id='btn" + (i+1) + "' " +
                    "onclick='clickDeleteBtn("+ JSON.stringify(deleteBook).replace(/"/g, '&quot;') +")'>删除</button></td>";
                t += "</tr>";
                document.getElementById("booktable").innerHTML += t;
            });

            document.getElementById("pagenum").innerHTML = "";

            t = "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-radius\">第" + nowpage + "页</button>"
            t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>";
            if (pageNum <= 10) {
                for (i = 0; i < pageNum; i++) {
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + (i + 1) + "</button>"
                }
            }else {

                if (nowpage == leftpage && leftpage != beginpage && nowpage != (beginpage+2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = leftpage-2; i <= leftpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage -= 2
                    rightpage = leftpage + 4
                }else if (nowpage == rightpage && rightpage != endpage && nowpage != (endpage-2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = rightpage-2; i <= rightpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    rightpage += 2
                    leftpage = rightpage-4
                }else if (nowpage == beginpage){
                    for (i = beginpage; i <= beginpage+4; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = beginpage + 4
                    rightpage = pageNum
                }else if (nowpage == endpage){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    leftpage = beginpage
                    rightpage = pageNum - 4
                }else if (nowpage < beginpage+4){
                    for (i = 1; i <= 5; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                }else if (nowpage > endpage-4){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                }else if (nowpage >= (beginpage+4) && nowpage <= (endpage-4)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = nowpage-2; i <= nowpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = nowpage - 2
                    rightpage = nowpage + 2
                }
            }
            t += "  <button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>"


            document.getElementById("pagenum").innerHTML += t;
        }



        // 给按钮注册点击事件
        const btn = document.getElementsByClassName("pagenum")

        btn[0].onclick = function (e) {
            let newpageList = []
            newCurrentPage = Number(e.target.innerText)
            if (!isNaN(newCurrentPage)) {
                nowpage = newCurrentPage
                newpageList = data.slice((newCurrentPage - 1) * pageSize, newCurrentPage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage > 1){
                nowpage = nowpage - 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage < pageNum){
                nowpage = nowpage + 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            }
        }
    });
}

// -------------------------------------------------------------书籍列表---------------------------------------------------------------
function showBorrowRecord() {
    $("#booktable").html("");

    $.getJSON("DisplayBorrowServlet", function(data) {
        var len = 0
        var nowpage = 1
        $.each(data, function(i) {
            len++
        });
        const currentPage = 1
        // 页面尺寸
        const pageSize = 10
        // 页面数量
        const pageNum = Math.ceil(len / pageSize)
        var leftpage = 5
        var beginpage = 1
        var rightpage = pageNum
        var endpage = pageNum
        // 截取到相应的页码数据
        let pageList = []

        // 刚开始显示的是第一页，所以先截取第一页的数据
        pageList = data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        List(pageList)

        var t = ""
        function List(pageData) {
            document.getElementById("booktable").innerHTML = "";

            t =
                "                                <thead>\n" +
                "                                <tr>\n" +
                "                                    <th>编号</th>\n" +
                "                                    <th>借书用户</th>\n" +
                "                                    <th>书名</th>\n" +
                "                                    <th>借书时间</th>\n" +
                "                                </tr>\n" +
                "                                </thead>\n";

            document.getElementById("booktable").innerHTML += t;

            $.each(pageData, function(i) {

                t = "<tr>"
                t += "<td>" + pageData[i].bookno + "</td>";
                t += "<td>" + pageData[i].username + "</td>";
                t += "<td>" + pageData[i].bookname + "</td>";
                t += "<td>" + pageData[i].borrowtime + "</td>";
                t += "</tr>";
                document.getElementById("booktable").innerHTML += t;
            });

            document.getElementById("pagenum").innerHTML = "";

            t = "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-radius\">第" + nowpage + "页</button>"
            t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>";
            if (pageNum <= 10) {
                for (i = 0; i < pageNum; i++) {
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + (i + 1) + "</button>"
                }
            }else {

                if (nowpage == leftpage && leftpage != beginpage && nowpage != (beginpage+2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = leftpage-2; i <= leftpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage -= 2
                    rightpage = leftpage + 4
                }else if (nowpage == rightpage && rightpage != endpage && nowpage != (endpage-2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = rightpage-2; i <= rightpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    rightpage += 2
                    leftpage = rightpage-4
                }else if (nowpage == beginpage){
                    for (i = beginpage; i <= beginpage+4; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = beginpage + 4
                    rightpage = pageNum
                }else if (nowpage == endpage){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    leftpage = beginpage
                    rightpage = pageNum - 4
                }else if (nowpage < beginpage+4){
                    for (i = 1; i <= 5; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                }else if (nowpage > endpage-4){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                }else if (nowpage >= (beginpage+4) && nowpage <= (endpage-4)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = nowpage-2; i <= nowpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = nowpage - 2
                    rightpage = nowpage + 2
                }
            }
            t += "  <button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>"


            document.getElementById("pagenum").innerHTML += t;
        }



        // 给按钮注册点击事件
        const btn = document.getElementsByClassName("pagenum")

        btn[0].onclick = function (e) {
            let newpageList = []
            newCurrentPage = Number(e.target.innerText)
            if (!isNaN(newCurrentPage)) {
                nowpage = newCurrentPage
                newpageList = data.slice((newCurrentPage - 1) * pageSize, newCurrentPage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage > 1){
                nowpage = nowpage - 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage < pageNum){
                nowpage = nowpage + 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            }
        }

    });
}

function showReturnRecord() {
    $("#booktable").html("");

    $.getJSON("DisplayReturnServlet", function(data) {
        var len = 0
        var nowpage = 1
        $.each(data, function(i) {
            len++
        });
        const currentPage = 1
        // 页面尺寸
        const pageSize = 10
        // 页面数量
        const pageNum = Math.ceil(len / pageSize)
        var leftpage = 5
        var beginpage = 1
        var rightpage = pageNum
        var endpage = pageNum
        // 截取到相应的页码数据
        let pageList = []

        // 刚开始显示的是第一页，所以先截取第一页的数据
        pageList = data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        List(pageList)

        var t = ""
        function List(pageData) {
            document.getElementById("booktable").innerHTML = "";

            t =
                "                                <thead>\n" +
                "                                <tr>\n" +
                "                                    <th>编号</th>\n" +
                "                                    <th>借书用户</th>\n" +
                "                                    <th>书名</th>\n" +
                "                                    <th>借书时间</th>\n" +
                "                                </tr>\n" +
                "                                </thead>\n";

            document.getElementById("booktable").innerHTML += t;

            $.each(pageData, function(i) {

                t = "<tr>"
                t += "<td>" + pageData[i].bookno + "</td>";
                t += "<td>" + pageData[i].username + "</td>";
                t += "<td>" + pageData[i].bookname + "</td>";
                t += "<td>" + pageData[i].returntime + "</td>";
                t += "</tr>";
                document.getElementById("booktable").innerHTML += t;
            });

            document.getElementById("pagenum").innerHTML = "";

            t = "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-radius\">第" + nowpage + "页</button>"
            t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>";
            if (pageNum <= 10) {
                for (i = 0; i < pageNum; i++) {
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + (i + 1) + "</button>"
                }
            }else {

                if (nowpage == leftpage && leftpage != beginpage && nowpage != (beginpage+2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = leftpage-2; i <= leftpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage -= 2
                    rightpage = leftpage + 4
                }else if (nowpage == rightpage && rightpage != endpage && nowpage != (endpage-2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = rightpage-2; i <= rightpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    rightpage += 2
                    leftpage = rightpage-4
                }else if (nowpage == beginpage){
                    for (i = beginpage; i <= beginpage+4; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = beginpage + 4
                    rightpage = pageNum
                }else if (nowpage == endpage){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    leftpage = beginpage
                    rightpage = pageNum - 4
                }else if (nowpage < beginpage+4){
                    for (i = 1; i <= 5; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                }else if (nowpage > endpage-4){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                }else if (nowpage >= (beginpage+4) && nowpage <= (endpage-4)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = nowpage-2; i <= nowpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = nowpage - 2
                    rightpage = nowpage + 2
                }
            }
            t += "  <button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>"


            document.getElementById("pagenum").innerHTML += t;
        }



        // 给按钮注册点击事件
        const btn = document.getElementsByClassName("pagenum")

        btn[0].onclick = function (e) {
            let newpageList = []
            newCurrentPage = Number(e.target.innerText)
            if (!isNaN(newCurrentPage)) {
                nowpage = newCurrentPage
                newpageList = data.slice((newCurrentPage - 1) * pageSize, newCurrentPage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage > 1){
                nowpage = nowpage - 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage < pageNum){
                nowpage = nowpage + 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            }
        }

    });
}

function showAddNewList(i) {
    $("#addBtn"+(i-1)+"").attr("style","display:none;");

    var t = "<tr>"
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入书名\" class=\"layui-input\" id='bookname"+i+"'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入作者\" class=\"layui-input\" id='auther"+i+"'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入书籍类型\" class=\"layui-input\" id='booktype"+i+"'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入出版社\" class=\"layui-input\" id='publisher"+i+"'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入数量\" class=\"layui-input\" id='booknum"+i+"'></td>";
    t += "<td><input type=\"text\" name=\"title\" lay-verify=\"title\" autocomplete=\"off\" placeholder=\"请输入价格\" class=\"layui-input\" id='price"+i+"'></td></tr>";

    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\" id='addBtn"+i+"' onclick='showAddNewList("+(i+1)+")'><i class=\"layui-icon\"></i></button>"

    document.getElementById("booktable").innerHTML += t;

    $("#addBtn1").val(i)
}

function showReturnList() {
    var seekBookname = $("#seekBookname").val();
    $("#booktable").html("");

    $.getJSON("FindReturnBookServlet?username=" + username + "&seekBookname=" + seekBookname, function(data) {
        var len = 0
        var nowpage = 1
        $.each(data, function(i) {
            len++
        });
        const currentPage = 1
        // 页面尺寸
        const pageSize = 10
        // 页面数量
        const pageNum = Math.ceil(len / pageSize)
        var leftpage = 5
        var beginpage = 1
        var rightpage = pageNum
        var endpage = pageNum
        // 截取到相应的页码数据
        let pageList = []

        // 刚开始显示的是第一页，所以先截取第一页的数据
        pageList = data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        List(pageList)

        var t = ""
        function List(pageData) {
            document.getElementById("booktable").innerHTML = "";

            t =
                "                                <thead>\n" +
                "                                <tr>\n" +
                "                                    <th>编号</th>\n" +
                "                                    <th>书名</th>\n" +
                "                                    <th>作者</th>\n" +
                "                                    <th>书籍类型</th>\n" +
                "                                    <th>出版社</th>\n" +
                "                                    <th>价格</th>\n" +
                "                                    <th>借书时间</th>\n" +
                "                                    <th align='center'>操作</th>\n" +
                "                                </tr>\n" +
                "                                </thead>\n";

            document.getElementById("booktable").innerHTML += t;

            $.each(pageData, function(i) {
                var borrowbook = {
                    bookno: pageData[i].bookno,
                    bookname: pageData[i].bookname,
                    username: username
                }


                t = "<tr>"
                t += "<td>" + pageData[i].bookno + "</td>";
                t += "<td>" + pageData[i].bookname + "</td>";
                t += "<td>" + pageData[i].auther + "</td>";
                t += "<td>" + pageData[i].booktype + "</td>";
                t += "<td>" + pageData[i].publisher + "</td>";
                t += "<td>" + pageData[i].price + "</td>";
                t += "<td>" + pageData[i].borrowtime + "</td>";
                t += "<td align='center'><button type=\"button\" class=\"layui-btn layui-btn-primary\" id='btn" + (i+1) + "' " +
                    "onclick='clickReturnBtn("+ JSON.stringify(borrowbook).replace(/"/g, '&quot;') +")'>还书</button></td>";
                t += "</tr>";

                document.getElementById("booktable").innerHTML += t;
            });

            document.getElementById("pagenum").innerHTML = "";

            t = "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-radius\">第" + nowpage + "页</button>"
            t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>";
            if (pageNum <= 10) {
                for (i = 0; i < pageNum; i++) {
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + (i + 1) + "</button>"
                }
            }else {

                if (nowpage == leftpage && leftpage != beginpage && nowpage != (beginpage+2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = leftpage-2; i <= leftpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage -= 2
                    rightpage = leftpage + 4
                }else if (nowpage == rightpage && rightpage != endpage && nowpage != (endpage-2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = rightpage-2; i <= rightpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    rightpage += 2
                    leftpage = rightpage-4
                }else if (nowpage == beginpage){
                    for (i = beginpage; i <= beginpage+4; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = beginpage + 4
                    rightpage = pageNum
                }else if (nowpage == endpage){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    leftpage = beginpage
                    rightpage = pageNum - 4
                }else if (nowpage < beginpage+4){
                    for (i = 1; i <= 5; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                }else if (nowpage > endpage-4){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                }else if (nowpage >= (beginpage+4) && nowpage <= (endpage-4)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = nowpage-2; i <= nowpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = nowpage - 2
                    rightpage = nowpage + 2
                }
            }
            t += "  <button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>"


            document.getElementById("pagenum").innerHTML += t;
        }



        // 给按钮注册点击事件
        const btn = document.getElementsByClassName("pagenum")

        btn[0].onclick = function (e) {
            let newpageList = []
            newCurrentPage = Number(e.target.innerText)
            if (!isNaN(newCurrentPage)) {
                nowpage = newCurrentPage
                newpageList = data.slice((newCurrentPage - 1) * pageSize, newCurrentPage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage > 1){
                nowpage = nowpage - 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage < pageNum){
                nowpage = nowpage + 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            }
        }

    });
}

function showDeleteList() {
    var seekBookname = $("#seekBookname").val();
    $("#booktable").html("");


    $.getJSON("FindBookServlet?seekBookname=" + seekBookname, function(data) {
        var len = 0
        var nowpage = 1
        $.each(data, function(i) {
            len++
        });
        const currentPage = 1
        // 页面尺寸
        const pageSize = 10
        // 页面数量
        const pageNum = Math.ceil(len / pageSize)
        var leftpage = 5
        var beginpage = 1
        var rightpage = pageNum
        var endpage = pageNum
        // 截取到相应的页码数据
        let pageList = []

        // 刚开始显示的是第一页，所以先截取第一页的数据
        pageList = data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        List(pageList)

        var t = ""
        function List(pageData) {
            document.getElementById("booktable").innerHTML = "";

            t =
                "                                <thead>\n" +
                "                                <tr>\n" +
                "                                    <th>书名</th>\n" +
                "                                    <th>作者</th>\n" +
                "                                    <th>书籍类型</th>\n" +
                "                                    <th>出版社</th>\n" +
                "                                    <th>数量</th>\n" +
                "                                    <th>价格</th>\n" +
                "                                    <th align='center'>操作</th>\n" +
                "                                </tr>\n" +
                "                                </thead>\n";

            document.getElementById("booktable").innerHTML += t;

            $.each(pageData, function(i) {
                var book = {
                    bookname: pageData[i].bookname,
                    auther: pageData[i].auther,
                    booktype: pageData[i].booktype,
                    publisher: pageData[i].publisher,
                    booknum: pageData[i].booknum,
                    price: pageData[i].price
                };

                t = "<tr>"
                t += "<td>" + pageData[i].bookname + "</td>";
                t += "<td>" + pageData[i].auther + "</td>";
                t += "<td>" + pageData[i].booktype + "</td>";
                t += "<td>" + pageData[i].publisher + "</td>";
                t += "<td>" + pageData[i].booknum + "</td>";
                t += "<td>" + pageData[i].price + "</td>";
                t += "<td align='center'><button type=\"button\" class=\"layui-btn layui-btn-primary\" " +
                    "onclick='clickManagerBtn("+ JSON.stringify(book).replace(/"/g, '&quot;') +")'>查看</button>" +
                    "<button type=\"button\" class=\"layui-btn layui-btn-primary\" " +
                    "onclick='clickAllDeleteBtn("+ JSON.stringify(book).replace(/"/g, '&quot;') +")'>整体下架</button>"
                    "</td>";
                t += "</tr>";
                document.getElementById("booktable").innerHTML += t;
            });

            document.getElementById("pagenum").innerHTML = "";

            t = "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-radius\">第" + nowpage + "页</button>"
            t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>";
            if (pageNum <= 10) {
                for (i = 0; i < pageNum; i++) {
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + (i + 1) + "</button>"
                }
            }else {

                if (nowpage == leftpage && leftpage != beginpage && nowpage != (beginpage+2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = leftpage-2; i <= leftpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage -= 2
                    rightpage = leftpage + 4
                }else if (nowpage == rightpage && rightpage != endpage && nowpage != (endpage-2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = rightpage-2; i <= rightpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    rightpage += 2
                    leftpage = rightpage-4
                }else if (nowpage == beginpage){
                    for (i = beginpage; i <= beginpage+4; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = beginpage + 4
                    rightpage = pageNum
                }else if (nowpage == endpage){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    leftpage = beginpage
                    rightpage = pageNum - 4
                }else if (nowpage < beginpage+4){
                    for (i = 1; i <= 5; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                }else if (nowpage > endpage-4){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                }else if (nowpage >= (beginpage+4) && nowpage <= (endpage-4)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = nowpage-2; i <= nowpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = nowpage - 2
                    rightpage = nowpage + 2
                }
            }
            t += "  <button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>"


            document.getElementById("pagenum").innerHTML += t;
        }



        // 给按钮注册点击事件
        const btn = document.getElementsByClassName("pagenum")

        btn[0].onclick = function (e) {
            let newpageList = []
            newCurrentPage = Number(e.target.innerText)
            if (!isNaN(newCurrentPage)) {
                nowpage = newCurrentPage
                newpageList = data.slice((newCurrentPage - 1) * pageSize, newCurrentPage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage > 1){
                nowpage = nowpage - 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage < pageNum){
                nowpage = nowpage + 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            }
        }

    });
}

function showlist() {
    var seekBookname = $("#seekBookname").val();
    $("#booktable").html("");


    $.getJSON("FindBookServlet?seekBookname=" + seekBookname, function(data) {
        var len = 0
        var nowpage = 1
        $.each(data, function(i) {
            len++
        });
        const currentPage = 1
        // 页面尺寸
        const pageSize = 10
        // 页面数量
        const pageNum = Math.ceil(len / pageSize)
        var leftpage = 5
        var beginpage = 1
        var rightpage = pageNum
        var endpage = pageNum
        // 截取到相应的页码数据
        let pageList = []

        // 刚开始显示的是第一页，所以先截取第一页的数据
        pageList = data.slice((currentPage - 1) * pageSize, currentPage * pageSize)
        List(pageList)

        var t = ""
        function List(pageData) {
            document.getElementById("booktable").innerHTML = "";

            t =
                "                                <thead>\n" +
                "                                <tr>\n" +
                "                                    <th>书名</th>\n" +
                "                                    <th>作者</th>\n" +
                "                                    <th>书籍类型</th>\n" +
                "                                    <th>出版社</th>\n" +
                "                                    <th>数量</th>\n" +
                "                                    <th>价格</th>\n" +
                "                                    <th align='center'>操作</th>\n" +
                "                                </tr>\n" +
                "                                </thead>\n";

            document.getElementById("booktable").innerHTML += t;

            $.each(pageData, function(i) {
                var book = {
                    bookname: pageData[i].bookname,
                    auther: pageData[i].auther,
                    booktype: pageData[i].booktype,
                    publisher: pageData[i].publisher,
                    booknum: pageData[i].booknum,
                    price: pageData[i].price
                };

                t = "<tr>"
                t += "<td>" + pageData[i].bookname + "</td>";
                t += "<td>" + pageData[i].auther + "</td>";
                t += "<td>" + pageData[i].booktype + "</td>";
                t += "<td>" + pageData[i].publisher + "</td>";
                t += "<td>" + pageData[i].booknum + "</td>";
                t += "<td>" + pageData[i].price + "</td>";
                t += "<td align='center'><button type=\"button\" class=\"layui-btn layui-btn-primary\" id='btn" + (i+1) + "' " +
                    "onclick='clickBtn("+ JSON.stringify(book).replace(/"/g, '&quot;') +")'>查看</button></td>";
                t += "</tr>";
                document.getElementById("booktable").innerHTML += t;
            });

            document.getElementById("pagenum").innerHTML = "";

            t = "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-radius\">第" + nowpage + "页</button>"
            t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>";
            if (pageNum <= 10) {
                for (i = 0; i < pageNum; i++) {
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + (i + 1) + "</button>"
                }
            }else {

                if (nowpage == leftpage && leftpage != beginpage && nowpage != (beginpage+2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = leftpage-2; i <= leftpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage -= 2
                    rightpage = leftpage + 4
                }else if (nowpage == rightpage && rightpage != endpage && nowpage != (endpage-2)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = rightpage-2; i <= rightpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    rightpage += 2
                    leftpage = rightpage-4
                }else if (nowpage == beginpage){
                    for (i = beginpage; i <= beginpage+4; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = beginpage + 4
                    rightpage = pageNum
                }else if (nowpage == endpage){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    leftpage = beginpage
                    rightpage = pageNum - 4
                }else if (nowpage < beginpage+4){
                    for (i = 1; i <= 5; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                }else if (nowpage > endpage-4){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = endpage-4; i <= endpage; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                }else if (nowpage >= (beginpage+4) && nowpage <= (endpage-4)){
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + beginpage + "</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    for (i = nowpage-2; i <= nowpage+2; i++) {
                        t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + i + "</button>"
                    }
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">...</button>"
                    t += "<button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\">" + endpage + "</button>"
                    leftpage = nowpage - 2
                    rightpage = nowpage + 2
                }
            }
            t += "  <button type=\"button\" class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\"></i></button>"


            document.getElementById("pagenum").innerHTML += t;
        }



        // 给按钮注册点击事件
        const btn = document.getElementsByClassName("pagenum")

        btn[0].onclick = function (e) {
            let newpageList = []
            newCurrentPage = Number(e.target.innerText)
            if (!isNaN(newCurrentPage)) {
                nowpage = newCurrentPage
                newpageList = data.slice((newCurrentPage - 1) * pageSize, newCurrentPage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage > 1){
                nowpage = nowpage - 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            } else if (e.target.innerText.toString() == "" && nowpage < pageNum){
                nowpage = nowpage + 1
                newpageList = data.slice((nowpage - 1) * pageSize, nowpage * pageSize)
                List(newpageList)
            }
        }

    });
}


$(document).ready(function() {
    clickInform()
});