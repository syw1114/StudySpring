const pName = document.querySelector("h2");

let prodList = new Array();

function sizeValue() {
    const allPrice = document.querySelector("#all-price");

    let product = {
        prodName : pName
        
    } 
    prodList.push(product);

    allPrice.innerHTML = "";

    allPrice.innerHTML += `
        <td>${product.prodName}</td>
        <td>    
        <input id="count-box" type="number" value="1" min="0">
        </td>
        <td></td>
        <td colspan="3"><hr></td>
    
    `
}