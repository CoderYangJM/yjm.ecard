function openPop() {
    var popFrame = document.getElementById("popFrame");
    var popForm =document.getElementById("popForm");
    //修改提交目标servlet并提交操作方式
    popForm.action="inner/manager/DepartmentServlet?op=add";
    popFrame.style.display="block";
    // var companyNum = document.getElementsByName("companyNum")[0];
    var num = document.getElementsByName("departmentNum")[0];
    var name = document.getElementsByName("departmentName")[0];
    var introduce = document.getElementsByName("departmentIntroduce")[0];
    //解除无法修改状态
    num.removeAttribute("disabled");
    num.style.backgroundColor="#eee";
    num.value = null;
    name.value =null;
    introduce.value=null;

}
function closePop() {
    var popFrame=document.getElementById("popFrame");
    popFrame.style.display="none";
}
function openUpdatePop(valueNum) {
    var popFrame = document.getElementById("popFrame");
    var popForm =document.getElementById("popForm");
    //修改提交目标servlet并提交操作方式
    popForm.action="inner/manager/DepartmentServlet?op=update";
    popFrame.style.display="block";
    //获取隐藏表单里的值

    var departmentNumInput = document.getElementsByName("departmentNumInput")[valueNum].value;
    var departmentNameInput = document.getElementsByName("departmentNameInput")[valueNum].value;
    var introduceInput = document.getElementsByName("introduceInput")[valueNum].value;
    //为弹出框元素赋值
    var num = document.getElementsByName("departmentNum")[0];
    var name = document.getElementsByName("departmentName")[0];
    var introduce = document.getElementsByName("departmentIntroduce")[0];
    num.style.backgroundColor="gray";
    num.value = departmentNumInput;
    //部门号无法修改
    num.disabled="disabled";
    name.value = departmentNameInput;
    introduce.value=introduceInput;
}
function openAddPop(companyNum,departmentNum) {
    var popFrame = document.getElementById("addEmployeeFrame");
    popFrame.style.display="block";
    var companyInput=document.getElementById("companyNumInput");
    var departmentInput=document.getElementById("departmentNumInput");
    //将公司号与部门号保存进入隐藏表单域
    companyInput.value=companyNum;
    departmentInput.value=departmentNum;
}
function closeAddPop() {
    var popFrame = document.getElementById("addEmployeeFrame");
    popFrame.style.display="none";
}
function removeDisable() {
    var num = document.getElementsByName("departmentNum")[0];
    num.removeAttribute("disabled");
}