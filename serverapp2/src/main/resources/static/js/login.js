function loginUser(nume, parola) {
    const credentials = {username: nume, password: parola};
const credentialsJSON = JSON.stringify(credentials);
sendRequest('POST','login',credentialsJSON,handle_LogareReusita,handle_LogareEronata);
}
function handle_LogareEronata(status) {
    alert('Logare esuata' + status);
}
function handle_LogareReusita(data) {
    localStorage.setItem('token', data.token);
    alert('Logare realizata cu succes!');
    window.location.href = 'map.html';
}
