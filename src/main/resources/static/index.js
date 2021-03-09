const apiInterface = 'http://localhost:8080/api/user';



async function userTableContextUpdate() {
    let users = await fetch(apiInterface);
    if (users.ok) {
        let content = await users.json();
        let table = document.getElementById('all-user-table');
        let tbody = document.createElement('tbody');
        console.log("content = " + content);
        console.log(tbody);

        for (let counter = 0; counter < content.length; counter++) {
            let tr = document.createElement('tr');
            let row = content[counter];
            console.log(row);
            for (let data in row) {
                let td = document.createElement('td');
                if(data == 'roles') {
                    console.log('data = roles');
                    td.innerHTML = rolesToSting(row[data]);
                    tr.appendChild(td);
                    continue;
                }
                td.innerHTML = row[data];
                tr.appendChild(td);
                console.log(row[data]);
            }
            let tdEdit = document.createElement('td');
            tdEdit.appendChild(createButton());
            tr.append(tdEdit);
            let tdDelete = document.createElement('td');
            tdDelete.appendChild(createButton());
            tr.append(tdDelete);
            tbody.appendChild(tr);
        }
        table.appendChild(tbody);

    } else {
        alert("Ошибка HTTP: " + users.status);
    }
    ;

};

function rolesToSting(arrayOfRoles) {
    let rolesString = "";
    for(let counter=0; counter<arrayOfRoles.length; counter++) {
        let obj = arrayOfRoles[counter];
        rolesString = rolesString.concat(obj['roleName'] + " ") ;
        console.log(rolesString);
        console.log(obj);
        console.log(obj['roleName']);
    }
    return rolesString;
}

function createButton(id, btnText, btnColor, btnLink) {
    let btn = document.createElement('button');
    btn.className = 'btn ' + btnColor;
    btn.innerText = btnText;
    return btn;
}

$(document).ready(function () {
    if (document.getElementById('v-pills-admin-tab') != null) {
        $('#v-pills-admin-tab').tab('show');
    } else {
        $('#v-pills-user-tab').tab('show');
    }
    ;

    userTableContextUpdate();

});

