var username;
var id;

function getUser() {
    alert(username + " " + id);
}

function myFunction() {
    var player = new Object();
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
            username = playerInfo.name;
            id = playerInfo.id;
            alert("test");
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