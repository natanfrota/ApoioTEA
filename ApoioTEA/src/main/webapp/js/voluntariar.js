let botao = document.getElementsByClassName('botao-voluntariar');

function voluntariar(atividadeId, indice) {
    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", "adicionar-candidato", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
    xhr.send("atividadeId=" + encodeURIComponent(atividadeId));
	console.log(indice);
	botao[indice].disabled = true;
	botao[indice].style.backgroundColor = 'gray';
	botao[indice].innerText = 'Voluntário';
}