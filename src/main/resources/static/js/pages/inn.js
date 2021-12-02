$(document).ready(function () {
    // get Inn List
    let get_inn_list_url = "/api/inn/list"
    get(get_inn_list_url
        , function(result){
        console.log(result);
        }
        )
    // fill tales
    // modal hide
    $(".modal").hide();
})