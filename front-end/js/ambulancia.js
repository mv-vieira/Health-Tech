function removePlaceHolder(input) {
    input.placeholder = "";
}
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('search-button').addEventListener('click', procurarPlaca);
});

function procurarPlaca() {
    let placa = document.getElementById("placaInput").value;
    const tableBody = document.getElementById('table-body');
    const tableTitle = document.getElementById('table-title');
    tableBody.innerHTML = '';
    const ApiUrl = `http://localhost:8080/health-tech/ambulancias/buscarplaca?placa=${placa}`;

    fetch(ApiUrl)
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status}`);
        }
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            return response.json();
        } else {
            throw new TypeError('A resposta não é JSON válido');
        }
    })
    .then(data => {
        console.log(data);
        tableTitle.innerHTML = ''
        tableTitle.innerHTML = `Ocorrências da Ambulância - Placa ${data.placaAmbulancia}`;

        // Faz uma cópia do array e ordena
        const sortedOcorrencias = [...data.ocorrencias].sort((a, b) => new Date(b.dataHora) - new Date(a.dataHora));

        // Usar um conjunto para evitar duplicatas
        const uniqueRows = new Set();

        // Preenche a tabela com os dados ordenados da API
        sortedOcorrencias.forEach(ocorrencia => {
            const rowKey = `${ocorrencia.protocolo}-${ocorrencia.paciente.nome}`;
            if (!uniqueRows.has(rowKey)) {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${ocorrencia.protocolo}</td>
                    <td>${ocorrencia.paciente.nome}</td>
                    <td>${formatarDataNascimento(ocorrencia.paciente.dataNascimento)}</td>
                    <td>${ocorrencia.endereco}</td>
                    <td>${formatarDataHora(ocorrencia.dataHora)}</td>
                    <td>${ocorrencia.hospital.nomeHospital + ", " + ocorrencia.hospital.municipio}</td>
                `;

                tableBody.appendChild(row);

                // Adiciona a chave ao conjunto para evitar duplicatas
                uniqueRows.add(rowKey);
            }
        });

        console.log('Finalizando procurarPlaca...');
    })
    .catch(error => console.error('Erro na requisição:', error));
}


// Função para formatar a data e hora para o padrão brasileiro
function formatarDataHora(dataHora) {
    const options = { day: 'numeric', month: 'numeric', year: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric', timeZone: 'America/Sao_Paulo'};
    return new Intl.DateTimeFormat('pt-BR', options).format(new Date(dataHora));
}

// Formatar data de nascimento
function formatarDataNascimento(dataNascimento) {
    const options = { day: 'numeric', month: 'numeric', year: 'numeric'};
    return new Intl.DateTimeFormat('pt-BR', options).format(new Date(dataNascimento));
}