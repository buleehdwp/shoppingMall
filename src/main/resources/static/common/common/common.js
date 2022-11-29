$(function () {
    /* Event Set */
    //목록
    $(".backList").on("click", function () {
        history.back()
    })

    //취소
    $(".cancel").on("click", function () {
        if (confirm("작성을 취소하시겠습니까?\n진행중인 내용은 저장되지 않습니다.")) {
            history.back()
        }
    })
})

//암호화
function enCrypto(str) {
    return CryptoJS.AES.encrypt(JSON.stringify(str), 'Gorilla').toString();
}

//복호화
function deCrypto(str) {
    return JSON.parse(CryptoJS.AES.decrypt(str, 'Gorilla').toString(CryptoJS.enc.Utf8));
}

//automatic setting
function responseDataSet(res, node) {
    let keys = Object.keys(res);
    let values = Object.values(res);
    let tags = node.find("input, textarea")
    $.each(tags, function (idx, tag) {
        let tagName = tag.name;
        $.each(keys, function (idx, key) {
            if (tagName === key) {
                $(tag).val(values[idx])
            }
        })
    })
}

//css attribute change for readonly
function unsetReadOnly(node) {
    let tags = node.find("input, textarea")
    $.each(tags, function (idx, tag) {
        $(tag).removeAttr("readOnly")
    })
}

//QueryString to Object
function getQueryString() {
    let queryString = location.search.split("&");
    let object = {};
    $.each(queryString, function (idx, data) {
        let splitStr = data.split("=");
        if (splitStr[0].includes("?")) {
            splitStr[0] = splitStr[0].replace("?", "");
        }
        object[splitStr[0]] = deCrypto(splitStr[1]);
    })
    return object;
}

//node to Json
function getJson(node) {
    let object = {};
    $.each($(node).find("input, textarea, input[type='hidden']"), function (idx, data) {
        object[data.name] = data.value;
    })
    return JSON.stringify(object);
}

//node to Json for only formNode
function formToJson(node) {
    let formArray = node.serializeArray();
    let object = {};
    $.each(formArray, function (idx, data) {
        object[data.name] = data.value;
    })
    return JSON.stringify(object);
}

function ajax_get(url) {
    $.ajax({
        url: url,
        type: "get",
        success: function (res) {
            drawTable(res.data);
        },
        error: function (res) {
        }
    })
}

function ajax_post(url, data, url2) {
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        contentType: "application/json",
        data: data,
        success: function (res) {
            /*if (res.status === "Ok") {
                alert(res.message);
                location.href = url2;
            }*/
        },
        error: function (res) {
            if (res.status === 400 || res.status === 403) {
                alert(res.responseJSON.message)
            }
        }
    })
}
