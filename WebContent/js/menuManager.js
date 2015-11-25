var html = document.getElementById("menuVertical").innerHTML;
Seam.Remoting.loadingMessage = "Guardando menu...";
var menuMgr = Seam.Component.getInstance('menuManager');
menuMgr.guardarHTML(html);

/*
function llamarFuncion(id){
	var a = document.getElementById(id);
	var url = a.getAttribute("href") + "&url=" + a.parentNode.getAttribute("direccion");
	location.href = url;
}
*/