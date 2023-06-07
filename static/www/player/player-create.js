
if(document.cookie.indexOf("userId") !== -1){
    document.location = "http://localhost:9000/home.html";
}

let onCreate = () => {
    let username = document.getElementById("username").value;
    let surname = document.getElementById("surname").value;
    let password = document.getElementById("pwd").value;
    let confirmPassword = document.getElementById("repwd").value;
    if(password !== confirmPassword){
        alert("Passwords do not match!");
        return;
    }
    let data = {
        username: username,
        surname: surname,
        password: password
    }

    fetch("http://localhost:9000/player-rest", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    }).then(response => {
        response.json().then(data => {
            if(data != null && data.id != null){
                document.cookie = "userId=" + data.id + ";path=/";
                window.location = "http://localhost:9000/home.html";
            }
        });
    });
}