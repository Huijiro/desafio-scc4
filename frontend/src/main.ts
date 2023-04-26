import "./style.css";

// Constantes

const SERVER_ADDRESS = "http://localhost:8080";

const form = document.querySelector("#input") as HTMLFormElement;
const tableBody = document.querySelector(
  "#data-body"
) as HTMLTableSectionElement;

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

// Inicialização

loadTable();

// Funções que afetam a tabela.

async function loadTable() {
  console.log("Carregando tabela...");
  const clientes = await getClientes();

  while (tableBody.firstChild) {
    tableBody.firstChild.remove();
  }
  if (clientes) {
    clientes.forEach((cliente) => {
      const tableRow = addTableRow(cliente);
      tableBody.appendChild(tableRow);
    });
  }
}

form.addEventListener("submit", async (e: SubmitEvent) => {
  e.preventDefault();
  await buttonCriar(form as HTMLFormElement & { elements: FormElements });
  await loadTable();
});

function addTableRow(cliente: Cliente): HTMLTableRowElement {
  const tableRow = document.createElement("tr");
  const tableDataId = document.createElement("td");
  const tableDataNome = document.createElement("td");
  const tableDataEmail = document.createElement("td");
  const tableDataTelefone = document.createElement("td");
  const tableDataProfissao = document.createElement("td");
  const tableDataAcoes = document.createElement("td");

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

  const button = document.createElement("button");

  button.textContent = "Excluir";
  button.className = "button-excluir";

  button.addEventListener("click", async () => {
    await buttonExcluir(cliente.id!);
    await loadTable();
  });

  tableDataAcoes.appendChild(button);

  return tableRow;
}

// Funções dos botões.

const buttonExcluir = async (id: number) => {
  await deleteCliente(id);
};

const buttonCriar = async (
  form: HTMLFormElement & { elements: FormElements }
) => {
  const nome = form.elements["nome"].value;
  const email = form.elements["email"].value;
  const telefone = form.elements["telefone"].value;
  const profissao = form.elements["profissao"].value;

  const cliente: Cliente = {
    nome,
    email,
    telefone,
    profissao,
  };

  await postCliente(cliente);
};

// Funções que usam o servidor.

async function getClientes(): Promise<Cliente[] | undefined> {
  try {
    const response = await fetch(`${SERVER_ADDRESS}/cliente`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const data = await response.json();

    if (!response.ok) throw new Error(data.message);

    return data;
  } catch (error) {
    if (error instanceof Error) alert(error.message);
  }
}

async function postCliente(cliente: Cliente): Promise<void> {
  try {
    const response = await fetch(`${SERVER_ADDRESS}/cliente`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cliente),
    });
    const data = await response.json();

    if (!response.ok) throw new Error(data.message);

    alert(data.message);
  } catch (error) {
    if (error instanceof Error) alert(error.message);
  }
}

async function deleteCliente(id: number): Promise<void> {
  try {
    const response = await fetch(`${SERVER_ADDRESS}/cliente/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const data = await response.json();

    if (!response.ok) throw new Error(data.message);

    alert(data.message);
  } catch (error) {
    if (error instanceof Error) alert(error.message);
  }
}
