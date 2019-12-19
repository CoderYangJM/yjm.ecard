function del() {
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        return true;
    }else{
        return false;
    }
}
function canNotDel(){
    alert("无此权限！")
    return false;
}