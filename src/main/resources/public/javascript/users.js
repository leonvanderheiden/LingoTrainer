function checkIfUserExists() {

    var username = document.forms["login"]["username"].value
    alert(username);

    fetch("/user/" + username, {method: "GET"})
        .then(function (response) {
            return response.json();

        }).then(function (users) {

            console.log(users);
            document.querySelector(".example").textContent = users.name;
        })
}