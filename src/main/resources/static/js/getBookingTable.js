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
            getObjectAndSetInputFields(this);
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

        getObjectAndSetInputFields(row);

    });
}

/* Get dataobject from the datatable and update the input fields of the edit form */
function getObjectAndSetInputFields(row){
    // get data object
    var table = $('#DataTableBooking').DataTable();
    var dataObject = table.row(row).data();
    updatedBookingId = dataObject.id;
    initialiseModal(dataObject);
    $('#bookingModal').modal('show');
}


/* Clear the date fields of the form and set add button visible */
function clearForm(){
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#add-booking-btn").show();
    $('#bookingModalContainer > div.container > form').find("#submit-buttons").find("#update-booking-btn").hide();
    $('#bookingModalContainer > div.container > form').find("input[type=date]").val("");
    updatedBookingId = null;
    initialiseModal();
}

/* Initialises the createBooking modal. It gets all available rooms, and when we update a booking, it prefills all the slots.
 This copies the functionality from getAvailableRooms, but it also fills the fields in case of an update.
 This is necessary because the get request is asynchronous. Uses appendRooms defined below.*/
function initialiseModal(dataObject) {
    console.log("getting rooms...")

    $("#roomSelect").empty();

    // get date values
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();

    var dates = {
        startDate:startDate,
        endDate:endDate
    };

    var JSONDates = JSON.stringify(dates);

    if(updatedBookingId != null){ // if we are updating a booking, set booking id so it is omitted from the available rooms check

    console.log("update booking");
        $.ajax({
            url: "http://localhost:8080/api/hotel/room/available/" + updatedBookingId,
            type:"post",
            data: JSONDates,
            contentType: "application/json",
            success: function(result) {
                // fill fields
                appendRooms(result);
                $('#guestSelect').val(dataObject.guest.id);
                $('#roomSelect').val(dataObject.room.id);
                $('#startDate').val(dataObject.startDate);
                $('#endDate').val(dataObject.endDate);
                $('#checkedIn').val(JSON.stringify(dataObject.checkIn));

            }
        });
    }
    else{ // else a new booking is created, so just get the available rooms

    console.log("create new booking");

        $.ajax({
            url: "http://localhost:8080/api/hotel/room/available",
            type:"post",
            data: JSONDates,
            contentType: "application/json",
            success: function(result) {
                appendRooms(result);
            }
        });
    }


}

/* Add rooms to the roomSelect object. */
function appendRooms(result){
    console.log("append rooms");
    $("#roomSelect").empty();
    console.log("These are the rooms: " + result);
    for(i=0;i<result.length;i++) {
            $("#roomSelect").append('<option value='+result[i].id +'>'+result[i].roomNumber+'</option>');
    }
}