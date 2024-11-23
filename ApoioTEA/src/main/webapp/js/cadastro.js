function mostrarCamposEspecificos() {
    const tipo = document.getElementById("tipo").value;
    const voluntarioCampos = document.getElementById("voluntarioCampos");
    const familiaCampos = document.getElementById("familiaCampos");
<<<<<<< HEAD
  
    voluntarioCampos.style.display = "none";
    familiaCampos.style.display = "none";

    if (tipo === "voluntario") {
      voluntarioCampos.style.display = "block";
=======
	const experienciaCampo = document.getElementById("experiencia");
	const habilidadesCampo = document.getElementById("habilidades");
  
    voluntarioCampos.style.display = "none";
    familiaCampos.style.display = "none";

    if (tipo === "voluntario") {
      voluntarioCampos.style.display = "block";
	  experienciaCampo.required = true;
	  habilidadesCampo.required = true;
>>>>>>> branch 'master' of https://github.com/SamuelHenrique007/ApoioTEA.git
    } else if (tipo === "familia") {
      familiaCampos.style.display = "block";
    }
  }