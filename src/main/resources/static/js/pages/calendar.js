let col;
let row;

// init calendar
$(document).ready(function (){
    // 현재 날짜 기준으로 예약현황 조회 후

    // 해당 날짜에 예약정보들을 넣어준다.
    fillCellWithValues();
    // 예약된 셀을 색칠
    colorReservations();

    // 모달창 숨김
    $(".modal").hide();
});

$(document).on('click', 'td', function(){
    col = $(this).parent().children().index($(this));
    row = $(this).parent().parent().children().index($(this).parent());
    // console.log("col : " + col + ", row : " + row);
    openModal(col, row)
})

$(document).on('click', '.close', function(){
    closeModal();
})

$(document).on('click', '.confirmButton', function(){
    //ajax
    //close modal
    closeModal();
})

$(document).on('click', '.cancelButton', function(){
    closeModal();
})


function openModal(col, row){
    if(!isContentCell(col, row)) return;
    //ajax

    //when success show modal
    $(".modal").show();
}

function closeModal(){
    $(".modal").hide();
}

/* 표 영역에서 날짜 표시 부분과 방 호수 부분을 제외한 영역인지 체크*/
function isContentCell(col, row){
    if(row < 2 || col < 1) return false;
    else return true;
}

// TODO- 그후, 예약정보에 8개 중 랜덤 컬러를 넣어준다.
// 일단 연노랑으로 고정해서 배경색 넣기.
function colorReservations(){
    $("td").each(function(){
        col = $(this).parent().children().index($(this));
        row = $(this).parent().parent().children().index($(this).parent());
        if(this.innerHTML != ""){
            if(isContentCell(col, row)) $(this).addClass("reserved");
        }
    })
}

function fillCellWithValues(jsonArray){

}