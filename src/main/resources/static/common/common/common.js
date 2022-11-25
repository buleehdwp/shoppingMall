//암호화
function enCrypto(str){
    return CryptoJS.AES.encrypt(JSON.stringify(str), 'Gorilla').toString();
}

//복호화
function deCrypto(str){
    return JSON.parse(CryptoJS.AES.decrypt(str, 'Gorilla').toString(CryptoJS.enc.Utf8));
}

function getQueryString(){
    let queryString = location.search.split("&");
    let object = {};
    $.each(queryString,function(idx,data){
        let splitStr = data.split("=");
        if(splitStr[0].includes("?")){
            splitStr[0] = splitStr[0].replace("?","");
        }
        object[splitStr[0]] = deCrypto(splitStr[1]);
    })
    return object;
}


//node안의 input, textarea, select 등 태그의 name의 값을 가져와서 json형태로 반환.
function getJson(node) {
    let object = {};
    $.each($(node).find("input, textarea, input[type='hidden']"), function (idx, data) {
        object[data.name] = data.value;
    })
    return JSON.stringify(object);
}

//form의 name값을 JSON 형식으로 변환
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
            if (res.status === "Ok") {
                alert(res.message);
                location.href = url2;
            }
        },
        error: function (res) {
            debugger
            if(res.status === 400){
                alert(res.responseJSON.message)
            }
        }
    })
}
