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

fetch("http://localhost:9000/market-rest", {
    method: "GET"
}).then(response => {
    response.json().then(data => {
        populateTable(data);
    });
});

function populateTable(data) {
  const tableBody = document.querySelector("#heroTable"); // Assuming there is only one table on the page

  // Iterate over the data and create table rows
  data.forEach(marketHero => {
    const row = document.createElement("tr");

    // Create and populate cells for each field
    const nameCell = document.createElement("td");
    nameCell.textContent = marketHero.hero.name;
    row.appendChild(nameCell);

    const descriptionCell = document.createElement("td");
    descriptionCell.textContent = marketHero.hero.description;
    row.appendChild(descriptionCell);

    const hpCell = document.createElement("td");
    hpCell.textContent = marketHero.hero.hp;
    row.appendChild(hpCell);

    const attackCell = document.createElement("td");
    attackCell.textContent = marketHero.hero.attack;
    row.appendChild(attackCell);

    const defenseCell = document.createElement("td");
    defenseCell.textContent = marketHero.hero.defense;
    row.appendChild(defenseCell);

    const energyCell = document.createElement("td");
    energyCell.textContent = marketHero.hero.energy;
    row.appendChild(energyCell);

    const priceCell = document.createElement("td");
    priceCell.textContent = marketHero.market.price;
    row.appendChild(priceCell);

    row.addEventListener("click", () => {
      handleRowClick(marketHero);
    });

    tableBody.appendChild(row);
  });
}

let currentMarketHero;

function handleRowClick(marketHero) {
    currentMarketHero = marketHero;
    document.getElementById("right-side-div").style.display = "flex";
    document.getElementById("name").innerHTML = marketHero.hero.name;
    document.getElementById("description").innerHTML = marketHero.hero.description;
    document.getElementById("hp").innerHTML = marketHero.hero.hp;
    document.getElementById("attack").innerHTML = marketHero.hero.attack;
    document.getElementById("defense").innerHTML = marketHero.hero.defense;
    document.getElementById("energy").innerHTML = marketHero.hero.energy;
    document.getElementById("image").src = marketHero.hero.imgUrl;
    document.getElementById("buy-bt").innerHTML = "Buy for " + marketHero.market.price;
    if(userId === marketHero.market.sellerId) {
        document.getElementById("buy-bt").style.display = "none";
        document.getElementById("buy-bt").disabled = true;
    } else {
        document.getElementById("buy-bt").style.display = "block";
        document.getElementById("buy-bt").disabled = false;
    }

}

let onBuy = () => {
    if(user.money < currentMarketHero.market.price){
        alert("Not enough money");
        return;
    }
    if(userId === currentMarketHero.market.sellerId){
        alert("You can't buy your own hero");
        return;
    }

    fetch("http://localhost:9000/market-rest/buy/" + userId, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            hero: currentMarketHero.hero,
            market: currentMarketHero.market
        })
    }).then(response => {
        response.json().then(data => {
            console.log(data);
        });
    });
}