$(document).ready(function(){
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

        if(currentId !== null){
            putData(JSONGuest);
        }
        else {
            // Send the data to postData
            postData(JSONGuest);
        }
    });
});