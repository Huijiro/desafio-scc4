import './style.css'

const form = document.querySelector('#input') as HTMLFormElement;
const tableBody = document.querySelector('#data-body') as HTMLTableSectionElement;

interface FormElements extends HTMLFormControlsCollection {
    nome: HTMLInputElement;
    email: HTMLInputElement;
    telefone: HTMLInputElement;
    profissao: HTMLInputElement;
}

form.addEventListener('submit', (e: SubmitEvent) => {
  e.preventDefault();
  const form = e.target as HTMLFormElement & { elements: FormElements };
  const nome = form.elements['nome'].value;
  const email = form.elements['email'].value;
  const telefone = form.elements['telefone'].value;
  const profissao = form.elements['profissao'].value;

  const data = {
      nome,
      email,
      telefone,
      profissao
  }

  const tableRow = document.createElement('tr');
  const tableDataId = document.createElement('td');
  const tableDataNome = document.createElement('td');
  const tableDataEmail = document.createElement('td');
  const tableDataTelefone = document.createElement('td');
  const tableDataProfissao = document.createElement('td');
  const tableDataAcoes = document.createElement('td');


  tableDataId.textContent = '1';
  tableDataNome.textContent = data.nome;
  tableDataEmail.textContent = data.email;
  tableDataTelefone.textContent = data.telefone;
  tableDataProfissao.textContent = data.profissao;
  tableDataAcoes.innerHTML = "Excluir";

  tableRow.appendChild(tableDataId);
  tableRow.appendChild(tableDataNome);
  tableRow.appendChild(tableDataEmail);
  tableRow.appendChild(tableDataTelefone);
  tableRow.appendChild(tableDataProfissao);
  tableRow.appendChild(tableDataAcoes);

  tableBody.appendChild(tableRow);
})
