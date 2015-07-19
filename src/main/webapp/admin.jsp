<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">

    <title>reveal.js - The HTML Presentation Framework</title>


    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui">

    <link rel="stylesheet" href="webjars/reveal.js/3.0.0/css/reveal.css">
    <link rel="stylesheet" href="webjars/reveal.js/3.0.0/css/theme/white.css" id="theme">

    <!-- Code syntax highlighting -->
    <link rel="stylesheet" href="webjars/reveal.js/3.0.0/lib/css/zenburn.css">

    <!-- Printing and PDF exports -->
    <script>
        var link = document.createElement('link');
        link.rel = 'stylesheet';
        link.type = 'text/css';
        link.href = window.location.search.match(/print-pdf/gi) ? 'webjars/reveal.js/3.0.0/css/print/pdf.css' : 'webjars/reveal.js/3.0.0/css/print/paper.css';
        document.getElementsByTagName('head')[0].appendChild(link);

    </script>

    <style>
        .quiz ul li:not(.fragment) {
            display: none;
        }
    </style>

    <!--[if lt IE 9]>
    <script src="webjars/reveal.js/3.0.0/lib/js/html5shiv.js"></script>
    <![endif]-->
</head>

<body>

<div class="reveal">
    <!-- Any section element inside of this container is displayed as a slide -->
    <div class="slides">
        <%@ include file="slides.jsp" %>

    </div>

</div>

<script src="webjars/reveal.js/3.0.0/lib/js/head.min.js"></script>
<script src="webjars/reveal.js/3.0.0/js/reveal.js"></script>

<script>

    // Full list of configuration options available at:
    // https://github.com/hakimel/reveal.js#configuration
    Reveal.initialize({
        controls: true,
        progress: true,
        history: true,
        center: true,

        transition: 'fade', // none/fade/slide/convex/concave/zoom

        // Optional reveal.js plugins
        dependencies: [
            {
                src: 'webjars/reveal.js/3.0.0/lib/js/classList.js', condition: function () {
                return !document.body.classList;
            }
            },
            {
                src: 'webjars/reveal.js/3.0.0/plugin/markdown/marked.js', condition: function () {
                return !!document.querySelector('[data-markdown]');
            }
            },
            {
                src: 'webjars/reveal.js/3.0.0/plugin/markdown/markdown.js', condition: function () {
                return !!document.querySelector('[data-markdown]');
            }
            },
            {
                src: 'webjars/reveal.js/3.0.0/plugin/highlight/highlight.js', async: true, condition: function () {
                return !!document.querySelector('pre code');
            }, callback: function () {
                hljs.initHighlightingOnLoad();
            }
            },
            {src: 'webjars/reveal.js/3.0.0/plugin/zoom-js/zoom.js', async: true},
            {src: 'plugin/notes/notes.js', async: true},
            {src: 'plugin/websockets/remote_admin.js', async: true}
        ]
    });

</script>

</body>
</html>
