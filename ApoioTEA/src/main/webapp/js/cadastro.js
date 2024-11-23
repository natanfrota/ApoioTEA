function mostrarCamposEspecificos() {
    const tipo = document.getElementById("tipo").value;
    const voluntarioCampos = document.getElementById("voluntarioCampos");
    const familiaCampos = document.getElementById("familiaCampos");
	const experienciaCampo = document.getElementById("experiencia");
	const habilidadesCampo = document.getElementById("habilidades");
  
    voluntarioCampos.style.display = "none";
    familiaCampos.style.display = "none";

    if (tipo === "voluntario") {
      voluntarioCampos.style.display = "block";
	  experienciaCampo.required = true;
	  habilidadesCampo.required = true;
    } else if (tipo === "familia") {
      familiaCampos.style.display = "block";
    }
  }