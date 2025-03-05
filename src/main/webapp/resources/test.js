/* Place any Javascript here you want loaded in index.xhtml */
const inputFields = document.querySelectorAll('.ui-inputfield');
if (inputFields.length >= 3) {
    inputFields[2].style.backgroundColor = 'lightblue';
}

