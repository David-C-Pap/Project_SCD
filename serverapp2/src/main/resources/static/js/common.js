function sendRequest(type, resource, data, successHandler, errHandler) {
    let token =localStorage.getItem('token');
    jQuery.ajax({
        type: type,
        url: "http://localhost:8082/" + resource,
        data: data,
        dataType: "json",
        accepts: "application/json",
        contentType: "application/json",
        headers:{"Authorization" : "Bearer " + token},
        success: function (data, status, jqXHR) {
                    successHandler(data);
                },
         error: function (jqXHR, status) {
                    errHandler(status + ": " + jqXHR.status);
                }
    });
}
function goToPage(url) {
    $(location).attr('href', url);
}