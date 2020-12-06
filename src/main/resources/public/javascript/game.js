function createScore(score) {
    var scoreObject = new Object();
    scoreObject.score = score;

    var scoreJsonString = JSON.stringify(scoreObject);

    fetch("/score", {
        method: 'POST',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json',}, body: scoreJsonString })
        .then(response => response.json())
        .then(function(scoreInfo) {
            console.log(scoreInfo);
            return JSON.stringify(scoreInfo);
        })
}

function newGame() {
    var game = new Object();
    game.score = createScore(0);

    var gameJsonString = JSON.stringify(game);
    console.log(gameJsonString);
    alert(gameJsonString);



    /*var username = document.forms["login"]["username"].value
    alert(username);

    fetch("/user/" + username, {method: "GET"})
        .then(function (response) {
            return response.json();

        }).then(function (users) {

            console.log(users);
            document.querySelector(".example").textContent = users.name;
        })*/
}