// инициализация

document.addEventListener("DOMContentLoaded", initElements);

function initElements() {
    if (document.getElementById('v-pills-admin-tab') != null) {
        $('#v-pills-admin-tab').tab('show');
        userTableContextUpdate().catch(alert);
    } else {
        $('#v-pills-user-tab').tab('show');
    }
}

// показывают юзеринфо

$('#v-pills-user-tab').on('click', async function (event) {

    let table = document.getElementById("logged-user-table");
    let id = document.getElementById('current-user').innerHTML;
    let oldTbody = document.getElementById('logged-user');

    let content = await loadInfo(apiInterfaceUser + '/' + id);
    let tbody = document.createElement('tbody');
    tbody.id = 'logged-user';

    let tr = document.createElement('tr');
    let row = content;

    for (let data in row) {
        let td = document.createElement('td');
        if (data === 'id') {
            td.id = 'current-user';
        }
        if (data === 'roles') {
            console.log('data = roles');
            td.innerHTML = rolesToSting(row[data]);
            tr.appendChild(td);
            continue;
        }
        if (data === 'password') {
            continue;
        }
        td.innerHTML = row[data];
        tr.appendChild(td);
        console.log(row[data]);
    }
    tbody.appendChild(tr);
    table.replaceChild(tbody, oldTbody);

    $(this).tab('show')
})
