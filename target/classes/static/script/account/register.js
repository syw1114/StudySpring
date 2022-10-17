const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInput = document.querySelectorAll(".field input");


let user = {
    id : accountInput[0].value,
    password : accountInput[1].value,
    reconfirm : accountInput[2].value,
    name : accountInput[3].value,
    registerNumber : accountInput[4].value + accountInput[5].value,
    address : accountInput[6].value + accountInput[7].value + accountInput[8].value
    }

let ajaxOption = {
    async : false,
    type : "post",
    url : "/api/account/SignUp",
    contentType : "application/json",
    data: JSON.stringify(user),
    dataType : "json",
    success : (response) => {
        console.log(response);
    },
    error : (error) => {
              console.log(error.responseJSON);
              console.log(error.responseJSON.data); //responseJSON <- CMRespDto
              loadErrorMessage(error.responseJSON.data);//이때 에러의 데이터를 여기에 넣어줌.
        }
    }
    $.ajax(ajaxOption);
    //에러들을 들고와서 반복을 시킴.
    function loadErrorMessage(errors){
    var check = true;
      const errorArray = Object.values(errors);
      //에러를 가져와서 innerHTML 에러를 뽑아줌.
      errorArray.forEach(error => {
      if(check){
        alert(error);
            check = false;
            return false;
        }
      });
    }
}