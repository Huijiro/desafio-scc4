import './style.css'


// Constantes

const SERVER_ADDRESS = 'http://localhost:8080';

const form = document.querySelector('#input') as HTMLFormElement;
const tableBody = document.querySelector('#data-body') as HTMLTableSectionElement;

// Interfaces

interface Cliente {
    id?: number;
    nome: string;
    email: string;
    telefone: string;
    profissao: string;
}

interface FormElements extends HTMLFormControlsCollection {
    nome: HTMLInputElement;
    email: HTMLInputElement;
    telefone: HTMLInputElement;
    profissao: HTMLInputElement;
}

// Funções que afetam a tabela.

async function loadTable() {
  console.log('Carregando tabela...');
  
    while(tableBody.firstChild) {
        tableBody.firstChild.remove();
    }
    const clientes = await getClientes();
    clientes.forEach(cliente => {
        const tableRow = addTableRow(cliente);
        tableBody.appendChild(tableRow);
    })

}

loadTable();

form.addEventListener('submit', async (e: SubmitEvent) => {
  e.preventDefault();
  const form = e.target as HTMLFormElement & { elements: FormElements };
  const nome = form.elements['nome'].value;
  const email = form.elements['email'].value;
  const telefone = form.elements['telefone'].value;
  const profissao = form.elements['profissao'].value;

  const cliente: Cliente = {
      nome,
      email,
      telefone,
      profissao
  }

  await postCliente(cliente);

  await loadTable();
})

function addTableRow(cliente: Cliente): HTMLTableRowElement {
    const tableRow = document.createElement('tr');
    const tableDataId = document.createElement('td');
    const tableDataNome = document.createElement('td');
    const tableDataEmail = document.createElement('td');
    const tableDataTelefone = document.createElement('td');
    const tableDataProfissao = document.createElement('td');
    const tableDataAcoes = document.createElement('td');

    tableRow.appendChild(tableDataId);
    tableRow.appendChild(tableDataNome);
    tableRow.appendChild(tableDataEmail);
    tableRow.appendChild(tableDataTelefone);
    tableRow.appendChild(tableDataProfissao);
    tableRow.appendChild(tableDataAcoes);

    tableDataId.textContent = cliente.id!.toString();
    tableDataNome.textContent = cliente.nome;
    tableDataEmail.textContent = cliente.email;
    tableDataTelefone.textContent = cliente.telefone;
    tableDataProfissao.textContent = cliente.profissao;

    const button = document.createElement('button');

    button.textContent = 'Excluir';
    button.className = 'button-excluir';

    button.addEventListener('click', async () => {
        await deleteCliente(cliente.id!);
    })

    tableDataAcoes.appendChild(button);
    
    return tableRow;
}

// Funções que usam o servidor.

async function getClientes(): Promise<Cliente[]> {
    const response = await fetch(`${SERVER_ADDRESS}/cliente`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const data = await response.json();
    return data;
}

async function postCliente(cliente: Cliente): Promise<Cliente> {
    const response = await fetch(`${SERVER_ADDRESS}/cliente`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cliente)
    });
    const data = await response.json();
    return data;
}

async function deleteCliente(id: number): Promise<Cliente> {
    const response = await fetch(`${SERVER_ADDRESS}/cliente/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const data = await response.json();
    return data;
}