var url = window.location.origin+window.location.pathname;
url = url.replace("http","ws")
var urlParts = url.split("/");
urlParts.pop();
var newUrl = urlParts.join("/")+"/control";


var ws = new WebSocket(newUrl);


ws.onopen = function(){
    console.log("Connecte en Websockets !");
};

ws.onmessage = function (evt){ 
	var command = JSON.parse(evt.data);
	if(command.command==="slide"){
		var slide = JSON.parse(command.data);
		 Reveal.slide(slide.indexh, slide.indexv, slide.indexf);
	}
	if(command.command==="pause"){
		Reveal.togglePause(true);
	}
	
	if(command.command==="resume"){
		Reveal.togglePause(false);
	}
   
};

ws.onclose = function(){ 
 console.log("Connection is closed..."); 
};
