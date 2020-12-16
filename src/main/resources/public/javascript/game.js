var gameId;
var userId;
var word;
var attempt = 0;
var round;
var typeOfRound;
var laststring = "_____";
var lastAction = new Date();

//<-- Functies voor het laden van de pagina -->
//Methode die wordt aangeroepen bij het laden van de pagina
async function loadGame() {
    gameId = sessionStorage.getItem("game");
    sessionStorage.removeItem("game");
    var hs = await fetchHighscore();
    document.getElementById("highscore").innerHTML = "Highscore: " + hs.highscore;
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
    const res = await fetch(`/feedback/` + document.getElementById("word").value, {
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
async function enterWord(failed) {
    var currentTime = new Date();
    var timeDif = currentTime - lastAction;
    if (timeDif < 10000) {
        //Gebruikt de fetch methode om feedback te halen uit het object ronde
        const feedback = await fetchFeedback();
        var givenLetters = "";
        if (feedback == "true") {
            alert("Correct!");
            var attemptsLeft = 5 - attempt;
            await updateScore(100 * attemptsLeft);
            await startNewRound();
        } else if (feedback == "false") {
            createNewAttempt(laststring);
            alert("Ongeldig woord!");
        } else {
            //Leest de feedback uit een veranderd kleuren en letters waar nodig
            var letters = feedback.split('\n');
            var totalScoreAddition = 0;

            for (var i = 0; i < (letters.length - 1); i++) {
                document.getElementById("attempt_" + attempt + "_" + i).innerHTML = letters[i].charAt(0);
                if (letters[i].includes("(correct)")) {
                    givenLetters += letters[i].charAt(0);
                    //Score +10 letter op de goede plaats zit
                    totalScoreAddition += 10;
                } else {
                    if (letters[i].includes("(absent)")) {
                        document.getElementById("attempt_" + attempt + "_" + i).style.backgroundColor = "red";
                    } else {
                        //Score +5 zodra er een letter aanwezig is
                        totalScoreAddition += 5;
                        document.getElementById("attempt_" + attempt + "_" + i).style.backgroundColor = "yellow";
                    }
                    givenLetters += "_";
                }
            }
            await updateScore(totalScoreAddition);
            await createNewAttempt(givenLetters);
        }
    }
    else {
        await createNewAttempt(laststring);
        alert("U heeft de 10 seconde denktijd overschreden.")
    }
    document.getElementById("word").value = "";
    currentTime = new Date();
    lastAction = currentTime;
}

//Functie om een game te beëindigen
async function endGame(message) {
    const g = await fetchGame();
    if (await updateHighscore() == true) {
        message += " [Nieuwe highscore!]";
    }
    alert(message);
    window.location.href = "menu.html";
}

//Functie om een nieuwe poging voor te bereiden
async function createNewAttempt(givenLetters) {
    attempt++;
    const g = await fetchGame();
    //Speler is er niet ingeslaagd om het woord te raden
    if (attempt == 5) {
        await endGame("Helaas, het woord was: " + word + ". Uw eindscore is: " + document.getElementById("score").innerHTML.split(" ")[1]);
    } else {
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

//De huidige highscore wordt opgehaald en gereturned
const fetchHighscore = async args => {
    const res = await fetch(`/highscore/` + JSON.parse(sessionStorage.getItem("loggedUser")).highscore.id, { method: "GET" });
    return res.json();
};

//Updaten van de score
async function updateScore(addition) {
    const g = await fetchGame();
    var score = g.score;
    g.score.score += addition;
    document.getElementById("score").innerHTML = "Score: " + g.score.score;
    fetch("/score/" + score.id, {
        method: 'PUT',
        headers: {'Accept': 'application/json', 'Content-Type': 'application/json',}, body: JSON.stringify(g.score)
    })
        .then(response => response.json())
        .then(function (scoreInfo) {
        })
}

//Update highscore
async function updateHighscore() {
    var highscoreObj = JSON.parse(sessionStorage.getItem("loggedUser")).highscore;
    var hs = await fetchHighscore();
    var score = document.getElementById("score").innerHTML.split(" ")[1];
    var returnValue = false;

    if (score > hs.highscore) {
        returnValue = true;
        fetch("/highscore/" + highscoreObj.id, {
            method: 'PUT',
            headers: {'Accept': 'application/json', 'Content-Type': 'application/json',}, body: JSON.stringify({ highscore : score })
        })
            .then(response => response.json())
            .then(function (scoreInfo) {
            })
    }
    return returnValue;
}

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
        var today = new Date();
        lastAction = today;
    }
    else {
        const g = await fetchGame();
        await endGame("U heeft alle rondes uitgespeeld. Uw eindscore is: " + g.score.score);
    }
}