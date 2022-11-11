class ProductMst {
    #category; //# = private -> 캡슐화
    #name;
    #price;
   

    constructor(category, name, price) {
        this.#category = category;
        this.#name = name;
        this.#price = price;
        
    }

    getCategory(){return this.#category;}
    setCategory(category) {this.#category = category;}

    getName(){return this.#name;}
    setName(name) {this.#name = name;}

    getPrice(){return this.#price;}
    setPrice(price) {this.#price = price;}


    getObject() {
        const obj = {
            category : this.#category,
            name : this.#name,
            price : this.#price,
           
        }
        return obj;
    }
}

class CommonApi {
    getCategoryList() {
        let responseResult = null;

        $.ajax({
            async:false,
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

class ProductApi {
    createProductRequest(productMst) {
        let responseData = null;

        $.ajax({
            async: false,
            type: "post",
            url: "/api/admin/product",
            contentType: "application/json",
            data: JSON.stringify(productMst),
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

    getProductListRequest(listRequestParams) {
        let responseData = null;

        $.ajax({
            async:false,
            type: get,
            url :"/api/admin/products",
            data : listRequestParams ,
            dataType: "json",
            success: (response) => {
                responseData = Response.data;
            },
            error : (error) => {
                console.log(error);
            }

            
        })

        return responseData;

    }
}

class RegisterEventService {
    #categorySelectObj;
    #nameInputObj;
    #priceInputObj;
    #registButtonObj;

    constructor() {
        this.#categorySelectObj = document.querySelectorAll(".product-inputs")[0];
        this.#nameInputObj = document.querySelectorAll(".product-inputs")[1];
        this.#priceInputObj = document.querySelectorAll(".product-inputs")[2];
        this.#registButtonObj = document.querySelector(".regist-button");

        this.init();

        this.addCategorySelectEvent();
        this.addNameInputEvent();
        this.addPriceInputEvent();
        this.addRegistButtonEvent();

    }

    init() {
        this.#nameInputObj.disabled = true;
        this.#priceInputObj.disabled = true;
        this.#registButtonObj.disabled = true;
    }

    addCategorySelectEvent() {
        this.#categorySelectObj.onchange = () => {
            if(this.#categorySelectObj.value != "none"){
                this.#nameInputObj.disabled = false;
            }else{
                this.#nameInputObj.disabled = true;
            }
        }
    }

    addNameInputEvent() {
        this.#nameInputObj.onkeyup = () => {
            if(this.#nameInputObj.value.length != 0){
                this.#priceInputObj.disabled = false;
            }else{
                this.#priceInputObj.disabled = true;
            }
        }

    }

    addPriceInputEvent() {
        this.#priceInputObj.onkeyup = () => {
            if(this.#priceInputObj.value.length != 0){
                this.#registButtonObj.disabled = false;
            }else{
                this.#registButtonObj.disabled = true;
            }
        }
    }

    addRegistButtonEvent= () => {
        this.#registButtonObj.onclick = () => {
            const category = this.#categorySelectObj.value;
            const name = this.#nameInputObj.value;
            const price = this.#priceInputObj.value;

            const productMst = new ProductMst( //생성
                category, name, price
            );

            const productApi = new ProductApi();
            if(productApi.createProductRequest(productMst.getObject())){
                alert("상품 등록 완료");
                location.href="/admin/product/register/dtl";
            }
        }
    }
}

class RegisterService {
    static #instance = null;

    constructor() {

    }

    static getInstance() { //생성 안하고 바로 호출가능
        if(this.#instance == null){
            this.#instance = new RegisterService();
        }
        return this.#instance;
    }

    loadRegister() {

    }

    getCategoryList() {
        const commonApi = new CommonApi();
        const productCategoryList = commonApi.getCategoryList();

        const productCategory = document.querySelector(".product-category");
        productCategory.innerHTML = `<option value="none">카테고리를 선택하세요.</option>`;

        productCategoryList.forEach(category => {
            productCategory.innerHTML += `
                <option value="${category.id}">${category.name}</option>
            `;
        })
    }

    setRegisterHeaderEvent() {
        
        new RegisterEventService();
    }
}

class ListService {
    static #instance = null;

    getInstance() {
        if(this.#instance == null ) {
            this.#instance = new ListService();
        }
        return this.#instance;
    }

}


window.onload = () => {
    RegisterService.getInstance().getCategoryList();
    RegisterService.getInstance().setRegisterHeaderEvent();
}