const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInput = document.querySelectorAll(".field input");


let user = {
    username : accountInput[0].value,
    password : accountInput[1].value,
    name : accountInput[2].value,
    phone : accountInput[3].value + accountInput[4].value + accountInput[5].value
    }

  $.ajax({
    async : false,
    type : "PUT",
    url : "/api/account/updateMypage",
    contentType : "application/json",
    data: JSON.stringify(user),
    dataType : "json",
    success : (response) => {
        alert("회원정보 수정에 성공하셨습니다.")
    },
    error : (error) => {
        console.log(error);
        }
    });

}