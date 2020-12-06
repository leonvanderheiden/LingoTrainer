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

function createRound(type) {
    var round = new Object();
    round.roundType = type;

    var roundJsonString = JSON.stringify(round);

    fetch("/round", {
        method: 'POST',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json',}, body: roundJsonString })
        .then(response => response.json())
        .then(function(roundInfo) {
            console.log(roundInfo);
            return JSON.stringify(roundInfo);
        })
}

async function newGame() {
    var game = new Object();
    game.score = await fetchScore();
    game.rounds = new Array();
    game.rounds.push(await fetchRound());

    var gameJsonString = JSON.stringify(game);
    var user = JSON.parse(sessionStorage.getItem("loggedUser"));

    fetch("/game/" + user.id, {
        method: 'POST',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json',}, body: gameJsonString })
        .then(response => response.json())
        .then(function(gameInfo) {
            console.log(gameInfo);
        })

}