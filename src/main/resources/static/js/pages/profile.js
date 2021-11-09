$(document).ready(function(){
    let get_account_url = "/api/account";
    get(get_account_url, function(response){
        console.log(response);
    })
});