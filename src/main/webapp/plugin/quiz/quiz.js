var RevealQuiz = (function () {

    function buildQuiz() {
        var answerGroups = parseAnswers();

        for (i in answerGroups) {
            for (var j = 0; j < answerGroups[i].length; j++) {
                answerGroups[i][j].onclick = getClickCallback(i, j);
            }
        }

    }

    parseAnswers = function () {
        var quizAnswers = document.querySelectorAll('.quiz ol li');
        var answerGroups = {}
        for (var i = 0; i < quizAnswers.length; i++) {
            var quizId = quizAnswers[i].parentNode.parentNode.id;
            if (!answerGroups[quizId]) {
                answerGroups[quizId] = [];
            }
            answerGroups[quizId].push(quizAnswers[i]);
        }
        return answerGroups;
    }

    getClickCallback = function (quizId, answerId) {
        return function () {
            data = {
                quizId: quizId,
                answerId: answerId
            }
            ws.send(JSON.stringify({command: "quiz", data: JSON.stringify(data)}));
        }
    };

    buildQuiz();


    return {open: buildQuiz};

})();
