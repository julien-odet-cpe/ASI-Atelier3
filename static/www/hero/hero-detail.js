const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

let getHero = function () {
    fetch("http://localhost:9000/hero-rest/" + id, {
        method: "GET"
    }).then(response => {
        response.json().then(data => {
            console.log(data);
            document.getElementById("name").innerHTML = data.name;
            document.getElementById("description").innerHTML = data.description;
            document.getElementById("image").src = data.imgUrl;
            document.getElementById("hp").innerHTML = data.hp;
            document.getElementById("energy").innerHTML = data.energy;
            document.getElementById("attack").innerHTML = data.attack;
            document.getElementById("defense").innerHTML = data.defense;
        });
    })
}

getHero();