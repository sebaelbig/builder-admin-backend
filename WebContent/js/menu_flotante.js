var g_RMenu = null;
var g_LeafArray = null;

function cargarMenuFlotante(e)
{
	if(g_RMenu == null){
		g_RMenu = document.getElementById('radialContainer');
		
		g_LeafArray = new Array();
		g_LeafArray.push(document.getElementById('radialLeft'));
		g_LeafArray.push(document.getElementById('radialRight'));
	
	}
 
	//if the menu is already visible, or if the click was not with the left button
  	e = e ? e : window.event;  
	if(g_RMenu.style.display == '' || e.button != 0) return;

  	var pos = absoluteCursorPosition(e);
 	
  	g_RMenu.style.left = pos.X + 'px';
  	g_RMenu.style.top = pos.Y + 'px';
  	g_RMenu.style.display = '';
 
  	hookEvent(document, "mousedown", testCloseMenu);
  	
}

function  cerrarMenuFlotante()
{
  unhookEvent(document, "mousedown", testCloseMenu);

  g_RMenu.style.display = 'none';
 
  for(var i=0; i != g_LeafArray.length; i++)
  {
    g_LeafArray[i].style.backgroundColor = '#D7E5F2';
  }
  
}

function  testCloseMenu(e)
{
  var tar = getEventTarget(e);
   
  do
  {
    if(tar == g_RMenu) return;
    
    tar = tar.parentNode;
      
  }while(tar != null);
 
  cerrarMenuFlotante();
}