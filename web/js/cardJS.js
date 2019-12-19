function openPop() {
    var popFrame = document.getElementById("addCardFrame");
    var popForm =document.getElementById("addCardForm");
    //修改提交目标servlet并提交操作方式
    popForm.action="inner/CardServlet?op=add";
    popFrame.style.display="block";
    //获取弹出框元素
    var name=document.getElementsByName("name")[0];
    var cardNum=document.getElementsByName("cardNum")[0];
    var company=document.getElementsByName("company")[0];
    var address=document.getElementsByName("address")[0];
    var phoneNum=document.getElementsByName("phoneNum")[0];
    var title=document.getElementsByName("title")[0];

    //重置弹出框元素
    name.value=null;
    company.value=null;
    address.value=null;
    phoneNum.value=null;
    title.value=null;
    cardNum.value=null;
}
function closePop() {
    var popFrame=document.getElementById("addCardFrame");
    popFrame.style.display="none";
}
function openSharePop(cardNum) {
    var popFrame = document.getElementById("shareFrame");
    var popForm =document.getElementById("shareForm");
    //修改提交目标servlet并提交操作方式
    popForm.action="inner/CardServlet?op=share";
    popFrame.style.display="block";
    var cardInput=document.getElementsByName("cardNum")[1];
    cardInput.value=cardNum;

}
function closeSharePop() {
    var popFrame = document.getElementById("shareFrame");
    popFrame.style.display="none";
}
function openUpdatePop(cardNum,valueNum) {
    var popFrame = document.getElementById("addCardFrame");
    var popForm =document.getElementById("addCardForm");
    //修改提交目标servlet并提交操作方式
    popForm.action="inner/CardServlet?op=update";
    popFrame.style.display="block";
    var cardInput=document.getElementsByName("cardNum")[0];
    cardInput.value=cardNum;
    //获取隐藏表单域的值
    var hiddenName=document.getElementsByName("HiddenName")[valueNum].value;
    var hiddenSex=document.getElementsByName("HiddenSex")[valueNum].value;
    var hiddenCompany=document.getElementsByName("HiddenCompany")[valueNum].value;
    var hiddenAddress=document.getElementsByName("HiddenAddress")[valueNum].value;
    var hiddenPhoneNum=document.getElementsByName("HiddenPhoneNum")[valueNum].value;
    var hiddenTitle=document.getElementsByName("HiddenTitle")[valueNum].value;

    //获取弹出框元素
    var name=document.getElementsByName("name")[0];
    var woman=document.getElementById("womanRadio");
    var man=document.getElementById("manRadio");
    var company=document.getElementsByName("company")[0];
    var address=document.getElementsByName("address")[0];
    var phoneNum=document.getElementsByName("phoneNum")[0];
    var title=document.getElementsByName("title")[0];

    //为弹出框元素赋值
    name.value=hiddenName;
    if (hiddenSex=='男'){
        man.checked="checked";
    }else {
        woman.checked="checked"
    }
    company.value=hiddenCompany;
    address.value=hiddenAddress;
    phoneNum.value=hiddenPhoneNum;
    title.value=hiddenTitle;

}