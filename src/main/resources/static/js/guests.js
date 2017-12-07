var currentId = null;

$(document).ready(function(){
    getGuests();
    // When the submit button is pressed, retrieve all values from the form


    $('#guestTable').on( 'click', 'tbody tr', function (evt) {
        var cell=(evt.target).closest('td');

        if($(cell).index() > 0){
            getGuestAndSetInputFields(this);
        }
    });
});

// When edit button is pressed modal is filled wit data
// when clicking add guest this function empty the modal
function addGuest(){

    // Remove current Id
    currentId = null;

    $("#guestModal input").each(function(){
       if(this.id !== "submitButton"){
           $(this).val("");
       }
    });
    $("#guestModal").modal("show");
}
// Returns the list of guests
function getGuests(){
    console.log("Getting data..");
    // Clears the table first
    $("#guestTable tbody").empty();
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
                    { "data": "phoneNumber" }
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
        getGuestAndSetInputFields(row);
    });
}

// Remove selected guest
function removeSelectedGuest(){
    $('#guestTable > tbody > tr.selected').each(function(i,row){
        $('#deleteConfirmModal').modal('hide');
        getGuestAndRemoveIt(row);
    });
}

// Remove guest
function getGuestAndRemoveIt(row){
    // Get data of datatable
    var table = $("#guestTable").DataTable();
    // get object of the row
    var dataObject = table.row(row).data();

    // Make a delete request
    $.ajax({
        url:"http://localhost:8080/api/hotel/guests/delete",
        type:"delete",
        data: JSON.stringify(dataObject),
        contentType: "application/json",
        success: function(result){
            // Get the guests again
            fillDataBase();
            // Show confirmation!
            $("#guestRemovedMessage").show();
        }
    });
}

// Populate the modal with a object
function getGuestAndSetInputFields(row) {

    // Get data of datatable
    var table = $("#guestTable").DataTable();
    // get object of the row
    var dataObject = table.row(row).data();
    // Save the id of the current guest in the variable
    currentId = dataObject.id;
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
    $("#documentType").val(dataObject.documentType);
    $("#documentNumber").val(dataObject.documentNumber);
    /// Opens the modal in Guestoverview
    $('#guestModal').modal('show');
}

// Read in data and call the booking Modal function defined below
function getGuestForBooking(){
    $('#guestTable > tbody > tr.selected').each(function(i,row){
        setGuestInBooking(row);
    });
}

// Populate the booking mod
function setGuestInBooking(row) {

    // Get data of datatable
    var table = $("#guestTable").DataTable();
    // get object of the row
    var dataObject = table.row(row).data();
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#add-booking-btn").show();
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#update-booking-btn").hide();
    $('#bookingModalContainer > div.container > form').find("input[type=date]").val("");
    updatedBookingId = null;
    $("#guestSelect").val(dataObject.id);
    initialiseModal();
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
            //update guest list in the booking modal
            getGuestsForBooking();
            // Close the modal
            $("#guestModal").modal("toggle");
            // Get the guests again
            fillDataBase();
            // Show confirmation!
            $("#guestAddedMessage").show();
        }
    });
}

// Posts the data to the server
function putData(guest){
    $.ajax({
        url:"http://localhost:8080/api/hotel/guests/update",
        type:"put",
        data: guest,
        contentType: "application/json",
        success: function(result){
            console.log("Updated guest.");

            // Close the modal
            $("#guestModal").modal("toggle");
            // Get the guests again
            fillDataBase();
            // Show confirmation!
            $("#guestUpdatedMessage").show();
        }
    });
}
