
let botao = document.getElementsByClassName('botao-voluntariar');

function voluntariar(atividadeId) {
    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", "adicionar-candidato", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
    xhr.send("atividadeId=" + encodeURIComponent(atividadeId));
}