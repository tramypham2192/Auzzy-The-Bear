let id = (id) => document.getElementById(id);

let productId = id("productId"),
    productname = id("productName"),
    price = id("price"),
    description = id("productDescription"),
    image = id("image"),
    editform = id("edit-form")
const productIdvalue = parseInt(productId.value);

editform.addEventListener("submit", (e) => {
  e.preventDefault();
   console.log("hit submit button edit product form");
   edit(productId.value, productname.value, price.value, description.value, image);
   console.log(productId.value, productname.value, price.value, description.value, image.files[0]);
   alert("Edit product with ID " + productId.value + " , name: " + productName.value + " successfully");
//   window.location.href = "./productList"; });
})

function edit(productId, productname, productprice, productdescription, image) {
    var productObj = new Blob([JSON.stringify({ id: productId, name: productname, price: productprice, description: productdescription })], {
      type: "application/json"
    });
  const formData = new FormData();
//  formData.append(id, productId);
//  formData.append(name, productname);
//  formData.append(price, productprice);
//  formData.append(description, productdescription);
  formData.append("product", productObj)
  formData.append("image", image.files[0]);

//  const data = {id: productId, name : productname, price: productprice, description: productdescription, image: productImageURL};
//  fetch(`http://localhost:8080/update/${productId}`, {

    fetch(`http://localhost:8080/product/editProduct`, {
        method:'POST',
        headers: {
//            'Content-Type': 'multipart/form-data',
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        console.log('Successfully edit:', data);
    })
    .catch((error) => {
        console.log('Error:', error);
    });



}



