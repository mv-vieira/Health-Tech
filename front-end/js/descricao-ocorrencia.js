// Função para formatar a data no formato desejado
function formatarData(data) {
  const dataObj = new Date(data);
  const options = { year: "numeric", month: "2-digit", day: "2-digit" };
  return dataObj.toLocaleDateString("pt-BR", options);
}

// Função para buscar os dados da API e atualizar o HTML
async function carregarDados() {
  try {
    const response = await fetch(
      "http://localhost:8080/health-tech/ocorrencia/listar-ocorrencias?page=0&size=999"
    );
    const data = await response.json();
    // Acesse o último item em "content"
    const ocorrencia = data.content[data.content.length - 1];

    const { paciente, hospital, ambulancia, protocolo, dataHora, endereco } =
      ocorrencia;

    // Crie o HTML com os dados
    const html = `
            <div class="info-ocorrencia">
                <h2>Protocolo</h2>
                <p>${protocolo}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Endereço da Ocorrência</h2>
                <p>${endereco}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Nome Paciente</h2>
                <p>${paciente.nome}</p>
            </div>
            <div class="info-ocorrencia">
                <h2>Data Nascimento</h2>
                <p>${formatarData(paciente.dataNascimento)}</p>
            </div>
           
            <div class="info-ocorrencia">
                <h2>Ambulância vinculada</h2>
                <p>${ambulancia.placaAmbulancia} Tipo: ${ambulancia.tipoAmbulancia}</p>
            </div>

            <div class="info-ocorrencia">
                <h2>Hospital Encaminhado</h2>
                <p>${hospital.nomeHospital}</p>
            </div>

            <button>Home</button>
        `;

    // Atualize o conteúdo dentro do elemento com id "occurrence-details"
    document.getElementById("occurrence-details").innerHTML = html;
  } catch (error) {
    console.error("Erro ao carregar dados:", error);
  }
}

// Chame a função para carregar os dados ao carregar a página
window.onload = carregarDados;
