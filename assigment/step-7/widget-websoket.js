function doNumberGauge(socketUrl, containerId) {
	
//	var containerName = 'graph_' + deviceId + "_" + pinId;
//	var ws = null;
	var isConnected = false;
	var payload = null;

	if (socketUrl.indexOf("http") != -1) {
		socketUrl = socketUrl.replace("http", "ws");
	} else {
		socketUrl = socketUrl.replace("https", "ws");
		alert("Defaulting websockect to " + socketUrl);
	}

	function log(message) {
//		var console = document.getElementById('console');
//		var p = document.createElement('p');
//		p.style.wordWrap = 'break-word';
//		p.appendChild(document.createTextNode(message));
//		console.appendChild(p);
//		while (console.childNodes.length > 1) {
//		console.removeChild(console.firstChild);
//		}
//		console.scrollTop = console.scrollHeight;
		console.log(message);
	}

	function connect() {
		log("Opening connection to " + socketUrl);
		if ('WebSocket' in window) {
			ws = new WebSocket(socketUrl);
		} else if ('MozWebSocket' in window) {
			ws = new MozWebSocket(socketUrl);
		} else {
			alert('WebSocket is not supported by this browser.');
			return;
		}
		ws.onopen = function () {
			isConnected = true;
			log('Info: WebSocket connection opened.');
		};
		ws.onmessage = function (event) {
			payload = event.data;
		};
		ws.onclose = function (event) {
			isConnected = false;
			log('Info: WebSocket connection closed, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));
		};
	}

	function getData()
	{
		var value;

		if (!isConnected) {
			connect();
		}
		if (payload == null) {
			return;
		}
		var pinJson = JSON.parse(payload);
		
		console.log("Received payload " + payload);
		if (pinJson !== undefined 
				&& pinJson !== null) {

			$("#"+containerId).text(pinJson['output']);
		} else {
			// discard invalid data
			value = 0.0;
		}
		return 2677;
	}

	function drawNumber() {
		var value = getData();
		
	}

	$(document).ready(function() {

		setInterval(drawNumber, 1000);
	});
}

