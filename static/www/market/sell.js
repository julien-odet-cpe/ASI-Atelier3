if (document.cookie.indexOf("userId") === -1) {
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

const userId = +document.cookie.split("=")[1];

fetch("http://localhost:9000/market-rest/seller/" + userId)
    .then((response) => response.json()
        .then((data) => {
            if (data === null || data.id === null) {
                return;
            }
            getCards(data);
        }));

function getCards(openedMarket) {
    fetch("http://localhost:9000/hero-rest/player/" + userId, {
        method: "GET"
    }).then(response => {
        response.json().then(data => {
            populateTable(data, openedMarket);
        });
    });
}

function populateTable(data, openedMarket) {
    const tableBody = document.querySelector("#heroTable"); // Assuming there is only one table on the page

    // Iterate over the data and create table rows
    data.forEach(hero => {
        const row = document.createElement("tr");

        // Create and populate cells for each field
        const nameCell = document.createElement("td");
        nameCell.textContent = hero.name;
        row.appendChild(nameCell);

    const descriptionCell = document.createElement("td");
    descriptionCell.textContent = hero.description;
    row.appendChild(descriptionCell);

    const hpCell = document.createElement("td");
    hpCell.textContent = hero.hp;
    row.appendChild(hpCell);

    const attackCell = document.createElement("td");
    attackCell.textContent = hero.attack;
        row.appendChild(attackCell);

        const defenseCell = document.createElement("td");
        defenseCell.textContent = hero.defense;
        row.appendChild(defenseCell);

        const energyCell = document.createElement("td");
        energyCell.textContent = hero.energy;
        row.appendChild(energyCell);

        const priceCell = document.createElement("td");
        for (oM of openedMarket) {
            if (oM.heroId === hero.id) {
                hero.price = oM.price;
                priceCell.textContent = oM.price;
                break;
            }
        }
        row.appendChild(priceCell);

        row.addEventListener("click", () => {
            handleRowClick(hero);
        });

        tableBody.appendChild(row);
    });
}

let currentHero = null;
let currentStateIsSell = false;

function handleRowClick(hero) {

    const btn = document.getElementById("sell-cancel-bt");
    if(hero.price != null){
        btn.innerHTML = "Cancel";
        currentStateIsSell = true;
        currentHero = hero.id;
        document.getElementById("price-input").value = hero.price;
        document.getElementById("price-input").disabled = true;
    } else {
        btn.innerHTML = "Sell";
        currentStateIsSell = false;
        currentHero = hero.id;
        document.getElementById("price-input").disabled = false;
    }

    document.getElementById("right-side-div").style.display = "flex";
    document.getElementById("name").innerHTML = hero.name;
    document.getElementById("description").innerHTML = hero.description;
    document.getElementById("hp").innerHTML = hero.hp;
    document.getElementById("attack").innerHTML = hero.attack;
    document.getElementById("defense").innerHTML = hero.defense;
    document.getElementById("energy").innerHTML = hero.energy;
    document.getElementById("image").src = hero.imgUrl;
}


let onBtn = () => {
    if(currentStateIsSell){
        console.log('EKIP')
        fetch("http://localhost:8080/market-rest/hero/" + currentHero, {
            method: "DELETE"
        }).then(response => {
            response.json().then(data => {
                document.getElementById("right-side-div").style.display = "none";
                location.reload();
            });
        });
    } else {
        if(+document.getElementById("price-input").value <= 0){
            alert("Price must be greater than 0");
            return;
        }
        fetch("http://localhost:8080/market-rest/", {
            method: "POST",
            body: JSON.stringify({
                "heroId": currentHero,
                "sellerId": userId,
                "price": +document.getElementById("price-input").value
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => {
            console.log(response);
            response.json().then(data => {
                document.getElementById("right-side-div").style.display = "none";
                location.reload();
            });
        });
    }
}