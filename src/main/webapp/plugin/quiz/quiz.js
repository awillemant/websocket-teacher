/**
 * Handles opening of and synchronization with the reveal.js
 * notes window.
 *
 * Handshake process:
 * 1. This window posts 'connect' to notes window
 *    - Includes URL of presentation to show
 * 2. Notes window responds with 'connected' when it is available
 * 3. This window proceeds to send the current presentation state
 *    to the notes window
 */
var RevealQuiz = (function () {

    function buildQuiz() {
        var quizAnswers = document.querySelectorAll('.quiz ol li');
        var answerGroups = {}
        for (var i = 0; i < quizAnswers.length; i++) {
            var quizId = quizAnswers[i].parentNode.parentNode.id;
            if (!answerGroups[quizId]) {
                answerGroups[quizId] = [];
            }
            answerGroups[quizId].push(quizAnswers[i]);
        }
        console.log(answerGroups);

        for (i in answerGroups) {
            for (var j = 0; j < answerGroups[i].length; j++) {
                answerGroups[i][j].onclick = (function (quizId, answerIndex) {
                    return function () {
                        console.log(quizId, " ", answerIndex);
                    }
                })(i, j);
            }
        }

    }

    buildQuiz();


    return {open: buildQuiz};

})();
