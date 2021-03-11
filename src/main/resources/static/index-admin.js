const apiInterfaceUser = 'http://localhost:8080/api/user';
const apiInterfaceRole = 'http://localhost:8080/api/role';


// обновление данных таблицы из базы

async function userTableContextUpdate() {
    let content = await loadInfo(apiInterfaceUser);
    let table = document.getElementById('all-user-table');
    let tbody = document.createElement('tbody');
    tbody.id = 'all-user-table-body';

    for (let counter = 0; counter < content.length; counter++) {
        let tr = document.createElement('tr');
        let row = content[counter];
        console.log(row);
        for (let data in row) {
            let td = document.createElement('td');
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
        let tdEdit = document.createElement('td');
        tdEdit.appendChild(createButton(row['id'], 'Edit', 'btn-primary', '#edit-user-modal'));
        tr.append(tdEdit);
        let tdDelete = document.createElement('td');
        tdDelete.appendChild(createButton(row['id'], 'Delete', 'btn-danger', '#delete-user-modal'));
        tr.append(tdDelete);
        tr.setAttribute("data-user_id", 'user_' + row['id']);
        tbody.appendChild(tr);
    }
    let oldTbody = document.getElementById('all-user-table-body');
    table.replaceChild(tbody, oldTbody);
}

// заполнение ролей в таблице

function rolesToSting(arrayOfRoles) {
    let rolesString = "";
    for (let counter = 0; counter < arrayOfRoles.length; counter++) {
        let obj = arrayOfRoles[counter];
        rolesString = rolesString.concat(obj['roleName'] + " ");
    }
    return rolesString;
}

// заполнение модальных окон

$('#edit-user-modal').on('show.bs.modal', async function (event) {
    let button = $(event.relatedTarget); // Кнопка, запускающая модальное окно
    let recipient = button.data('user_id');

    let id = recipient.substr(7);
    console.log('id = ' + id);

    let userInfo = await loadInfo(apiInterfaceUser + '/' + id);
    let allRoles = await loadInfo(apiInterfaceRole);
    let userRoles = userInfo.roles;

    console.log(userInfo);
    let selectBox = document.getElementById('edit-user-roles');

    for (let counter = 0; counter < allRoles.length; counter++) {
        let obj = allRoles[counter];
        let opt = document.createElement('option');
        // opt.value = obj['id'];
        opt.text = obj['roleName'];
        selectBox.appendChild(opt);
    }

    document.getElementById('edit-user-id').value = userInfo.id;
    document.getElementById('edit-user-firstname').value = userInfo.firstname;
    document.getElementById('edit-user-lastname').value = userInfo.lastname;
    document.getElementById('edit-user-age').value = userInfo.age;
    document.getElementById('edit-user-email').value = userInfo.username;

    for (let counter = 0; counter < userRoles.length; counter++) {
        let userRole = userRoles[counter];
        for (let counterInner = 0; counterInner < selectBox.options.length; counterInner++) {
            if (userRole['roleName'] === selectBox.options[counterInner].innerText) {
                selectBox.options[counterInner].selected = "selected";
                console.log('Role checked SUCCESS ' + userRole['roleName']);
            }
        }
    }
})

$('#delete-user-modal').on('show.bs.modal', async function (event) {
    let button = $(event.relatedTarget); // Кнопка, запускающая модальное окно
    let recipient = button.data('user_id');

    let id = recipient.substr(7);
    console.log('id = ' + id);

    let userInfo = await loadInfo(apiInterfaceUser + '/' + id);
    let allRoles = await loadInfo(apiInterfaceRole);

    console.log(userInfo);
    let selectBox = document.getElementById('delete-user-roles');

    for (let counter = 0; counter < allRoles.length; counter++) {
        let obj = allRoles[counter];
        let opt = document.createElement('option');
        opt.value = obj['id'];
        opt.text = obj['roleName'];
        selectBox.appendChild(opt);
    }

    document.getElementById('delete-user-id').value = userInfo.id;
    document.getElementById('delete-user-firstname').value = userInfo.firstname;
    document.getElementById('delete-user-lastname').value = userInfo.lastname;
    document.getElementById('delete-user-age').value = userInfo.age;
    document.getElementById('delete-user-email').value = userInfo.username;

})

// очистка модальных окон

$('#edit-user-modal').on('hide.bs.modal', function () {
    let modal = $(this)
    modal.find('.modal-body input').val('');
    modal.find('.modal-body select').innerHTML="";
    document.getElementById('edit-user-roles').innerHTML = '';
});

$('#delete-user-modal').on('hide.bs.modal', function () {
    let modal = $(this)
    modal.find('.modal-body input').val('');
    modal.find('.modal-body select').innerHTML="";
    document.getElementById('delete-user-roles').innerHTML = '';
});

// создание кнопок в таблице для действий над сущностью

function createButton(id, btnText, btnColor, btnLink) {
    let btn = document.createElement('button');
    btn.className = 'btn ' + btnColor;
    btn.innerText = btnText;
    btn.setAttribute('data-toggle', 'modal');
    btn.setAttribute('data-target', btnLink);
    btn.setAttribute("data-user_id", 'user_id' + id);
    return btn;
}

// загрузка данных

async function loadInfo(url) {
    let response = await fetch(url);
    if (response.status === 200) {
        let data = await response.json();
        console.log(data);
        return data;
    }
    throw new Error(response.status.toString());
}

// обработка изменения юзера в модальном окне при редактировании

document.getElementById('edit-user').onsubmit = async (event) => {
    event.preventDefault();

    let formData = new FormData(document.getElementById('edit-user'));
    console.log(formData);

    let options = Array.from(document.getElementById('edit-user').getElementsByTagName("option"));

    let object = {};
    let roles = [];

    options.forEach((value, index) => {
        if (value.selected) {
            let obj = {};
            obj['id'] = value.name;
            obj['roleName'] = value.text;
            roles.push(obj);
        }
    })

    formData.forEach((value, key) => {
        if (!Reflect.has(object, key)) {
            object[key] = value;
            return;
        }
        if (!Array.isArray(object[key])) {
            object[key] = [object[key]];
        }
        object[key].push(value);
    });
    object['roles']= roles;
    let data = JSON.stringify(object);
    let id = object['id'];

    let connectUrl = apiInterfaceUser + '/' + id

    console.log('connectUrl= ' + connectUrl);
    console.log(data);
    console.log('id = ' + id);

    const response = await fetch(connectUrl, {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json",
        },
        body: data
    });
    const string = await response.text();
    const json = string === "" ? {} : JSON.parse(string);

    userTableContextUpdate().catch(alert);
    $('#edit-user-modal').modal('hide');

}

