$(document).ready(function(){
    // When the submit button is pressed, retrieve all values from the form
    $("#submitButton").click(function(e){

        // Prevent submit to form
        e.preventDefault();

        // Retrieve the values from the form
        var firstNameInput = $("#firstNameInput").val();
        var lastNameInput = $("#lastNameInput").val();
        var addressInput = $("#addressInput").val();
        var houseNumberInput = $("#houseNumberInput").val();
        var zipCodeInput = $("#zipCodeInput").val();
        var cityInput = $("#cityInput").val();
        var countryInput = $("#countryInput").val();
        var phoneNumberInput = $("#phoneNumberInput").val();
        var dateOfBirthInput = $("#dateInput").val();
        var emailInput = $("#emailInput").val();
        var checkBoxConfirmation = $("#checkBoxConfirmation").val();

        // Log all values to the console.
        console.log(firstNameInput);
        console.log(lastNameInput);
        console.log(addressInput);
        console.log(houseNumberInput);
        console.log(zipCodeInput);
        console.log(cityInput);
        console.log(countryInput);
        console.log(phoneNumberInput);
        console.log(dateOfBirthInput);
        console.log(emailInput);
        console.log(checkBoxConfirmation);

        // Sends a guest JSON object without ID
        var guest = {
            firstName: firstNameInput,
            lastName: lastNameInput,
            streetName: addressInput,
            zipCode: zipCodeInput,
            city: cityInput,
            country: countryInput,
            houseNumber: houseNumberInput,
            phoneNumber: phoneNumberInput,
            emailAddress: emailInput,
            birthDate: dateOfBirthInput
        };

        // Checks if any fields are left empty
        var emptyFields = false;
        $.each(guest, function(index, element) {
            if(element == ""){
                // Prompts the user about empty fields
                alert("Fill in all fields!");
                emptyFields = true;
            }
        });

        // If there are empty fields, stop the function and don't add the guest
        if(emptyFields){
            return;
        }

        // Make a JSON String out of the object
        var JSONGuest = JSON.stringify(guest);
        console.log(JSONGuest);

        // Send the data to postData
        postData(JSONGuest);
        changeContent('views/guestsoverview.html');
    });
});

// Returns the list of guests
function getGuests(){
    console.log("Getting data..");
    // Clears the table first
    $("#tbodyid").empty();
    $.ajax({
        // Ajax get request
        url:"http://localhost:8080/api/hotel/guests/all",
        type:"get",
        success: function(result){
            console.log("This is the data:" + result);

            // On success, fill the database with al fields from JSON
            $('#guestTable').DataTable( {
                data: result,
                select:{
                    style: 'os',
                    selector: 'td:first-child'
                },
                order:[[1,'asc']],
                columns: [
                    { "defaultContent": "" },
                    { "data": "firstName" },
                    { "data": "lastName" },
                    { "data": function(data){
                        return data.streetName + " " + data.houseNumber;
                    }},
                    { "data": "zipCode" },
                    { "data": "city" },
                    { "data": "country" },
                    { "data": "phoneNumber" },
                    { "data": "guestNr" }
                ],
                columnDefs:[{
                    orderable:false,
                    className:'select-checkbox',
                    targets: 0
                }]
            });
            // After setting all values, fill the database.
            fillDataBase();
        }
    })
}

// Only click on the row after the checkbox
function getSelectGuest(){
    $('#guestTable > tbody > tr.selected').each(function(i,row){
        getObjectAndSetInputFields(row);
    });
}

// Populate the modal with a object
function getObjectAndSetInputFields(row) {

    // Get data of datatable
    var table = $("#guestTable").DataTable();
    // get object of the row
    var dataObject = table.row(row).data();
    // Populate al inputfield in the modal
    $("#firstNameInput").val(dataObject.firstName);
    $("#lastNameInput").val(dataObject.lastName);
    $("#addressInput").val(dataObject.streetName);
    $("#houseNumberInput").val(dataObject.houseNumber);
    $("#zipCodeInput").val(dataObject.zipCode);
    $("#cityInput").val(dataObject.city);
    $("#countryInput").val(dataObject.country);
    $("#phoneNumberInput").val(dataObject.phoneNumber);
    $("#countryInput").val(dataObject.country);
    $("#dateInput").val(dataObject.birthDate);
    $("#emailInput").val(dataObject.emailAddress);
    /// Opens the modal in Guestoverview
    $('#myModal').modal('show');
}

// Fills the database with the values
function fillDataBase(){
    $.ajax({
        // Gets all JSON guests
        url:"http://localhost:8080/api/hotel/guests/all",
        type:"get",
        success: function(guests){
            // On success, fills in all fields
            $('#guestTable').DataTable().clear();
            $('#guestTable').DataTable().rows.add(guests);
            $('#guestTable').DataTable().columns.adjust().draw();
        }
    });
}

// Posts the data to the server
function postData(guest){
    $.ajax({
        url:"http://localhost:8080/api/hotel/guests/add",
        type:"post",
        data: guest,
        contentType: "application/json",
        success: function(result){
            console.log("Added guest.");
        }
    });
}



