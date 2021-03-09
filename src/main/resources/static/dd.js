$('#delete-user-modal').on('show.bs.modal', function (event) {
    $(this).find('.modal-content')
    let rowId = $(event.relatedTarget).data('user_id');
    let cells = document.getElementById(rowId).getElementsByTagName("td");
    for (let counter = 0; counter < 5; counter++) {
        console.log(cells[counter].textContent);
    }
    document.getElementById('delete-user-id').value = cells[0].textContent;
    document.getElementById('delete-user-firstname').value = cells[1].textContent;
    document.getElementById('delete-user-lastname').value = cells[2].textContent;
    document.getElementById('delete-user-age').value = cells[3].textContent;
    document.getElementById('delete-user-email').value = cells[4].textContent;
    document.getElementById('delete-user-roles').value = cells[5].textContent;

});

$('#edit-user-modal').on('show.bs.modal', function (event) {
    $(this).find('.modal-content')
    let rowId = $(event.relatedTarget).data('user_id');
    let cells = document.getElementById(rowId).getElementsByTagName("td");
    for (let counter = 0; counter < 5; counter++) {
        console.log(cells[counter].textContent);
    }
    document.getElementById('edit-user-id').value = cells[0].textContent;
    document.getElementById('edit-user-firstname').value = cells[1].textContent;
    document.getElementById('edit-user-lastname').value = cells[2].textContent;
    document.getElementById('edit-user-age').value = cells[3].textContent;
    document.getElementById('edit-user-email').value = cells[4].textContent;
    let selectBox = document.getElementById('edit-user-roles');
    let stringValues = cells[5].textContent;
    stringValues = stringValues.slice(1, stringValues.length - 1);
    let arr = stringValues.split(",");

    for (let counter = 0; counter < arr.length; counter++) {
        for (let counterInner = 0; counterInner < selectBox.options.length; counterInner++) {
            if (arr[counter].trim() === selectBox.options[counterInner].innerText) {
                selectBox.options[counterInner].selected = "selected";
                console.log('Role checked SUCCESS ' + arr[counter].trim());
            }
        }
    }
});

$('#edit-user-modal').on('hide.bs.modal', function () {
    let selectBox = document.getElementById('edit-user-roles');
    for (let counterInner = 0; counterInner < selectBox.length; counterInner++) {
        selectBox.options[counterInner].selected = "";
    }
});

$('#delete-user-modal').on('hide.bs.modal', function () {
    let selectBox = document.getElementById('edit-user-roles');
    for (let counterInner = 0; counterInner < selectBox.length; counterInner++) {
        selectBox.options[counterInner].selected = "";
    }
});