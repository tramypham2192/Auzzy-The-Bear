let id = (id) => document.getElementById(id);

let productId = id("productId"),
    deleteForm = id("delete-form")
const productIdvalue = parseInt(productId.value);

deleteForm.addEventListener("submit", (e) => {
    e.preventDefault();
    deleteProduct(productId.value);
    alert("Delete product with ID " + productId.value + " successfully");
    window.location.href = "./productList"; });

function deleteProduct(productId) {
  const data = {id: productId};
  console.log("product id to be delete: " + productId);
  fetch(`http://localhost:8080/delete/${productId}`, {
        method:'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(data => {
        console.log('Successfully delete:', data);
    })
    .catch((error) => {
        console.log('Error:', error);
    });
}