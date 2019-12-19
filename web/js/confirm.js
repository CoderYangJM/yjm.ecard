function checking() {
    var confirmPassword = document.getElementById("confirmPassword").value;
    var loginPassword = document.getElementById("RegisterPassword").value;
    if (confirmPassword == loginPassword) {
        return true;
    } else {
        alert("两次输入的密码不一致！密码：" + loginPassword + ",确认密码：" + confirmPassword);
        return false;
    }
}
