function openUpdatePop(valueNum) {
    var popFrame = document.getElementById("popFrame");
    var popForm =document.getElementById("popForm");
    //修改提交目标servlet并提交操作方式
    popForm.action="inner/manager/EmployeeServlet?op=update";
    popFrame.style.display="block";
    //获取隐藏表单里的值
    var idInput=document.getElementsByName("idInput")[valueNum].value;
    var titleInput = document.getElementsByName("titleInput")[valueNum].value;
    var wagesInput=document.getElementsByName("wagesInput")[valueNum].value;
    var dateInput=document.getElementsByName("dateInput")[valueNum].value;
    //为弹出框元素赋值
    var id=document.getElementsByName("id")[0];
    var title = document.getElementsByName("title")[0];
    var wages = document.getElementsByName("wages")[0];
    var date = document.getElementsByName("date")[0];
    id.style.backgroundColor="gray";
    id.value = idInput;
    //部门号无法修改
    id.disabled="disabled";
    title.value = titleInput;
    wages.value=wagesInput;
    date.value=dateInput;
}
function closePop() {
    var popFrame=document.getElementById("popFrame");
    popFrame.style.display="none";
}
function removeDisable() {
    var id = document.getElementsByName("id")[0];
    id.removeAttribute("disabled");
}