// обработка удаления юзера в модальном окне при удалении

document.getElementById('delete-user').onsubmit = async (event) => {
    event.preventDefault();
    console.log('Delete User data');

    let formData = new FormData(document.getElementById('delete-user'));
    console.log(formData);

    let id = formData.get('id');

    let connectUrl = apiInterfaceUser + '/' + id

    console.log('connectUrl= ' + connectUrl);

    const response = await fetch(connectUrl, {
        method: 'DELETE',
    });
    const string = await response.text();
    const json = string === "" ? {} : JSON.parse(string);

    userTableContextUpdate().catch(alert);
    $('#delete-user-modal').modal('hide');

}

// обработка добавления юзера

document.getElementById('add-user').onsubmit = async (event) => {
    event.preventDefault();

    let formData = new FormData(document.getElementById('add-user'));
    console.log(formData);

    let options = Array.from(document.getElementById('add-user').getElementsByTagName("option"));

    let object = {};
    let roles = [];

    options.forEach((value, index) => {
        if (value.selected) {
            let obj = {};
            obj['id'] = value.name;
            obj['roleName'] = value.text;
            roles.push(obj);
        }
    })

    formData.forEach((value, key) => {
        if (!Reflect.has(object, key)) {
            object[key] = value;
            return;
        }
        if (!Array.isArray(object[key])) {
            object[key] = [object[key]];
        }
        object[key].push(value);
    });
    object['roles']= roles;
    let data = JSON.stringify(object);

    let connectUrl = apiInterfaceUser;

    console.log(data);

    const response = await fetch(connectUrl, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: data
    });
    const string = await response.text();
    const json = string === "" ? {} : JSON.parse(string);


    userTableContextUpdate().catch(alert);
    document.getElementById('user-table-tab').dispatchEvent(new Event("click", {bubbles : true, cancelable : true}));
    addFormClean();

}

// очистка формы добавления пользователя

function addFormClean() {
    let inputs = Array.from(document.getElementById('add-user').getElementsByTagName('input'));
    let options= Array.from(document.getElementById('add-user').getElementsByTagName('option'));

    inputs.forEach( element => {
        element.value = "";
    })

}

// при включении вкладки создания пользователя отрисовываем доступные роли

$('#add-user-tab').on('click', async function (event) {
    //event.preventDefault()

    let allRoles = await loadInfo(apiInterfaceRole);

    let selectBox = document.getElementById('add-user-roles');

    selectBox.innerHTML = "";
    console.log('flash add options');

    for (let counter = 0; counter < allRoles.length; counter++) {
        let obj = allRoles[counter];
        let opt = document.createElement('option');
        opt.text = obj['roleName'];
        selectBox.appendChild(opt);
    }
    $(this).tab('show')
})


