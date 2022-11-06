function formToJson(node) {
    let formArray = node.serializeArray();
    let object = {}
    $.each(formArray, function (idx, data) {
        object[data.name] = data.value
    })
    return JSON.stringify(object)
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
                alert(res.message)
                location.href = url2;
            }
        },
        error: function (res) {
            alert(res.responseJSON.errorMessage);
        }
    })
}