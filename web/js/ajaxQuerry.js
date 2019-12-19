$("#UserId").blur(function() {
    var UserId = $("#UserId").val();
//ajax
    $.ajax({
        url : "UserIdQueryServlet",
        data : {
            UserId : UserId
        },
        type:"GET",
        success:function(result) {
            result=JSON.parse(result)
            if (result.result==1) {
                if ($('#idExitSpan')!=null){
                    showSpan('#idExitSpan');
                }
            }else if(result.result==0){
                if ($('#IdNotExitSpan')!=null){
                    showSpan('#IdNotExitSpan')
                }
            }else if (result.result==2){
                //存在此员工
                if ($('#EmployeeExitSpan')!=null){
                    showSpan('#EmployeeExitSpan')
                }
            }
        }

    });
});
//获得焦点

$("#UserId").focus(function() {
    if ($('#idExitSpan')!=null){
         hideError('#idExitSpan');
    }
    if ($('#IdNotExitSpan')!=null){
        hideError("#IdNotExitSpan");
    }
    if ($('#EmployeeExitSpan')!=null){
        hideError("#EmployeeExitSpan");
    }
});

//显示与隐藏

function showSpan(jqId) {
    // document.getElementById(jqId).style.display="block";
    $(jqId).show();
}


// 隐藏错误信息
function hideError(jqId) {
    $(jqId).hide();
}
