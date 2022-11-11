const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInput = document.querySelectorAll(".field input");


let user = {
    username : accountInput[0].value,
    password : accountInput[1].value,
    reconfirm : accountInput[2].value,
    name : accountInput[3].value,
    phone : accountInput[4].value + accountInput[5].value + accountInput[6].value
    }

let ajaxOption = {
    async : false,
    type : "post",
    url : "/api/account/signUp",
    contentType : "application/json",
    data: JSON.stringify(user),
    dataType : "json",
    success : (response, textStatus, request) => {
        alert("회원가입에 성공하셨습니다.")
        const successURI = request.getResponseHeader("Location");
        location.replace(successURI + "?username=" + response.data);
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