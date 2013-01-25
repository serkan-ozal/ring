function common_loginSupporter() {
    var result = false;
    $.ajax({
        url:"../j_spring_security_check",
        async:false,
        beforeSend:function (xhr) {
            xhr.setRequestHeader('j_username', $("#username").val());
            xhr.setRequestHeader('j_password', $("#password").val());
            xhr.setRequestHeader('_spring_security_remember_me', "true");
        },
        type:"POST",
        success:function (data, textStatus, jqXHR) {
            //
        },
        error:function (jqXHR, textStatus, errorThrown) {
            //
        },
        complete:function (jqXHR) {
            springHeader = jqXHR.getResponseHeader('SPRING_SECURITY_REMEMBER_ME_COOKIE');

            if (springHeader != null) {
                alert("login ok");
                result = true;
            } else {
                alert("login failed");
            }
        }
    });
    return result;
}