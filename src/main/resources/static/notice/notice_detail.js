$(function () {
    //init
    init();

    function init() {
        if (location.search.includes("title")) {
            getNotice(getQueryString().title)
        }
    }

    function getNotice(keyword) {
        $.ajax({
            url: "/api/view/notice/detail",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                title: keyword
            }),
            success: function (res) {
                let data = res.data;

                //user
                let title = `<tr><th>제목</th><td colspan="3">${data.title}</td></tr>`;
                let createdByDate = `<tr><th>작성자</th><td>${data.createBy}</td><th>작성일</th><td>${data.updateDate.substring(0, 10)}</td></tr>`;
                let content = `<tr><td colspan="4"><textarea class="form-control notice_content" readonly>${data.content}</textarea></td></tr>`;
                const user = `<tbody>${title}+${createdByDate}+${content}+</tbody>`
                $(".detailTable").append(user)

                //admin
                $("input[name='uuid']").val(data.uuid)
                title = `<tr><th>제목</th><td colspan="3"><input type="text" class="form-control" name="title" value=${data.title}></td></tr>`;
                createdByDate = `<tr><th>작성자</th><td><input type="text" class="form-control" name="createBy" value=${data.createBy}></td>`
                                +`<th>작성일</th><td><input type="text" class="form-control"  readonly value=${data.updateDate.substring(0, 10)}></td></tr>`;
                content = `<tr><td colspan="4"><textarea class="form-control notice_content" name="content">${data.content}</textarea></td></tr>`;
                const modify = `<tbody>${title}+${createdByDate}+${content}+</tbody>`
                $(".modifyTable").append(modify)
            },
            error: function (res) {
                if (res.status === 400) {
                    alert(res.responseJSON.message)
                }
            }
        })
    }


    //insert
    $(".insert").on("click", function () {
        if ($("#createBy").val() === "") $("#createBy").val("관리자");
        ajax_post("/api/admin/submit?type=I", getJson($("#insertDiv")), "/api/view/notice/listPage");
    })

    //modify
    $(".modify").on("click", function () {
        if (confirm("정말 수정하시겠습니까?")) {
            ajax_post("/api/admin/submit?type=U", getJson($("#modifyTable")), "/api/view/notice/listPage");
        }
    })

    //delete
    $(".delete").on("click", function () {
        if (confirm("정말 삭제하시겠습니까?")) {
            ajax_post("/api/admin/submit?type=D", getJson($("#modifyTable")), "/api/view/notice/listPage");
        }
    })

    //cancel
    $(".cancel").on("click", function () {
        if (confirm("작성을 취소하시겠습니까?\n진행중인 내용은 저장되지 않습니다.")) {
            location.href = "/api/view/notice/listPage";
        }
    })

    //backList
    $(".backList").on("click", function () {
        history.back()
    })
})