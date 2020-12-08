var gameId;
var word;
var attempt = 0;
var round;

const fetchFeedback = async args => {
    console.log(round);
    alert(round);
    const res = await fetch(`/feedback/` + document.getElementById("word").value, {
        method: "POST",
        body: round,
        headers: {
            "Accept": "application/json", "Content-Type": "application/json"
        }
    });
    const body = res.text();
    return body;
};

function getWord()
{
    fetch("game/" + gameId)
        .then(response => response.json())
        .then(function(response) {
            word = response.rounds[0].word.word;
            console.log(word);
            round = JSON.stringify(response.rounds[0]);
            console.log(round);
            document.getElementById("attempt_1").innerHTML = word.charAt(0) + " _ _ _ _";
        });
}

function loadGame() {
    gameId = sessionStorage.getItem("game");
    sessionStorage.removeItem("game");
    getWord();
}

async function enterWord() {
    const test = await fetchFeedback();
    console.log(test);
}