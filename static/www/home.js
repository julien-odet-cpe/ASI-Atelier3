if(document.cookie.indexOf("userId") === -1){
    document.location = "http://localhost:9000/";
}

let user;

fetch("http://localhost:9000/player-rest/" + document.cookie.split("=")[1])
    .then((response) => response.json()
        .then((data) => {
            if(data === null || data.id === null){
                return;
            }
            document.getElementById("money").innerHTML = data.money;
            document.getElementById("surname").innerHTML = data.surname;
            user = data;
        }))


let logout = () => {
    document.cookie = 'userId=; Max-Age=0'
    document.location = "http://localhost:9000/";
};

function redirectToBuy() {
    window.location.href = "market/buy.html";
}

function redirectToSell() {
    window.location.href = "market/sell.html";
}

function redirectToGame() {
    window.location.href = "game/game.html";
}