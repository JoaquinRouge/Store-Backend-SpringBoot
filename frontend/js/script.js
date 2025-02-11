const notificationPanel = document.getElementById("notifications")

fetch('http://localhost:8080/products/lowstock')
  .then(response => response.json())  // Convierte la respuesta a JSON
  .then(data => {
      
      data.forEach(function (product) {
          
          console.log(data)
          
          notificationPanel.innerHTML +=
              
              `
              <div class="notification">
                <p>
                <i class="bi bi-bell-fill icon noti"></i>Falta de stock en el producto ${product.name} ${product.brand} 
                </p>
              </div>
              `
          
      })
    
  })
  .catch(error => {
    console.error('Error:', error);
  });
