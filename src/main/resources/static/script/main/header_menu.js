getPrincipal();

function getPrincipal() {
    let responseData = null;

    $.ajax({
        async: false,
        type: "GET",
        url: "/api/account/principal",
        dataType: "json",
        success: response => {
            if(response.data != null) {
                responseData = response.data;
                console.log(responseData.id);
                document.querySelector(".login-status").innerHTML = `<li><a href="/logout">LOGOUT</a></li>`;
            }
        },
        error: error => {
        console.log(error);
        }
    });
    return responseData;
}