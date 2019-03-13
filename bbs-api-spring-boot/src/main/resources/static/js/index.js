function apiPost(method) {
    var baseRequest = new Object();
    baseRequest.method = method;

    switch (method) {
        case "getPostList":
            var parameter = new Object();
            parameter.recordsLimit = document.getElementById("pageSize").value;
            //parameter.whereFieldValue = document.getElementById("whereFieldValue").value;

            baseRequest.jsonStringParameter = JSON.stringify(parameter);
            break;
        case "getPostByPrimaryKey":
            baseRequest.extendValue = document.getElementById("whereFieldValue").value;
            break;
        case "getCommentList":
            var parameter = new Object();
            parameter.recordsLimit = document.getElementById("pageSizeForComment").value;
            parameter.whereFieldValue = document.getElementById("postID").value;

            baseRequest.jsonStringParameter = JSON.stringify(parameter);
            break;
    }

    callAjax(baseRequest);
}

function userStarAndPraise(method, mapType) {
    var baseRequest = new Object();
    baseRequest.method = method;

    var parameter = new Object();

    parameter.userId = document.getElementById("userId").value;
    parameter.postId = document.getElementById("postIDForStarOrPraise").value;
    parameter.mapType = mapType;

    baseRequest.jsonStringParameter = JSON.stringify(parameter);

    callAjax(baseRequest);
}

function callAjax(baseRequest) {
    $.ajax({
        type: "POST",
        url: apiUrl,
        data: baseRequest,
        timeout: 600000,
        success: function (o) {
            $("#txtJsonResult").val(JSON.stringify(o));
            var result = "操作结果：";

            if (o.code === 1) {
                alert("success!");
                $("#divResult").html(result + "success!");
            } else {
                alert(o.message);
                $("#divResult").html(result + o.message);
            }
        },
        error: function (e) {
            alert(e.responseText);
        }
    });
}
