var gameId;
var word;
var attempt = 0;
var round;


//Methode die wordt aangeroepen bij het laden van de pagina
function loadGame() {
    gameId = sessionStorage.getItem("game");
    sessionStorage.removeItem("game");
    createGameArea();
    getWord();
}

//Het te raden woord wordt opgeladen en ingeladen in de eerste rij
function getWord()
{
    fetch("game/" + gameId)
        .then(response => response.json())
        .then(function(response) {
            word = response.rounds[0].word.word;
            round = JSON.stringify(response.rounds[0]);
            writeWord(0,word.charAt(0) + "____")
        });
}

//Er wordt een game area gemaakt waar alle letters op komen te staan
function createGameArea() {
    for(var y = 0; y < 5; y++)
    {
        for(var x = 0; x < 5; x++)
        {
            var label = document.createElement("LABEL");
            label.setAttribute("name", "area");
            label.setAttribute("id", "attempt_" + y + "_" + x);
            label.style.backgroundColor = "green";
            label.innerHTML = "_";
            document.getElementById("gameArea").appendChild(label);
        }
        document.getElementById("gameArea").appendChild(document.createElement("br"));
    }
}


//Er wordt een nieuw woord geschreven in de game area
function writeWord(attempt, word) {
    for(var i = 0; i < 5; i++)
    {
        var letter = word.charAt(i);
        document.getElementById("attempt_" + attempt + "_" + i).innerHTML = letter;
    }
}

//Feedback fetch om de letters van het ingevoerde woord te controleren
const fetchFeedback = async args => {
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

//Gebruiker voert een woord in
async function enterWord() {
    //Gebruikt de fetch methode om feedback te halen uit het object ronde
    const feedback = await fetchFeedback();
    var givenLetters = "";
    if (feedback == "true") {
        alert("Correct!");
    }
    else if (feedback == "false") {
        alert("Ongeldig woord!")
        attempt++;
    }
    else {
        //Leest de feedback uit een veranderd kleuren en letters waar nodig
        var letters = feedback.split('\n');
        for(var i = 0; i < (letters.length - 1);i++){
            document.getElementById("attempt_" + attempt + "_" + i).innerHTML = letters[i].charAt(0);
            if (letters[i].includes("(correct)")) {
                givenLetters += letters[i].charAt(0);
            }
            else {
                if (letters[i].includes("(absent)")) {
                    document.getElementById("attempt_" + attempt + "_" + i).style.backgroundColor = "red";
                }
                else {
                    document.getElementById("attempt_" + attempt + "_" + i).style.backgroundColor = "yellow";
                }
                givenLetters += "_";
            }
        }
        attempt++;
        //Speler is er niet ingeslaagd om het woord te raden
        if (attempt == 5) {
            alert("Helaas, het woord was: " + word);
        }
        writeWord(attempt, givenLetters);
    }
}