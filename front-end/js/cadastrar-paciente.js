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

// Formatar CPF 

document.addEventListener("DOMContentLoaded", function () {
  const cpfInput = document.getElementById("cpf");

  cpfInput.addEventListener("input", function (event) {
      let value = event.target.value;
      // Remove tudo que não é número
      value = value.replace(/\D/g, "");

      // Adiciona os pontos e traço no formato do CPF
      if (value.length <= 11) {
          value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
      } else {
          // Se for maior que 11 dígitos, limita o tamanho
          value = value.substring(0, 11);
          value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
      }

      // Atualiza o valor no campo
      event.target.value = value;
  });
});

// Método Post para envio de formulário no endpoint de pacientes

document.addEventListener("DOMContentLoaded", function () {
  document
    .getElementById("cadastroForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();

      const nomeElement = document.getElementById("nome");
      const dataNascimentoElement = document.getElementById("dataNascimento");
      const cpfElement = document.getElementById("cpf");
      const ambulanciaElement = document.getElementById("ambulancia");

      const nome = nomeElement.value;
      const dataNascimento = dataNascimentoElement.value;
      const cpf = cpfElement.value;
      const ambulancia = ambulanciaElement.value;

      const UrlApi =
        "http://localhost:8080/health-tech/paciente/?idAmbulancia=" +
        ambulancia;

      const data = {
        nome,
        dataNascimento,
        cpf,
      };

      fetch(UrlApi, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(data),
      })
        .then(async (response) => {
          if (!response.ok) {
            throw new Error(
              `Erro na solicitação: ${response.status} - ${response.statusText}`
            );
          }
          const responseBody = await response.text(); // Lê o corpo da resposta como texto
          try {
            const result = JSON.parse(responseBody);
            console.log("Sucesso:", result);
            // Faça o que for necessário com o JSON retornado, se aplicável
          } catch (error) {
            console.log("Sucesso:", responseBody);
            // Limpar os campos, pois a resposta não é um JSON
            nomeElement.value = "";
            dataNascimentoElement.value = "";
            cpfElement.value = "";
            ambulanciaElement.value = "";
            window.location.href = "gerar-ocorrencia.html";
          }
        })
        .catch((error) => {
          console.error("Erro na resposta da API:", error.message);
        });
    });
});
