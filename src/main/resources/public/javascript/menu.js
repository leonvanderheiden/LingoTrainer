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

async function newGame() {
    var game = new Object();
    game.score = await fetchScore();
    game.rounds = new Array();

    var gameJsonString = JSON.stringify(game);

    fetch("/game/" + user.id, {
        method: 'POST',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json',}, body: gameJsonString })
        .then(response => response.json())
        .then(function(gameInfo) {
            sessionStorage.setItem("game", gameInfo.id);
            window.location.href = "game.html";
        })

}
