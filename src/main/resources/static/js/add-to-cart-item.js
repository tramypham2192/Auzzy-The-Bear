let productQuantity = 0;
    let productID = 0;
    let id = (id) => document.getElementById(id);
    let productQuantityElement = id(id)
        productname = id("productName"),
        addToCartButton = id("addToCartButton");

    //STEP 1: Get Product Quantity

    //decreaseProductQuantity
<!--    function decreaseProductQuantity(productID){-->
    function decreaseProductQuantity(e){
        productID = e.dataset.param1;
        productQuantity = e.dataset.param2;
        console.log("type of id " + typeof productID);
        console.log("type of quantity " + typeof productQuantity);
        productQuantityElementValue = parseInt(document.getElementById(productID).innerHTML);
        productQuantityElementValue -= 1;
        productQuantity = productQuantityElementValue;
        document.getElementById(productID).innerHTML = productQuantityElementValue;
        console.log("product id: " + productID + ", product quantity: " + productQuantity);
     }


     //increaseProductQuantity
<!--        function increaseProductQuantity(productID){-->
       function increaseProductQuantity(e){
            productID = e.dataset.param1;
            productQuantity = e.dataset.param2;
            console.log("type of id " + typeof productID);
            console.log("type of quantity " + typeof productQuantity);

            productQuantityElementValue = parseInt(document.getElementById(productID).innerHTML);
            productQuantityElementValue += 1;
            productQuantity = productQuantityElementValue;
            document.getElementById(productID).innerHTML = productQuantityElementValue;
            console.log("product id: " + productID + ", product quantity: " + productQuantity);
     }

     //STEP 2: Write fetch method to send object to item controller
    async function addToCartItem(product_ID, product_Quantity) {
    const data = { productID: product_ID, productQuantity: product_Quantity };
    try {
      const response = await fetch("http://localhost:8080/add-to-cart-item", {
        method: "POST",
        headers: {
          'Content-Type': 'application/json; charset=utf-8',
        },
        body: JSON.stringify(data)
      });
    const responseData = await response.json();
    console.log(responseData); // Logging the response data

    return responseData; // Return the response data
  } catch (error) {
    console.log('Error:', error);
  }
}

     //STEP 3: Add to Cart Button on click, call fetch method

     //when click on Add to Cart button, number of this item added increase by 1
      addToCartButtononclick = () => {
          console.log("clicked add to cart button");
          if(productID && productQuantity){
            console.log(" typeof productID: " + typeof productID + " typeof productQuantity: " + typeof productQuantity);
            addToCartItem(productID, productQuantity);
            console.log("Added cart item with ID " + productID + " successfully");
          }
      }

