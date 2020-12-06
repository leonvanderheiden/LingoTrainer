var user = JSON.parse(sessionStorage.getItem("loggedUser"));

const fetchScore = async args => {
    const res = await fetch(`/score`, {
        method: "POST",
        body: JSON.stringify({ score: 0 }),
        headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }
    });
    const body = await res.json();
    return body;
};

const fetchRound = async args => {
    const res = await fetch(`/round`, {
        method: "POST",
        body: JSON.stringify({ roundType: "5 letterwoord" }),
        headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }
    });
    const body = await res.json();
    return body;
};

//Misschien voor later
function loadGames() {
    fetch("player/" + user.id)
        .then(response => response.json())
        .then(function(response) {
            for (var i = 0; i < response.games.length; i++) {
                var rounds = response.games[i].rounds.length;
                if (rounds < 5) {

                }
            }
        });
}

async function newGame() {
    var game = new Object();
    game.score = await fetchScore();
    game.rounds = new Array();
    game.rounds.push(await fetchRound());

    var gameJsonString = JSON.stringify(game);

    fetch("/game/" + user.id, {
        method: 'POST',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json',}, body: gameJsonString })
        .then(response => response.json())
        .then(function(gameInfo) {
            console.log(gameInfo);
        })

}