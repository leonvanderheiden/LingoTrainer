var gameId;
var word;
var attempt = 0;
var round;
var typeOfRound;
var laststring = "_____";

//<-- Functies voor het laden van de pagina -->
//Methode die wordt aangeroepen bij het laden van de pagina
async function loadGame() {
    gameId = sessionStorage.getItem("game");
    sessionStorage.removeItem("game");
    await startNewRound();
}

//Er wordt een game area gemaakt waar alle letters op komen te staan
function createGameArea() {
    for(var y = 0; y < 5; y++)
    {
        for(var x = 0; x < typeOfRound; x++)
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


//<-- Functies voor het spelen van een game -->
//Er wordt een nieuw woord geschreven in de game area
function writeWord(writtenWord) {
    for(var i = 0; i < typeOfRound; i++)
    {
        var letter = writtenWord.charAt(i);
        document.getElementById("attempt_" + attempt + "_" + i).innerHTML = letter;
    }
}

//Feedback fetch om de letters van het ingevoerde woord te controleren
const fetchFeedback = async args => {
    const res = await fetch(`/feedback/` + document.getElementById("word").value + "/" + attempt, {
        method: "POST",
        body: JSON.stringify(round),
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
        await startNewRound();
    }
    else if (feedback == "false") {
        createNewAttempt(laststring);
        alert("Ongeldig woord!");
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
        createNewAttempt(givenLetters);
    }
}

//Functie om een nieuwe poging voor te bereiden
function createNewAttempt(givenLetters) {
    attempt++;
    //Speler is er niet ingeslaagd om het woord te raden
    if (attempt == 5) {
        alert("Helaas, het woord was: " + word);
        //Game ended
    }
    else {
        writeWord(givenLetters);
        laststring = givenLetters;
    }
}

//De game area wordt leeggehaald
function deleteGameArea() {
    const div = document.getElementById("gameArea");
    while (div.firstChild) {
        div.removeChild(div.lastChild);
    }
}

//<-- Functies voor het laden van een nieuwe ronde -->
const fetchRound = async args => {
    const res = await fetch(`/newround`, {
        method: "POST",
        body: JSON.stringify({ roundType: typeOfRound + " letterwoord" }),
        headers: { "Accept": "application/json", "Content-Type": "application/json" }
    });
    return await res.json();
};

//Het huidige game object wordt opgehaald en gereturned
const fetchGame = async args => {
    const res = await fetch(`/game/` + gameId, { method: "GET" });
    return res.json();
};

//Maken van een nieuwe ronde
async function startNewRound() {
    const g = await fetchGame();
    //Er kunnen maximaal 5 rondes gespeeld worden
    if (g.rounds.length < 5) {
        var roundNum = 5;
        attempt = 0;

        //Zodra er al eerder een ronde is gespeeld wordt gekeken naar wat de volgende rondesoort moet zijn
        if (g.rounds.length > 0) {
            deleteGameArea();
            var roundNum = parseInt(g.rounds[(g.rounds.length - 1)].roundType.charAt(0));
            if (roundNum == 7) { roundNum = 5;}
            else { roundNum++; }
        }

        //Er wordt een nieuw round object aangemaakt en toegevoegd aan de roundlist in de huidige game
        typeOfRound = roundNum;
        round = await fetchRound(roundNum);
        word = round.word.word;
        g.rounds.push(round);

        //De game wordt gemaakt in de frontend en de eerste letter van het woord wordt ingeladen
        createGameArea();
        laststring = word.charAt(0) + '_'.repeat(roundNum - 1);
        writeWord(laststring);

        //De nieuwe ronde wordt nu ook daadwerkelijk toegevoegd aan het game object in de database
        fetch("/game/" + gameId, {
            method: 'PUT',
            headers: { 'Accept': 'application/json', 'Content-Type': 'application/json',}, body: JSON.stringify(g) })
            .then(response => response.json())
            .then(function(gameInfo) { })
    }
    else {
        //Game is beëindigd
    }
}