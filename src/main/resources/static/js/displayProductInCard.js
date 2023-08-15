function display() {
  fetch("http://localhost:8080/listAllProducts", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        return data;
    })
    .catch((error) => {
        console.log('Error:', error);
    });

const productList = display();

function addItemCard(productItem){
    const itemHTML = '<div class="card" style="width: 20rem;">\n' +
        '    <img src="'+productItem.imageURL +'" width="300" height="250"  alt="product image">\n' +
        '    <div class="card-body">\n' +
        '        <h5 class="card-title">'+productItem.name+'</h5>\n' +
        '        <p class="card-text">'+productItem.description+'</p>\n' +
        '        <a href="#" class="btn btn-primary">Add</a>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<br/>';
    const itemsContainer = document.getElementById("list-items");
    itemsContainer.innerHTML += itemHTML;
}

function loadCardsListFromItemsController(){
    for(var i = 0, size = productList.length; i < size ; i++){
        const item = productList[i];
        addItemCard(item);
    }
}

loadCardsListFromItemsController();