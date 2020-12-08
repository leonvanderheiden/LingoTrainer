var gameId;
var word;
var attempt = 1;
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

function createGameArea() {
    alert("wa")
    var gameArea = document.querySelector(".gameArea")
    for(var y = 0; y < 5; y++){
        for(var x = 0; x < x; i++){
            var newlabel = document.createElement("label");
            newlabel.setAttribute("id", "attempt_" + y + "_" + x);
            newlabel.innerHTML = "_";
            newlabel.style.backgroundColor = "red";
            gameArea.appendChild(newlabel);
        }
    }
}

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
    createGameArea();
    getWord();
}

async function enterWord() {
    const test = await fetchFeedback();
    var givenLetters = "";
    var letters = test.split('\n');
    for(var i = 0; i < letters.length;i++){
        if (letters[i].includes("(correct)")) {
            givenLetters += letters[i].charAt(0) + " ";
        }
        else {
            givenLetters += "_ ";
        }
    }
    attempt++;
    document.getElementById("attempt_" + attempt).innerHTML = givenLetters;
}