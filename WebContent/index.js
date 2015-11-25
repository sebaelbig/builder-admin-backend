var ws = null;
 
function connect() {
    var URL = 'ws://' + location.host  + '/websockets/simple';
    if ('WebSocket' in window) {
        ws = new WebSocket(URL);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(URL);
    } else {
        alert('Tu navegador no soporta WebSockets');
        return;
    }
    ws.onopen = function () {
        // pintamos mensaje
        addMessage('Concectado!');
    };
    ws.onmessage = function (event) {
        var message = event.data;
        // pintamos mensaje
        addMessage(message);
    };
    ws.onclose = function () {
        // pintamos mensaje
        addMessage('Desconectado!');
    };
    ws.onerror = function (event) {
        addMessage('Se produjo un error! ');
    };
}
 
function disconnect() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}
     
function sendMessage(message) {
    if (ws != null) {
        ws.send(message);
    }
}   