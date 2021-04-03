/**
 * 
 */
function draw(type,eventNode)
{
	var insertItem=eventNode.parentNode;
	if (type == "TODO")
		type = 'DOING';
	else if (type == "DOING") {
		type = 'DONE';
		insertItem.removeChild(eventNode);
	}

	var list = document.querySelector("section[id=" + type + "]");

	list.appendChild(insertItem);
}

function changeButtonClick(id,eventNode){
	
	var xhr=new XMLHttpRequest();
	var type=eventNode.parentNode.parentNode.gettAttribute("id");
	xhr.addEventListener("load",function(){
		if(this.responseText === "success")
			draw(type, eventNode);
	});
	xhr.open("GET","./TodoTypeServlet?id="+id+"&type="+type);
	xhr.send();
}