if(document.cookie.indexOf("userId") !== -1){
    document.location = "http://localhost:9000/home.html";
}
function redirectToLogin() {
    window.location.href = "player/login.html";
}

function redirectToSignup() {
    window.location.href = "player/create.html";
}