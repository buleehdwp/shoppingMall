$(".insert").on("click", function (e) {
    ajax_post("/notice/admin/insert", formToJson($("#noticeFrm")), "/notice/view")
})

$(".cancel").on("click", function (e) {
    if(confirm("작성을 취소하시겠습니까?\n진행중인 내용은 저장되지 않습니다.")){
        location.href="/notice/view";
    }
})