function mostrarCamposEspecificos() {
    const tipo = document.getElementById("tipo").value;
    const voluntarioCampos = document.getElementById("voluntarioCampos");
    const familiaCampos = document.getElementById("familiaCampos");
  
    voluntarioCampos.style.display = "none";
    familiaCampos.style.display = "none";

    if (tipo === "voluntario") {
      voluntarioCampos.style.display = "block";
    } else if (tipo === "familia") {
      familiaCampos.style.display = "block";
    }
}

let status = document.getElementById('status').value;
console.log(status);
let caixaMensagem = document.getElementById('caixaMensagem');
if(status == 'erro'){
	caixaMensagem.style.display = 'block';
}