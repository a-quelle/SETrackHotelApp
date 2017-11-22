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
            console.log("this is the data:" + result);

            $.each(result,function (index,value) {
                // console.log(value.zipCode + " " + value.firstName + " " + value.lastName);
                $('#guestTable').append('<tr><td>'+value.firstName+'</td><td>'+value.lastName+'</td>' +
                    '<td>'+value.zipCode+'</td><td>'+value.guestNr+'</td>' +
                    '<td><button type="button" class="btn btn-default">Edit</button></td></tr>');
            })
        }
    })
}