var url = window.location.origin + window.location.pathname;
url = url.replace("http", "ws")
var urlParts = url.split("/");
urlParts.pop();
var newUrl = urlParts.join("/") + "/admin";


var ws = new WebSocket(newUrl);

var isSpeakerNotes = function () {

    return !!window.location.search.match(/receiver/gi);

}

var notifyServer = function (event) {
    data = {
        indexv: Reveal.getIndices().v,
        indexh: Reveal.getIndices().h,
        indexf: Reveal.getIndices().f || 0
    }
    if (!isSpeakerNotes()) {
        ws.send(JSON.stringify({command: "slide", data: JSON.stringify(data)}));
    }


}
Reveal.addEventListener("slidechanged", notifyServer);
Reveal.addEventListener("fragmentshown", notifyServer);
Reveal.addEventListener("fragmenthidden", notifyServer);


var sendPause = function (event) {
    ws.send(JSON.stringify({command: "pause"}));
}

var sendResume = function (event) {
    ws.send(JSON.stringify({command: "resume"}))
}

Reveal.addEventListener("paused", sendPause);
Reveal.addEventListener("resumed", sendResume);

ws.onopen = function (evt) {
    console.log("Connecte en Websockets !");
    console.debug(evt);
};

ws.onmessage = function (evt) {
    var command = JSON.parse(evt.data);
    if (command.command === "sessions") {
        window.postMessage(JSON.stringify(command), '*');
    }


};

ws.onclose = function () {
    console.log("Connection is closed...");
};