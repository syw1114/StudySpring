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
            },
            error: (error) => {
            console.log(error);
         }
       });

    return responseResult;
  }
}

