if(document.cookie.indexOf("userId") === -1){
    document.location = "http://localhost:9000/";
}

let createHero = function () {
    const name = document.getElementById("form-name")?.value;
    const description = document.getElementById("form-description")?.value;
    const imgUrl = document.getElementById("form-imgUrl")?.value;
    const hp = document.getElementById("form-hp")?.value;
    const energy = document.getElementById("form-energy")?.value;
    const attack = document.getElementById("form-attack")?.value;
    const defense = document.getElementById("form-defense")?.value;
    if (name === "" || description === "" || imgUrl === "" || hp === "" || energy === "" || attack === "" || defense === "") {
        alert("Please fill in all fields");
        return;
    }

    const userId = +document.cookie.split("=")[1];

    if(userId == null){
        alert("Please login first!");
        return;
    }

    const hero = {
        name: name,
        description: description,
        imgUrl: imgUrl,
        hp: hp,
        energy: energy,
        attack: attack,
        defense: defense,
        playerId: userId
    };

    fetch("http://localhost:9000/hero-rest", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(hero)
    }).then(response => {
        response.json().then(data => {
            if(data != null && data.id != null){
                window.location = "http://localhost:9000/hero/detail.html?id=" + data.id;
            }
        });
    })
}