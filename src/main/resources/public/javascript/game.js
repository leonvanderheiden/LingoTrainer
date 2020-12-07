var gameId;

function loadGame() {
    gameId = sessionStorage.getItem("game");
    sessionStorage.removeItem("game");
    console.log(gameId);
}