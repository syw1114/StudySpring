const plus = document.querySelector(".plus-btn");
const minus = document.querySelector(".minus-btn");
const qty = document.querySelector(".quantity-input")
const bprice = document.querySelector(".bag-price")

let price = 66000

plus.onclick = () => {
    ++qty.value
    bprice.innerHTML = `₩${(price*qty.value).toLocaleString()}`;
}

minus.onclick = () => {
    if(qty.value>0) {
    --qty.value
    bprice.innerHTML = `₩${(price*qty.value).toLocaleString()}`;
    }
}

