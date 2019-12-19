function openPop() {
    var popFrame = document.getElementById("popFrame");
    var popForm =document.getElementById("popForm");
    //修改提交目标servlet并提交操作方式
    popForm.action="inner/manager/company/CompanyServlet?op=add";
    popFrame.style.display="block";
    var num = document.getElementsByName("companyNum")[0];
    var name = document.getElementsByName("companyName")[0];
    var location = document.getElementsByName("companyLocation")[0];
    var phoneNum = document.getElementsByName("companyPhoneNum")[0];
    var introduce = document.getElementsByName("companyIntroduce")[0];
    //解除无法修改状态
    num.removeAttribute("disabled");
    num.style.backgroundColor="#eee";
    num.value = null;
    name.value =null;
    location.value = null;
    phoneNum.value = null;
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
    popForm.action="inner/manager/company/CompanyServlet?op=update";
    popFrame.style.display="block";
    //获取隐藏表单里的值

    var companyNumInput = document.getElementsByName("companyNumInput")[valueNum].value;
    var companyNameInput = document.getElementsByName("companyNameInput")[valueNum].value;
    var locationInput = document.getElementsByName("locationInput")[valueNum].value;
    var phoneNumInput = document.getElementsByName("phoneNumInput")[valueNum].value;
    var introduceInput = document.getElementsByName("introduceInput")[valueNum].value;
    //为弹出框元素赋值
    var num = document.getElementsByName("companyNum")[0];
    var name = document.getElementsByName("companyName")[0];
    var location = document.getElementsByName("companyLocation")[0];
    var phoneNum = document.getElementsByName("companyPhoneNum")[0];
    var introduce = document.getElementsByName("companyIntroduce")[0];
    num.style.backgroundColor="gray";
    num.value = companyNumInput;
    //公司号无法修改
    num.disabled="disabled";
    name.value = companyNameInput;
    location.value = locationInput;
    phoneNum.value = phoneNumInput;
    introduce.value=introduceInput;
}
function openAddPop(companyNum) {
    var popFrame = document.getElementById("addEmployeeFrame");
    popFrame.style.display="block";
    var companyInput=document.getElementById("companyNumInput");
    //将公司号与部门号保存进入隐藏表单域
    companyInput.value=companyNum;
}
function closeAddPop() {
    var popFrame = document.getElementById("addEmployeeFrame");
    popFrame.style.display="none";
}
function removeNumDisable() {
    var num = document.getElementsByName("companyNum")[0];
    num.removeAttribute("disabled");
}