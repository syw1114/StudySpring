class ProductMst {
    #category;
    #name;
    #price;
    #prcount;
    #simpleInfo;

    constructor(category,name,price,prcount,simpleInfo){
        this.#category = category;
        this.#name=name;
        this.#price=price;
        this.#prcount=prcount;
        this.#simpleInfo=simpleInfo;
      }

      getCategory(){return this.#category;}
      setCategory(category){this.#category = category;}
    
      getName(){return this.#name;}
      setName(name){this.#name = name;}
    
      getPrice(){return this.#price;}
      setPrice(price){this.#price = price;}

      getPrcount(){return this.#prcount;}
      setPrcount(prcount){this.#prcount = prcount;}
    
      getSimpleInfo(){return this.#simpleInfo;}
      setSimpleInfo(simpleInfo){this.#simpleInfo =simpleInfo;}

      getObject(){
        const obj = {
          category : this.#category,
          name : this.#name,
          price : this.#price,
          prcount : this.#prcount,
          simpleInfo : this.#simpleInfo
        }
        return obj;
    }
}
class CommonApi {

  getCategoryList() {
      let responseResult = null;

      $.ajax({
          async: false,
          type: "get",
          url:"/api/admin/product/category",
          dataType: "json",
          success: (response) => {
              responseResult = response.data;
          },
          error: (error) => {
              console.log(error);
          }

      });

      return responseResult;

  }
}

class RegisterApi {
  createProductRequest(productMst) {
    let responseResult = null;

    $.ajax({
      async: false,
      type: "post",
      url:"/api/admin/product",
      contentType: "application/json",
      data: JSON.stringify(productMst),
      dataType: "json",
      success : (response) => {
         responseResult = response.data;
         console.log(response.data);
      },
      error: (error) => {
      console.log(error);
   }
  });

 return responseResult;
  }
}


class RegisterEventService{
  
  #categorySelectObj;
  #nameInputObj;
  #priceInputObj;
  #productCountObj;
  #productInfoObj;
  #registButtonObj;

  constructor(){
    this.#categorySelectObj = document.querySelectorAll(".product-inputs")[0];
    this.#nameInputObj = document.querySelectorAll(".product-inputs")[1];
    this.#priceInputObj = document.querySelectorAll(".product-inputs")[2];
    this.#productCountObj = document.querySelectorAll(".product-inputs")[3];
    this.#productInfoObj = document.querySelectorAll(".product-inputs")[4];
    this.#registButtonObj= document.querySelector(".submit_btn");

    this.init();

    this.addCategorySelectEvent();
    this.addNameInputEvent();
    this.addPriceInputEvent();
    this.addProductCountEvent();
    this.addRegistButtonEvent();

  } 

  init(){
    this.#nameInputObj.disabled = true;
    this.#priceInputObj.disabled = true;
    this.#productCountObj.disabled = true;
    this.#productInfoObj.disabled = true;
    this.#registButtonObj.disabled = true;
  }

  
  addCategorySelectEvent() {
    this.#categorySelectObj.onchange = () => {
      if(this.#categorySelectObj.value != "none"){
        this.#nameInputObj.disabled = false;
      }else {
        this.#nameInputObj.disabled = true;
      }
    }
  }

  addNameInputEvent() {
    this.#nameInputObj.onkeyup = () => {
      if(this.#nameInputObj.value.length != 0){
        this.#priceInputObj.disabled = false;
      }else {
        this.#nameInputObj.disabled = true;
      }
    }
  }

  addPriceInputEvent() {
    this.#priceInputObj.onkeyup = () => {
      if(this.#priceInputObj.value.length != 0){
        this.#productCountObj.disabled = false;
      }else {
        this.#productCountObj.disabled = true;
      }
    }
  }

  addProductCountEvent() {
    this.#productCountObj.onkeyup = () => {
      if(this.#productCountObj.value.length != 0){
        this.#productInfoObj.disabled = false;
      }else {
        this.#productInfoObj.disabled = true;
      }
    }
  }

  addRegistButtonEvent() {
      this.#registButtonObj.onclick = () => {

      const category = this.#categorySelectObj.value;
      const name = this.#nameInputObj.value;
      const price = this.#priceInputObj.value;
      const prcount = this.#productCountObj.value;
      const simpleInfo = this.#productInfoObj.value;
      
      const productMst = new ProductMst(
        category,name,price,prcount,simpleInfo
        );
  
        const registerApi = new RegisterApi();
        registerApi.createProductRequest(productMst.getObject());
  
      }
    }
}
const ProductRegistration = {
  initRegisterEvent: () => {
    RegisterEventService.init();
    RegisterEventService.addCategorySelectEvent();
    RegisterEventService.addNameInputEvent();
    RegisterEventService.addPriceInputEvent();
    RegisterEventService.addProductCountEvent();
    RegisterEventService.addRegistButtonEvent();
  }
}



class RegisterService{

  static #instance = null; 

  constructor(){
    
  }
  
  static getInstance() {
    if(this.#instance == null){
      this.#instance = new RegisterService();
    }
    return this.#instance;
  }

  loadRegister(){
      
  }

  getCategoryList() {
    const commonApi = new CommonApi();
    const productCategoryList = commonApi.getCategoryList();

    const productCategory = document.querySelector(".product-category");
    productCategory.innerHTML = "";

    productCategoryList.forEach(category => {
        productCategory.innerHTML += `productMst
            <option value="${category.id}">${category.name}</option>
        `;
    })
}

setRegisterHeaderEvent() {
    new RegisterEventService();
}
}

window.onload = () => {
  RegisterService.getInstance().getCategoryList();
  RegisterService.getInstance().setRegisterHeaderEvent();
}