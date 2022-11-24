$(function () {
    //init
    init();

    function init() {
        noticeSearch("/api/view/notice/search?keyword=" + $("#keyword").val());
    }

    //search.keyword
    $(".search").on("click", function () {
        noticeSearch("/api/view/notice/search?keyword=" + $("#keyword").val());
    })

    //search.pageNum
    $("p.page-link").on("click", function (e) {
        let pNum = e.target.dataset.pagenum;
        if (pNum === "+") {
            pNum = window.pageNum * 1 + 1;
        }
        noticeSearch("/api/view/notice/search?page=" + pNum + "&keyword=" + $("#keyword").val());
    })

    function noticeSearch(url) {
        $.ajax({
            url: url,
            type: "get",
            success: function (res) {
                $(".table tbody").empty();

                let data = res.data;
                let html = "<tbody>";
                $.each(data.content, function (idx, content) {
                    let title = content.title;
                    let createBy = content.createBy;
                    let date = content.updateDate.substring(0, 10);
                    html += `<tr><td class="title">${title}</td><td class="createBy">${createBy}</td><td class="updateDate">${date}</td></tr>`;
                })
                html += "</tbody>";

                $(".table").append(html);
            },
            error: function (res) {
            }
        })
    }

    //search.detail
    $(".table").on("click", function (e) {
        let tag = e.target;
        if (tag.className === "title") {
            location.href = "/api/view/notice/detailPage?title=" + enCrypto(tag.innerHTML);
        }
    })

    function noticeDraw(data) {
        // window.pageNum = data.data.pageable.pageNumber;
        // let numArea = $(".pagination").find("p")
        // $.each(numArea,function(idx,data){
        //     if(window.pageNum === data.dataset.pagenum*1){
        //         $(data).css("background", "lightgray")
        //     }else{
        //         $(data).css("background", "#fff")
        //     }
        // })
    }
})


