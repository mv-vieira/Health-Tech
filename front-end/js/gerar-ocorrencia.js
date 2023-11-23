
//Recebendo dados das ambulâncias e colocando no select do form

document.addEventListener("DOMContentLoaded", function () {
    // Requisição usando fetch
    fetch("http://localhost:8080/health-tech/ambulancias?page=0&size=15")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Erro na requisição");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        const ambulanciaSelect = document.getElementById("ambulancia");
        ambulanciaSelect.innerHTML = "";
  
        const defaultOption = document.createElement("option");
        defaultOption.text = "Selecione uma ambulância";
        ambulanciaSelect.add(defaultOption);
  
        data.content.forEach((ambulancia) => {
          const option = document.createElement("option");
          option.value = ambulancia.id;
          option.text =
            ambulancia.placaAmbulancia + " - " + ambulancia.tipoAmbulancia;
          ambulanciaSelect.add(option);
        });
      })
      .catch((error) => console.error("Erro:", error));
  });

//Recebendo dados dos hospitais e colocando no select do form
document.addEventListener("DOMContentLoaded", function () {
    // Requisição usando fetch
    fetch("http://localhost:8080/health-tech/hospital/listar-hospitais?page=0&size=10")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Erro na requisição");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        const hospitalSelect = document.getElementById("hospital");
        hospitalSelect.innerHTML = "";
  
        const defaultOption = document.createElement("option");
        defaultOption.text = "Selecione um hospital";
        hospitalSelect.add(defaultOption);
  
        data.content.forEach((hospital) => {
          const option = document.createElement("option");
          option.value = hospital.id;
          option.text =
          hospital.nomeHospital + " - " + hospital.municipio;
          hospitalSelect.add(option);
        });
      })
      .catch((error) => console.error("Erro:", error));
  });

  //Recebendo dados dos paciente e ambulancia e colocando no select do form
document.addEventListener("DOMContentLoaded", function () {
    // Requisição usando fetch
    fetch("http://localhost:8080/health-tech/paciente?page=0&size=15")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Erro na requisição");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        const ambulanciaSelect = document.getElementById("ambulancia");
        const pacienteSelect = document.getElementById("paciente");
        pacienteSelect.innerHTML = "";
        ambulanciaSelect.innerHTML = "";
  
        // const defaultOption = document.createElement("option");
        // defaultOption.text = "Selecione o paciente";
        // pacienteSelect.add(defaultOption);

        const pacientes = data.content;
  
        if (pacientes.length > 0) {
            const ultimoPaciente = pacientes[pacientes.length - 1];
            
            const optionAmbulancia = document.createElement("option");
            optionAmbulancia.value = ultimoPaciente.ambulancia.id
            optionAmbulancia.text = ultimoPaciente.ambulancia.placaAmbulancia + ultimoPaciente.ambulancia.tipoAmbulancia
            ambulanciaSelect.add(optionAmbulancia);

            const optionPaciente = document.createElement("option");
            optionPaciente.value = ultimoPaciente.id;
            optionPaciente.text = ultimoPaciente.nome + " - " + formatarDataNascimento(ultimoPaciente.dataNascimento);
            pacienteSelect.add(optionPaciente);


        }
            
        // data.content.forEach((paciente) => {
        //   const option = document.createElement("option");
        //   option.value = paciente.id;
        //   option.text =
        //   paciente.nome + " - " + paciente.dataNascimento;
        //   pacienteSelect.add(option);
        // });
      })
      .catch((error) => console.error("Erro:", error));
  });

  // Formatar data de nascimento
function formatarDataNascimento(dataNascimento) {
    const options = { day: 'numeric', month: 'numeric', year: 'numeric', timeZone: 'UTC' };
    return new Intl.DateTimeFormat('pt-BR', options).format(new Date(dataNascimento));
}