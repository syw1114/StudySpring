class CommonApi {
  static #instance = null;

  static getInstance() {
      if(this.#instance == null) {
          this.#instance = new CommonApi();
      }
      return this.#instance;
  }

 
  
  getProductMstList() {
      let responseData = null;
      const url = location.href;
      const pdtId = url.substring(url.lastIndexOf("/") + 1);

      $.ajax({
          async: false,
          type: "get",
          url: "/api/product/" + pdtId,
          dataType: "json",
          success: (response) => {
              responseData = response.data;
          },
          error: (error) => {
              console.log(error);
          }
      });
      return responseData;
  }
}

class Option {

  constructor() {
      this.getProductPdtId();
  }

  getProductPdtId(){
      const responseData = CommonApi.getInstance().getProductMstList();
      this.setSizeSelectOptions(responseData);
      this.setProductPdtName(responseData);
      this.getProductPrice(responseData);
      this.loadProductImgs(responseData);
      console.log(responseData);

  }
  
  setSizeSelectOptions(responseData) {
      const pdtDtlSizeSelect = document.querySelector(".size-select");

      pdtDtlSizeSelect.innerHTML = "";
      responseData.pdtSizes.forEach(value => {
          pdtDtlSizeSelect.innerHTML += `
              <option value="${value.pdtDtlId}">${value.sizeName}</option>
          `;
      });
    }
      loadProductImgs(responseData) {
        const productImages = document.querySelector(".image");
        const productDtlImg = document.querySelector("#detail-img");
        productImages.innerHTML = ``;
  
        responseData.pdtImgs.forEach((img,index) => {
          if(index == 0)
            productImages.innerHTML += `
                <div class="image">
                    <img src="/static/upload/product/${img}">
                </div>
            `;
          if(index > 0){
            productDtlImg.innerHTML += `
                <div>
                    <img src="/static/upload/product/${img}">
                </div>
            `;
          }
        });
    }


  setProductPdtName(responseData) {
      const pdtDtlpdtName = document.querySelector(".pName");
      const pdtDtlPdtPrice = document.querySelector(".price-value");
      const tablePdtName = document.querySelectorAll(".product-name");
      const pdtDtlImg = document.querySelector(".image");
      const counts = document.querySelectorAll(".input1");
      const totalPrice = document.querySelector(".totalPrice");
      const plus_btns = document.querySelectorAll(".plus");
      const minus_btns = document.querySelectorAll(".minus");

  
      
      pdtDtlpdtName.innerHTML = "";
      pdtDtlpdtName.innerHTML += `
          ${responseData.pdtName}
      `;

      
      pdtDtlPdtPrice.innerHTML = "";
      pdtDtlPdtPrice.innerHTML += `
          ${responseData.pdtPrice}
      `;

  }

  getProductPrice(responseData){
      const selectedSizes = new Array();
      const pdtDtlSizeSelect = document.querySelector(".size-select");
      const pdtSizeTable = document.querySelector(".selected-table");
      const pdtTableptb = document.querySelector(".pdt-tbody");
      let i = 0;
      pdtDtlSizeSelect.onchange = () => {
        console.log(responseData)

        

        const sizeName = pdtDtlSizeSelect.options[pdtDtlSizeSelect.selectedIndex].text;
        let emptyFlag = true;
        for(let selectedSizeObj of selectedSizes) {
          if(selectedSizeObj.sizeName == sizeName) {
            emptyFlag = false;
            break;
          }
        }

        if(emptyFlag) {

          const pdt = {
            "pdtDtlid" : pdtDtlSizeSelect.value,
            "sizeName" : sizeName,
            "stockCnt" : 0,
            "price" : responseData.pdtPrice
          }

          console.log(pdt)
          selectedSizes.push(pdt);

          pdtTableptb.innerHTML += `
            <tr>
                <td>
                    <p><span class="product-name">${responseData.pdtName}</span><br>- <span class="product-size">${sizeName}</span></p>
                </td>
                <td>
                    <input class="input1" type="text" size="1" value="1">
                    <button type="button" class="plus">+</button>
                    <button type="button" class="minus">-</button>   
                </td>
            <td class="pdt-price-td">${responseData.pdtPrice}
            </td>
            </tr>
          `;
          
          const plusButtons = document.querySelectorAll(".plus");
          plusButtons.forEach((button, index) => {
            button.onclick = () => {
              console.log(index)
              console.log(selectedSizes[index])
              selectedSizes[index].stockCnt += 1;
              selectedSizes[index].price += selectedSizes[index].price;

            }
          })

          const minusButtons = document.querySelectorAll(".minus");
          minusButtons.forEach((button, index) => {
            button.onclick = () => {
              selectedSizes[index].stockCnt--;
              selectedSizes[index].price -= selectedSizes[index].price;
            }
          })
          

        }

      }  
    } 
  }

window.onload = () => {
  new Option();
}