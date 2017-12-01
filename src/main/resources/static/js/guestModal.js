$(document).ready(function(){
    $("#guestForm").validator();

    $('#guestForm').validator().on('submit', function (e) {
        if (e.isDefaultPrevented()) {
            // handle the invalid form...
        } else {
            // everything looks good!
            // Prevent submit to form
            e.preventDefault();
            getGuestData();
        }
    });

    function getGuestData (){

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

        // If we DO know of an ID, we want to update. So add the id to the guest.
        if(currentId !== null) {
            guest.id = currentId;
        }

        // Make a JSON String out of the object
        var JSONGuest = JSON.stringify(guest);

        if(currentId !== null){
            putData(JSONGuest);
        }
        else {
            // Send the data to postData
            postData(JSONGuest);
        }
    }
});