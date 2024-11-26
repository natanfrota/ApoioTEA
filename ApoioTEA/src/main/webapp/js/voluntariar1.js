let botao = document.getElementsByClassName('botao-voluntariar');

function candidatar(atividadeId, familiaId, indice){
	console.log(botao[indice].innerText);
	if(botao[indice].innerText == 'Voluntariar-se'){
		voluntariar(atividadeId, familiaId, indice);
	} else if(botao[indice].innerText == 'Cancelar'){
		cancelar(atividadeId, familiaId, indice);
	} 
}

function voluntariar(atividadeId, familiaId, indice) {
    let xhr = new XMLHttpRequest();    
    xhr.open("POST", "adicionar-candidato", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("atividadeId=" + encodeURIComponent(atividadeId) + "&familiaId=" + 
		encodeURIComponent(familiaId));
		
	mudarBotao(indice);
}

function cancelar(atividadeId, familiaId, indice) {
    let xhr = new XMLHttpRequest();    
    xhr.open("POST", "cancelar-candidatura", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("atividadeId=" + encodeURIComponent(atividadeId) + "&familiaId=" + 
		encodeURIComponent(familiaId));
	mudarBotao(indice);
}

function mudarBotao(indice) {
	if (botao[indice].innerText == 'Voluntariar-se') {
		botao[indice].style.backgroundColor = 'red';
		botao[indice].innerText = 'Cancelar';
	} else if (botao[indice].innerText == 'Cancelar') {
		botao[indice].style.backgroundColor = '#1e88e5';
		botao[indice].innerText = 'Voluntariar-se';
	}
}