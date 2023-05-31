var btn = document.getElementById("submitBtn");

// Add a click event listener to the compare button
btn.addEventListener("click", function() {
        // Scroll to the bottom of the page
        window.scrollTo(0, document.body.scrollHeight);
});

// Listen for the submit event on the form
// document.getElementById("myForm").addEventListener("submit", function(event) {
//         // Prevent the default form submission
//         event.preventDefault();
//
//         // Show the "showup" div
//         document.getElementById("showup").style.display = "block";
//
//         // Submit the form programmatically
//         event.target.submit();
// });

function toggleArrayTypes() {
        var arrayTypeCheckboxes = document.getElementsByName("arrayType");
        var selectAllArrayTypesCheckbox = document.getElementById("selectAllArrayTypes");

        for (var i = 0; i < arrayTypeCheckboxes.length; i++) {
                arrayTypeCheckboxes[i].checked = selectAllArrayTypesCheckbox.checked;
        }
}

function toggleAlgorithms1() {
        var algorithmCheckboxes = document.getElementsByName("algorithms");
        var selectAllAlgorithmsCheckbox = document.getElementById("selectLowAlgorithms");

        algorithmCheckboxes[0].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[1].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[3].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[4].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[6].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[9].checked = selectAllAlgorithmsCheckbox.checked;
}

function toggleAlgorithms2() {
        var algorithmCheckboxes = document.getElementsByName("algorithms");
        var selectAllAlgorithmsCheckbox = document.getElementById("selectHighAlgorithms");

        algorithmCheckboxes[2].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[5].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[7].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[8].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[10].checked = selectAllAlgorithmsCheckbox.checked;
        algorithmCheckboxes[11].checked = selectAllAlgorithmsCheckbox.checked;
}

function toggleAlgorithms() {
        var algorithmCheckboxes = document.getElementsByName("algorithms");
        var selectAllAlgorithmsCheckbox = document.getElementById("selectAllAlgorithms");

        for (var i = 0; i < algorithmCheckboxes.length; i++) {
                algorithmCheckboxes[i].checked = selectAllAlgorithmsCheckbox.checked;
        }
}





