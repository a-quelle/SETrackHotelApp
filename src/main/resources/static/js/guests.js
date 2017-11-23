$(document).ready(function(){

    // When the CheckBox is ticked, store its current state
    $("#checkBoxConfirmation").click(function(e){
        var isChecked = $('#checkBoxConfirmation:checked');
        console.log(isChecked);
    });

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

        var emptyFields = false;
        $.each(guest, function(index, element) {
            if(element == ""){
                console.log("Fill in all fields!");
                emptyFields = true;
            }
        });

        if(emptyFields){
            return;
        }

        var JSONGuest = JSON.stringify(guest);
        console.log(JSONGuest);

        postData(JSONGuest);

    });
});

function getGuests(){
    console.log("Getting data..");
    $("#tbodyid").empty();
    $.ajax({
        url:"http://localhost:8080/api/guests/all",
        type:"get",
        success: function(result){
            console.log("This is the data:" + result);

            $('#guestTable').DataTable( {
                data: result,
                columns: [
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
                ]
            });
            fillDataBase();
        }
    })
}

function fillDataBase(){
    $.ajax({
        url:"http://localhost:8080/api/guests/all",
        type:"get",
        success: function(guests){
            $('#guestTable').DataTable().clear();
            $('#guestTable').DataTable().rows.add(guests);
            $('#guestTable').DataTable().columns.adjust().draw();
        }
    });
}