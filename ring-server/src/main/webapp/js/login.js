$(document).ready(function () {
    // can be used if necessary
});

function loginSupporter() {
    common_loginSupporter();
//    if (loginOK) {
//        window.location.href = './home.html';
//    } else {
//        window.location.href = '../login.html?error=true';
//    }
}

function onKeyPressed() {
    var key = window.event.keyCode;

    // If the user has pressed enter, login
    if (key == 13) {
        loginSupporter();
    }
}