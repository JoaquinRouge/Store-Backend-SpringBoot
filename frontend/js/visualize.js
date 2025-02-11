const modifiable = document.getElementById("modifiable-section");
const urlParams = new URLSearchParams(window.location.search);
const action = urlParams.get("action");
const entity = urlParams.get("entity");

fetch("http://localhost:8080/clients/all")
  .then((response) => response.json()) // Convierte la respuesta a JSON
  .then((data) => {
    if (action === "visualize" && entity === "client") {
      let tableContainer = document.createElement("div");

      let table = document.createElement("table");
      table.innerHTML = `
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>DNI</th>
          </tr>
        </thead>
        <tbody id="table-body"></tbody>
      `;

      tableContainer.appendChild(table);
      modifiable.appendChild(tableContainer);

      const tableBody = document.getElementById("table-body");

      function updateTable(filteredData) {
        tableBody.innerHTML = "";
        filteredData.forEach((client) => {
          let row = document.createElement("tr");
          row.innerHTML = `
            <td>${client.clientId}</td>
            <td>${client.nameSurname}</td>
            <td>${client.dni}</td>
          `;
          tableBody.appendChild(row);
        });
      }

      // Mostrar todos los clientes al inicio
      updateTable(data);

      const searchBar = document.getElementById("search-bar");

      searchBar.addEventListener("input", function () {
        let searchText = searchBar.value.toLowerCase().trim();
        let filteredClients = data.filter((client) =>
          client.nameSurname.toLowerCase().includes(searchText)
        );

        updateTable(filteredClients);
      });
    }
  })
  .catch((error) => {
    console.error("Error:", error);
  });

