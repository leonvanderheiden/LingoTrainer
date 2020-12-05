var player = new Object();

function myFunction() {
    player.name = document.forms["login"]["username"].value
    player.password = document.forms["login"]["password"].value

    var playerJsonString = JSON.stringify(player);

    fetch("/login", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }, body: playerJsonString })
        .then(response => response.json())
        .then(function(playerInfo) {
            console.log(playerInfo);
        })


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