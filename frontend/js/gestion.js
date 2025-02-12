const modifiable = document.getElementById("modifiable-section");
const urlParams = new URLSearchParams(window.location.search);
const action = urlParams.get("action");
const entity = urlParams.get("entity");

let clientsDB = []

fetch("http://localhost:8080/clients/all")
  .then((response) => response.json()) // Convierte la respuesta a JSON
  .then((data) => {
      clientsDB = data;
      console.log("Clientes cargados:", clientsDB); // Verificar que los clientes están llegando correctamente
    // Llamar a la función que genera el select después de cargar los datos
    if (action === "edit" && entity === "client") {
      generateClientSelect(); // Solo generar el select en "edit"
    }
    if (action === "delete" && entity === "client") {
      generateClientSelectForDelete(); // Solo generar el select en "delete"
    }
  })
  .catch((error) => {
    console.error("Error:", error);
  });

// Generar el select para editar cliente
function generateClientSelect() {
  const select = document.createElement("select");
  select.id = "select";
  select.required = true;

  // Agregar una opción por defecto
  const defaultOption = document.createElement("option");
  defaultOption.value = "";
  defaultOption.textContent = "Seleccionar Cliente";
  select.appendChild(defaultOption);

  // Agregar las opciones con los clientes
  clientsDB.forEach(client => {
    const option = document.createElement("option");
    option.value = client.clientId; // Asumiendo que el cliente tiene un campo 'id'
    option.textContent = client.nameSurname; // Mostrar nombre y apellido
    select.appendChild(option);
  });

  // Insertar el select en el formulario
  const form = document.getElementById("clienteForm");
  form.insertBefore(select, form.querySelector("button"));
}

// Generar el select para eliminar cliente
function generateClientSelectForDelete() {
  const select = document.createElement("select");
  select.id = "select";
  select.required = true;

  // Agregar una opción por defecto
  const defaultOption = document.createElement("option");
  defaultOption.value = "";
  defaultOption.textContent = "Seleccionar Cliente";
  select.appendChild(defaultOption);

  // Agregar las opciones con los clientes
  clientsDB.forEach(client => {
    const option = document.createElement("option");
    option.value = client.clientId; // Asumiendo que el cliente tiene un campo 'id'
    option.textContent = client.nameSurname; // Mostrar nombre y apellido
    select.appendChild(option);
  });

  // Insertar el select en el formulario
  const form = document.getElementById("clienteForm");
  form.insertBefore(select, form.querySelector("button"));

  // Agregar el botón de eliminar
  const deleteButton = document.createElement("button");
  deleteButton.type = "button";
  deleteButton.textContent = "Eliminar Cliente";
  deleteButton.onclick = deleteClient;
  form.appendChild(deleteButton);
}

// Lógica para crear cliente
async function createClient() {
  const nameSurname = document.getElementById('nameSurname').value.trim();
  const dni = document.getElementById('dni').value.trim();

  // Validación de campos vacíos
  if (!nameSurname || !dni) {  
    alert("Por favor, completa todos los campos.");
    return; // Detiene la ejecución
  }

  const client = {
    nameSurname: nameSurname,
    dni: parseInt(dni)
  };

  try {
    const response = await fetch('http://localhost:8080/clients/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(client)
    });

    if (response.ok) {
      alert("Cliente creado con éxito");
      window.location.href = "index.html"; // Redirección
    } else {
      alert('Error al crear cliente');
    }
  } catch (error) {
    console.error('Error:', error);
    alert('Error de conexión con la API');
  }
}

// Lógica para editar cliente
async function editClient() {
  const nameSurname = document.getElementById('nameSurname').value.trim();
  const dni = document.getElementById('dni').value.trim();
  const select = document.getElementById('select').value;

  // Validación de campos vacíos
  if (!nameSurname || !dni || !select) {  
    alert("Por favor, completa todos los campos.");
    return; // Detiene la ejecución
  }

  const client = {
    clientId: select,
    nameSurname: nameSurname,
    dni: parseInt(dni)
  };

  try {
    const response = await fetch('http://localhost:8080/clients/edit', {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(client)
    });

    if (response.ok) {
      alert("Cliente editado con éxito");
      window.location.href = "index.html"; // Redirección
    } else {
      alert('Error al editar cliente');
    }
  } catch (error) {
    console.error('Error:', error);
    alert('Error de conexión con la API');
  }
}

// Lógica para eliminar cliente
async function deleteClient() {
  const id = document.getElementById('select').value;

  // Validación de selección
  if (!select) {  
    alert("Por favor, selecciona un cliente para eliminar.");
    return; // Detiene la ejecución
  }

    console.log(id)
    
  try {
    const response = await fetch(`http://localhost:8080/clients/delete/${id}`, {
      method: 'DELETE',
    });

    if (response.ok) {
      alert("Cliente eliminado con éxito");
      window.location.href = "index.html"; // Redirección
    } else {
      alert('Error al eliminar cliente');
    }
  } catch (error) {
    console.error('Error:', error);
    alert('Error de conexión con la API');
  }
}

// Condicional para renderizar los formularios según el action y entity
if (action == "create") {
  if (entity == "client") {
    modifiable.innerHTML += 
      `
      <div class="create-form">
        <form id="clienteForm">
          <label for="nameSurname">Nombre y Apellido:</label>
          <input type="text" id="nameSurname" required><br>

          <label for="dni">DNI:</label>
          <input type="number" id="dni" required><br>

          <button type="button" onclick="createClient()">Crear Cliente</button>
        </form>
      </div>
      `;
  }
}

if (action == "edit") {
  if (entity == "client") {
    modifiable.innerHTML += 
      `
      <div class="create-form">
        <form id="clienteForm">
          <label for="nameSurname">Nombre y Apellido:</label>
          <input type="text" id="nameSurname" required><br>

          <label for="dni">DNI:</label>
          <input type="number" id="dni" required><br>

          <!-- Aquí se añadirá el select con los clientes -->
          
          <button type="button" onclick="editClient()">Editar Cliente</button>
        </form>
      </div>
      `;
  }
}

if (action == "delete") {
  if (entity == "client") {
    modifiable.innerHTML += 
      `
      <div class="create-form">
        <form id="clienteForm">
          <label for="select">Seleccionar Cliente:</label>
          <!-- Aquí se añadirá el select con los clientes -->
        </form>
      </div>
      `;
  }
}

