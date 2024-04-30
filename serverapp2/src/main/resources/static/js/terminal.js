function createTerminal(tId, tname) {
    let terminalData = {id: tId,terminalName: tname};
    sendRequest('POST', 'terminals', JSON.stringify(terminalData), handle_CreareSucces, handle_CreareEroare);
}
function deleteTerminal(terminalId) {

    sendRequest('DELETE','terminals/' + terminalId,null,null,handle_StergereSucces, handle_StergereEroare);
}
function handle_CreareSucces() {
    alert("Terminalul a fost creat!");
}
function handle_CreareEroare() {
    alert("Nu s-a putut crea terminalul");
}

function handle_StergereSucces(status) {
    alert("Terminalul a fost sters!" + status);
}

function handle_StergereEroare(status) {
    alert("Nu s-a putut sterge terminalul" + status);
}
