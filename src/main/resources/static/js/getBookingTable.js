function getData(){
    console.log("get data")

    $.ajax({
    url: "http://localhost:8080/api/hotel/booking/all",
    type:"get",
    success: function(getBooking){
        console.log("returned from get:" + getBooking)
        $.each(getBooking,function(index, booking){
            console.log("The checkIn parameter is: " +booking.checkIn);
        });

           $("#DataTableBooking").DataTable().clear();
           $("#DataTableBooking").DataTable().rows.add(getBooking);
           $("#DataTableBooking").DataTable().columns.adjust().draw();
        }
    });

}

$(document).ready(function (){
    $("#DataTableBooking").DataTable({
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        order: [[ 1, 'asc' ]],
        columns: [
            {"defaultContent": ""},
            {"data": "guest.lastName"},
            {"data": "room.roomNumber"},
            {"data": "startDate"},
            {"data": "endDate"},
            {"data": "checkIn"}
        ],
        columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }]
    });

    getData();

    /* Set onclick function for the edit button */
    $('#DataTableBooking').on('click', 'tbody tr', function(evt){
        // show update button and hide add button
        $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#add-booking-btn").hide();
        $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#update-booking-btn").show();

        // do not trigger if clicked on checkbox
        var cell=(evt.target).closest('td');
        
        if($(cell).index() > 0){
            getBookingAndSetInputFields(this);
        }
    });
});

/* Get booking for the selected row */
function getSelectedBooking(){
    // show update button and hide add button
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#add-booking-btn").hide();
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#update-booking-btn").show();

    // find selected row
    $('#DataTableBooking > tbody > tr.selected').each(function(i, row){

        getBookingAndSetInputFields(row);

    });
}

/* Get dataobject from the datatable and update the input fields of the edit form */
function getBookingAndSetInputFields(row){
    // get data object
    var table = $('#DataTableBooking').DataTable();
    var dataObject = table.row(row).data();

    updatedBookingId = dataObject.id;
    initialiseModal(dataObject);
}


/* Clear the date fields of the form and set add button visible */
function clearForm(){
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#add-booking-btn").show();
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#update-booking-btn").hide();
    $('#bookingModalContainer > div.container > form').find("input[type=date]").val("");

    updatedBookingId = null;
    initialiseModal();
}

/* Initialises the createBooking modal. When we update a modal, it prefills all the slots.*/

function initialiseModal(dataObject) {
    date1 = null;
    date2 = null;
    $("#startDateText").html("");
    $("#endDateText").html("");
    $("#roomSelect").empty();

    if(updatedBookingId != null){ // if we are updating a booking, set booking id so it is omitted from the available rooms check


        $('#roomSelect').val(dataObject.room.id);
        $('#guestSelect').val(dataObject.guest.id);
        $('#startDate').val(dataObject.startDate);
        $('#endDate').val(dataObject.endDate);
        $('#checkedIn').val(JSON.stringify(dataObject.checkIn));
        date1 = dataObject.startDate;
        date2 = dataObject.endDate;
    }

    $('#bookingModal').modal('show');
}


function toggleCheckedIn() {

    var row = $('#DataTableBooking > tbody > tr.selected')[0];
    var table = $('#DataTableBooking').DataTable();
    var dataObject = table.row(row).data();
    if (dataObject.checkIn == true) {
        dataObject.checkIn = false;
    } else {
        dataObject.checkIn = true;
    }
    console.log(dataObject);

    var json = JSON.stringify(dataObject);

    $.ajax({
            type: "put",
            url: "http://localhost:8080/api/hotel/booking/update",
            data: json,
            contentType: "application/json",
            success: function(){
                console.log("...posted");
                getData();
                $('#bookingUpdateMessage').show();
            }
    });

};