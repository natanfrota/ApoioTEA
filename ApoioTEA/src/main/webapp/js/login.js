let status = document.getElementById('status').value;
console.log(status);
let caixaMensagem = document.getElementById('caixaMensagem');
if(status == 'fracasso'){
	caixaMensagem.style.display = 'block';
}