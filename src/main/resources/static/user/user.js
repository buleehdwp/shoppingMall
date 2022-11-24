$(function () {
    // 로그인 실패
    if(location.search.includes("fail")){
        alert("아이디 또는 비밀번호를 잘못 입력했습니다.\n입력하신 내용을 다시 확인해주세요.(대소문자 구분함)");
        history.pushState(null,null,"/api/login");
    }

    //회원가입 페이지 이동
    $(".signUpBtn").on("click", function (e) {
        location.href = "/api/view/user/signUp"
    })

    // 회원가입 데이터 제출
    $(".submitBtn").on("click",function(e){
        ajax_post("/api/user/submit/signUp", getJson($("#insertDiv")), "/api/view/user/login");
    })

})
