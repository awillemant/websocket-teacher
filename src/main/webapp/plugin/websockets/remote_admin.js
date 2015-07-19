var url = window.location.origin + window.location.pathname;
url = url.replace("http", "ws")
var urlParts = url.split("/");
urlParts.pop();
var newUrl = urlParts.join("/") + "/admin";

var quizData = {};
var nbOfSessions = 0;

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
        nbOfSessions = Number(command.data);
    }
    if (command.command === "quiz") {
        var answerData = JSON.parse(command.data);
        ensureQuizId(answerData.quizId);
        ensureAnswerId(answerData.quizId, answerData.answerId);
        quizData[answerData.quizId][answerData.answerId] += answerData.answerValue ? 1 : -1;
        updateQuizStats();
    }
};

var ensureQuizId = function (idToEnsure) {
    if (!quizData[idToEnsure]) {
        quizData[idToEnsure] = {}
    }
}

var ensureAnswerId = function (quizId, idToEnsure) {
    if (!quizData[quizId][idToEnsure]) {
        quizData[quizId][idToEnsure] = 0;
    }
}

var updateQuizStats = function () {
    console.log(nbOfSessions, quizData);
    for (quizId in quizData) {
        var scores = document.querySelectorAll("#" + quizId + " ul li span");
        for (answerId in quizData[quizId]) {
            var score = Math.round(100 * quizData[quizId][answerId] / nbOfSessions);
            scores[answerId].innerHTML = (score != 0) ? score + " %" : "";
        }
    }
}

ws.onclose = function () {
    console.log("Connection is closed...");
};