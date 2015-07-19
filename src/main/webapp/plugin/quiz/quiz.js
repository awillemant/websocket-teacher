var RevealQuiz = (function () {

    function buildQuiz() {
        var answerGroups = parseAnswers();

        for (i in answerGroups) {
            for (var j = 0; j < answerGroups[i].length; j++) {
                answerGroups[i][j].onclick = getClickCallback(answerGroups[i][j], i, j);
                answerGroups[i][j].removeAttribute("class");
                answerGroups[i][j].removeAttribute("data-fragment-index");
            }
        }

    }

    parseAnswers = function () {
        var quizAnswers = document.querySelectorAll('.quiz ul li');
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

    getClickCallback = function (element, quizId, answerId) {
        return function () {
            element.classList.toggle("checked");
            data = {
                quizId: quizId,
                answerId: answerId,
                answerValue: element.classList.contains("checked")
            }
            ws.send(JSON.stringify({command: "quiz", data: JSON.stringify(data)}));
        }
    };

    buildQuiz();


    return {open: buildQuiz};

})
();
