function loginFunction() {
    var player = new Object();
    player.name = document.getElementById("username").value;
    player.password = document.getElementById("password").value;

    var playerJsonString = JSON.stringify(player);

    fetch("/login", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }, body: playerJsonString })
        .then(response => response.json())
        .then(function(playerInfo) {
            sessionStorage.setItem("loggedUser", JSON.stringify(playerInfo));
            console.log(playerInfo);
            window.location.href = "menu.html";
        })
}