function getPositions() {
    let criteria = new Criteria();

    //todo: replace with the token stored in the local storage at login
    let token = localStorage.getItem('token');

    sendRequest("GET", "positions?" + $.param(criteria), null, getPositionsSuccessHandler, getPositionsErrorHandler);
}
function Criteria() {
    let deviceId = $('#deviceId').val().trim();
    if (deviceId.length > 0) {
        this.terminalId = deviceId;
    }

    let startDate = $('#startDate').val().trim();
    if (startDate.length > 0) {
        this.startDate = startDate;
    }

    let endDate = $('#endDate').val().trim();
    if (endDate.length > 0) {
        this.endDate = endDate;
    }
}
function getPositionsSuccessHandler(date_venite) {
    for (let i=0; i < date_venite.length; i++) {
        addStaticMarker({
            lat: date_venite[i].latitude,
            lng: date_venite[i].longitude
        });
    }
}
function getPositionsErrorHandler(status) {
    alert("err response: " + status);
}
