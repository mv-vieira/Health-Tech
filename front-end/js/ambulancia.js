document.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.getElementById('table-body');
    const tableTitle = document.getElementById('table-title');
    const ApiUrl = 'http://localhost:8080/health-tech/ambulancias/buscarplaca?placa=KGT-0357'

    // Realiza a requisição à API
    fetch(ApiUrl)
        .then(response => response.json())
        .then(data => {
            // Limpa o corpo da tabela
            tableBody.innerHTML = '';
            tableTitle.innerHTML = `Ocorrências da Ambulância - Placa ${data.placaAmbulancia}`;


            // Preenche a tabela com os dados da API
            data.ocorrencias.forEach(ocorrencia => {
                const row = document.createElement('tr');
                

                row.innerHTML = `
                    <td>${ocorrencia.protocolo}</td>
                    <td>${ocorrencia.paciente.nome}</td>
                    <td>${formatarDataNascimento(ocorrencia.paciente.dataNascimento)}</td>
                    <td>${ocorrencia.endereco}</td>
                    <td>${formatarDataHora(ocorrencia.dataHora)}</td>
                    <td>${ocorrencia.hospital.nomeHospital + ", "+ ocorrencia.hospital.municipio}</td>
                `;

                tableBody.appendChild(row);
            });
            
        })
        .catch(error => console.error('Erro na requisição:', error));
});

// Função para formatar a data e hora para o padrão brasileiro
function formatarDataHora(dataHora) {
    const options = { day: 'numeric', month: 'numeric', year: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric', timeZone: 'UTC' };
    return new Intl.DateTimeFormat('pt-BR', options).format(new Date(dataHora));
}

// Formatar data de nascimento
function formatarDataNascimento(dataNascimento) {
    const options = { day: 'numeric', month: 'numeric', year: 'numeric', timeZone: 'UTC' };
    return new Intl.DateTimeFormat('pt-BR', options).format(new Date(dataNascimento));
}