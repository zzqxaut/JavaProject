function change(){
    var pic=document.getElementById("picture");
    var i=Math.random();//目的是使页面不一样
    pic.src="validate.jsp?id="+i;
}