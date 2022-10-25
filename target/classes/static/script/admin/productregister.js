const RegisterEventService  ={
    getCategorySelectObj : () => document.querySelectorAll(".product-inputs")[0],
    getNameInputObj: () => document.querySelectorAll(".product-inputs")[1],
    getPriceInputObj: () => document.querySelectorAll(".product-inputs")[2],
    getProductCountObj: () => document.querySelectorAll(".product-inputs")[3],
    getProductInputObj: () => document.querySelectorAll(".product-inputs")[4],
    getRegistButtonObj: () => document.querySelector(".submit_btn"),
    
    init: function() {
        this.getNameInputObj().disabled = true;
        this.getPriceInputObj().disabled = true;
        this.getProductCountObj().disabled = true;
        this.getProductInputObj().disabled = true;
      },
      
      addCategorySelectEvent: function() {
        this.getCategorySelectObj().onchange = () =>{
            
            if(this.getCategorySelectObj().value != "none"){
                
                this.getNameInputObj().disabled = false;
            }else {
                this.getNameInputObj().disabled = true;
            }
            RegisterObj.category = this.getCategorySelectObj().value;    
        }
      },

      addNameInputEvent: function() {
        this.getNameInputObj().onkeyup = () => {
          if(this.getNameInputObj().value.length != 0){
            this.getPriceInputObj().disabled = false;
          }else {
            this.getNameInputObj().disabled = true;
          }
          RegisterObj.name = this.getNameInputObj().value;
        }
      },

      addPriceInputEvent: function() {
        this.getPriceInputObj().onkeyup = () => {
            if(this.getPriceInputObj().value.length != 0){
                this.getProductCountObj().disabled = false;
            }else {
                this.getProductCountObj().disabled = true;
            }
            RegisterObj.price = this.getPriceInputObj().value;
        }
      },

      addCountInputEvent: function() {
        this.getProductCountObj().onkeyup = () => {
            if(this.getProductCountObj().value.length != 0){
                this.getProductInputObj().disabled = false;
            }else{
                this.getProductInputObj().disabled = true;
            }
            RegisterObj.prcount = this.getProductCountObj().value;
        }
      },

      addRegistButtonEvent: function() {
        this.getRegistButtonObj().onclick = () => {

          RegisterObj.detailInfo = this.getProductInputObj().value;

          console.log(RegisterObj);
    
          RegisterRequestService.createProductRequest();
        }
    },
}

const RegisterRequestService = {
    createProductRequest: () => {
      let responseResult = null

      $.ajax({
         async: false,
         type: "post",
         url:"/api/admin/product",
         contentType: "application/json",
         data: JSON.stringify(RegisterObj),
         dataType: "json",
         success : (response) => {
            responseResult = response.data;
         },
         error: (error) => {
            console.log(error);
      }
    });

    return responseResult;
  }
}

const RegisterObj = {
    category: null,
    name: null,
    price: null,
    prcount: null,
    detailInfo: null
  }

  const ProductRegistration = {
    initRegisterEvent: () => {
      RegisterEventService.init();
      RegisterEventService.addCategorySelectEvent();
      RegisterEventService.addNameInputEvent();
      RegisterEventService.addPriceInputEvent();
      RegisterEventService.addCountInputEvent();
      RegisterEventService.addRegistButtonEvent();
    }
  }

  window.onload = () => {
    ProductRegistration.initRegisterEvent();

  }