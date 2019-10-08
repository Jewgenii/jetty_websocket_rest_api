function connectChatServer() {
    ws = new WebSocket("ws://localhost:8099/file");

    ws.binaryType = "arraybuffer";
    ws.onopen = function() {
        alert("Connected.")
    };

    ws.onmessage = function(event) {
        //alert(event.msg);
        console.log(event.data)
    };

    ws.onclose = function() {
        alert("Connection is closed...");
    };
    ws.onerror = function(e) {
        alert(e.msg);
    }

}

function sendFile() {
    var file = document.getElementById('filename').files[0];

    if(file.length> 1024 * 1024 * 10){

        var loadfactor  = 0;
        var offset = 0;
        for (var i = 0;i< file.length / loadfactor; i+=loadfactor)
        {
            var blob = file.slice(offset,loadfactor);
            loadfactor  += 1024 * 1024 * 10;
        }
    }


    ws.send('filename:' + file.name);
    var reader = new FileReader();
    var rawData = new ArrayBuffer();
    //alert(file.name);

   // ws.send(file);

    reader.onload = function (e) {
        rawData = e.target.result;
        ws.send(rawData);
      //  alert("the File has been transferred.")
      //  ws.send('end');
    }

    reader.readAsArrayBuffer(file);
}


