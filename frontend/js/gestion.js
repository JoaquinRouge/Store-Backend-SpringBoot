const modifiable = document.getElementById("modifiable-section");
const urlParams = new URLSearchParams(window.location.search);
const action = urlParams.get("action");
const entity = urlParams.get("entity");

if (action == "create") {
    if (entity == "client") {
        modifiable.innerHTML += 
            `
            <div class = "create-form">
                <form id="clienteForm">
                    <label for="nameSurname">Nombre y Apellido:</label>
                    <input type="text" id="nameSurname" required><br>

                    <label for="dni">DNI:</label>
                    <input type="number" id="dni" required><br>

                    <button type="button" onclick="createClient()">Crear Cliente</button>
                </form>
            </div>
            `
    }
}

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
