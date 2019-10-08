var ws;

function connect() {
    var username = document.getElementById("username").value;

    var host = document.location.host;
    var pathname = document.location.pathname;

    var uri = "ws://" + host + pathname + "" + "messaging";
    /*  var uri = "ws://localhost:8099/messaging";*/
    console.log(uri);

    ws = new WebSocket(uri);

    ws.onmessage = function (event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };

    ws.onerror = function (event) {
        console.log("error");
    }

    function send() {
        var content = document.getElementById("msg").value;
        var json = JSON.stringify({
            "content": content
        });

        ws.send(json);
    }
}
