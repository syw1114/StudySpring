class CollectionsApi {
    static #instatnce = null;

    static getInstance() {
        if(this.#instatnce == null) {
            this.#instatnce = new CollectionsApi();
        }
        return this.#instatnce;
    }

    getCollections(page) {
        let responseData = null;

        const url = location.href;
        const category = url.substring(url.lastIndexOf("/") + 1);

        $.ajax({
            async: false,
            type: "get",
            url: "/api/collections/" + category,
            data: {
                "page": page
            },
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

class PageNumber {
    #page = 0;
    #maxPageNumber = 0;
    #pageNumberList = null;

    constructor(page, totalCount) {
        this.#page = page;
        this.#maxPageNumber = totalCount % 9 == 0 ? Math.floor(totalCount / 9) : Math.floor(totalCount / 9) + 1;
        this.#pageNumberList =  document.querySelector(".page-num");
        this.#pageNumberList.innerHTML = ``;
        this.loadPageNumbers();
    }

    loadPageNumbers() {
        this.createPreButton();
        this.createNumberButtons();
        this.createNextButton();
        this.addPageButtonEvent();
    }

    createPreButton() {
        if(this.#page != 1) {
            this.#pageNumberList.innerHTML += `
            <a href="javascript:void(0)"><li>Prev</li></a>
            `;

        }
    }

    createNumberButtons() {
        const startIndex = this.#page % 5 == 0 ? this.#page - 4 : this.#page - (this.#page % 5) + 1;
        const endIndex = startIndex + 4 <= this.#maxPageNumber ? startIndex + 4 : this.#maxPageNumber;

        for(let i = startIndex; i <= endIndex; i++) {
            this.#pageNumberList.innerHTML += `
            <a href="javascript:void(0)"><li>${i}</li></a>
            `;
        }
    }

    createNextButton() {
        if(this.#page != this.#maxPageNumber) {
            this.#pageNumberList.innerHTML += `
            <a href="javascript:void(0)"><li>Next</li></a>
            `;
        }
    }

    addPageButtonEvent() {
        const pageButtons = this.#pageNumberList.querySelectorAll("li");
        pageButtons.forEach(button => {
            button.onclick = () => {
                if(button.textContent == "Prev") {
                    const nowPage = CollectionsService.getInstance().collectionsEntity.page;
                    CollectionsService.getInstance().collectionsEntity.page = Number(nowPage) - 1;
                    CollectionsService.getInstance().loadCollections();

                }else if(button.textContent == "Next") {
                    const nowPage = CollectionsService.getInstance().collectionsEntity.page;
                    CollectionsService.getInstance().collectionsEntity.page = Number(nowPage) + 1;
                    CollectionsService.getInstance().loadCollections();

                }else {
                    const nowPage = CollectionsService.getInstance().collectionsEntity.page;
                    if(button.textContent != nowPage) {
                        CollectionsService.getInstance().collectionsEntity.page = button.textContent;
                        CollectionsService.getInstance().loadCollections();
                    }
                }
            }
        });
    }
}

class CollectionsService {
    static #instatnce = null;

    static getInstance() {
        if(this.#instatnce == null) {
            this.#instatnce = new CollectionsService();
        }
        return this.#instatnce;
    }
    pdtIdList = new Array();

    collectionsEntity = {
        page: 1,
        totalCount: 0
    }

    constructor() {
        this.pdtIdList = new Array();
    }

    loadCollections() {
        const responseData = CollectionsApi.getInstance().getCollections(this.collectionsEntity.page);
        if(responseData.length > 0 ){
            this.collectionsEntity.totalCount = responseData[0].productTotalCount;
            new PageNumber(this.collectionsEntity.page, this.collectionsEntity.totalCount);
            this.getCollections(responseData);
            console.log(this.pdtIdList);
        }else {
            alert("해당 카테고리에 등록된 상품 정보가 없습니다.");
            location.href = "/collections/all"
        }

    }

    getCollections(responseData) {
        const collectionProducts = document.querySelector(".collection-products");
        collectionProducts.innerHTML = ``;
        this.pdtIdList.length = 0;
        responseData.forEach(product => {
            this.pdtIdList.push(product.productId);
            collectionProducts.innerHTML += `
            <li class="collection-product">
                <div class="product-img">
                    <img src="/static/upload/product/${product.mainImg}">
                </div>
                <div class="product-name">
                    ${product.productName}
                </div>
                <div class="product-price">
                    ${product.productPrice}원
                </div>
            </li>
            `;
        });
        this.addProductListEvent();
    }

    addProductListEvent() {
        const collectionProducts = document.querySelectorAll(".collection-product");
    
        collectionProducts.forEach((product,index) => {
            product.onclick = () => {
            location.href = "/product/" + this.pdtIdList[index];
            }
        });
      }

}

window.onload = () => {
    CollectionsService.getInstance().loadCollections();
}