// Função para formatar a data no formato desejado
function formatarData(data) {
  const dataObj = new Date(data);
  const options = { year: "numeric", month: "2-digit", day: "2-digit" };
  return dataObj.toLocaleDateString("pt-BR", options);
}

function formatarDataHora(dataHora) {
  const options = { day: 'numeric', month: 'numeric', year: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric', timeZone: 'America/Sao_Paulo'};
  return new Intl.DateTimeFormat('pt-BR', options).format(new Date(dataHora));
}

function tracarRota() {
  // Obter os valores dos campos de endereço
  var enderecoOcorrencia = encodeURIComponent(document.getElementById("enderecoOcorrencia").textContent);
  var enderecoHospital = encodeURIComponent(document.getElementById("enderecoHospital").textContent);

  // Construir a URL da API de Direções do Google Maps
  var url = 'https://www.google.com/maps/dir/?api=1&origin=' + enderecoOcorrencia + '&destination=' + enderecoHospital;

  // Redirecionar para o Google Maps
  window.location.href = url;
}

// Função para buscar os dados da API e atualizar o HTML
async function carregarDados() {
  try {
    const response = await fetch(
      "http://localhost:8080/health-tech/ocorrencia/listar-ocorrencias?page=0&size=999"
    );
    const data = await response.json();

    const ocorrencia = data.content[data.content.length - 1];

    const { paciente, hospital, ambulancia, protocolo, dataHora, descricao, endereco } =
      ocorrencia;

    const html = `
            <h1>Detalhes Ocorrência</h1>
            <div class="info-ocorrencia">
                <h2>Protocolo</h2>
                <p>${protocolo}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Nome do Paciente</h2>
                <p>${paciente.nome}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Data Nascimento</h2>
                <p>${formatarData(paciente.dataNascimento)}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Data e Hora</h2>
                <p>${formatarDataHora(dataHora)}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Endereço</h2>
                <p id="enderecoOcorrencia">${endereco}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Ambulância vinculada</h2>
                <p>${ambulancia.placaAmbulancia} Tipo: ${ambulancia.tipoAmbulancia}</p>
            </div>

            <div class="info-ocorrencia" >
                <h2>Hospital Encaminhado</h2>
                <p id="enderecoHospital">${hospital.nomeHospital} - ${hospital.endereco} - ${hospital.municipio}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Descrição</h2>
                <p>${descricao}</p>
            </div>

            <button onclick="tracarRota()" target="_blank">Traçar Rota</button>
        `;

    document.getElementById("occurrence-details").innerHTML = html;
  } catch (error) {
    console.error("Erro ao carregar dados:", error);
  }
}

window.onload = carregarDados;
