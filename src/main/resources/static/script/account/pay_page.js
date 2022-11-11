const registerButton = document.querySelector(".address-button");

registerButton.onclick = () => {
    const postcodeInput = document.querySelector(".postcode").value;
    const addressInput = document.querySelector(".address").value;
    const detailAddressInput = document.querySelector(".detailAddress").value;

    $.ajax({
        async: false,
        type : "put",
        url : "/api/account/address",
        dataType : "json",
        contentType: "application/json",
        data: JSON.stringify({
            userId: getPrincipal().id,
            address1: postcodeInput,
            address2: addressInput,
            address3: detailAddressInput
        }),
        success : (response) => {
           alert("주소저장에 성공하였습니다.")
        },
        error : (error) => {
            console.log(error);
        }


    });

}

document.querySelector(".sameaddr").onclick = () => {
    const orderName = document.querySelector(".customer");
    const orderName1 =  document.querySelector(".customer1");
    const orderNum = document.querySelector(".phone-number");
    const orderNum1 = document.querySelector(".phone-number1");
    const orderPostcode = document.querySelector(".postcode"); 
    const orderPostcode1 = document.querySelector(".postcode1"); 
    const orderaddress = document.querySelector(".address");
    const orderaddress1 = document.querySelector(".address1");
    const orderdtlAddress = document.querySelector(".detailAddress");
    const orderdtlAddress1 = document.querySelector(".detailAddress1");

    orderName1.value = orderName.value;
    orderNum1.value = orderNum.value;
    orderPostcode1.value = orderPostcode.value;
    orderaddress1.value = orderaddress.value;
    orderdtlAddress1.value = orderdtlAddress.value;

document.querySelector(".sameaddr1").onclick = () => {
    const orderName1 =  document.querySelector(".customer1");
    const orderNum1 = document.querySelector(".phone-number1");
    const orderPostcode1 = document.querySelector(".postcode1");
    const orderaddress1 = document.querySelector(".address1");
    const orderdtlAddress1 = document.querySelector(".detailAddress1");

    orderName1.value = "";
    orderNum1.value = "";
    orderPostcode1.value = "";
    orderaddress1.value = "";
    orderdtlAddress1.value = "";

}

}



class ImportApi {
    #IMP = null;

    constructor () {
        this.#IMP = window.IMP;
        this.#IMP.init("imp24504212");
        this.addPaymentEvent();

    }

    addPaymentEvent() {
        document.querySelector(".payment-button").onclick = () => {
            this.requestPay();
            
        }
    }

    requestPay(){
        const pdtName = document.querySelector(".product-name").textContent;
        const pdtPrice = document.querySelector(".product-price").textContent;
        const name = document.querySelector(".customer").value;
        const zoneCode = document.querySelector(".postcode").value;
        const addressAll = document.querySelector(".address").value;
        const addressDetail = document.querySelector(".detailAddress").value;
        const address = addressAll + " " + addressDetail;
        const phone = document.querySelector(".phone-number").value;

             // IMP.request_pay(param, callback) 결제창 호출
             IMP.request_pay({ // param
                pg: "kakaopay",
                pay_method: "card",
                merchant_uid: "PRODUCT-" + new Date().getTime(),
                name: pdtName,
                amount: pdtPrice,
                buyer_name: name,
                buyer_tel: phone,
                buyer_addr: address,
                buyer_postcode: zoneCode
            }, function (rsp) { // callback
                if (rsp.success) {
                    
                } else {
    
                }
    
            });
   
        }

    }   

window.onload = () => {
    new ImportApi();

}

