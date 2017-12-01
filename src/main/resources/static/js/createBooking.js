

var guests={};
var rooms = {};
var updatedBookingId;
var date1;
var date2;

console.log(date1);
console.log(date2);

/* Send a get request to the api to get a list of al guests and append them to the guest select object */
function getGuests() {
    console.log("getting guests...")

    $.ajax({
        url:"http://localhost:8080/api/hotel/guests/all",
        type:"get",
        success: function(result) {
            console.log("This is now the guestlist: " + result);
            for(i=0;i<result.length;i++) {
                    // add guest to dictionary
                    guests[result[i].id] = result[i];

                    console.log("voeg gast toe");
                    $("#guestSelect").append('<option value=' + result[i].id +'>' + result[i].firstName + ' ' + result[i].lastName+'</option>');
                    console.log(result[i].firstName);
                }
        }
    })

}

$(document).ready(getGuests());

/* update available rooms on a date change */
$("#startDate").change(function() {
    date1 = $("#startDate").val();
    console.log(date1);
    if(date1 != null && date1 != "" && typeof date1 != 'undefined'){
        // Get available rooms and fill the select accordingly
        getAvailableRooms();
    }
});

/* update available rooms on a date change */
$("#endDate").change(function() {
    date2 = $("#endDate").val();
    console.log(date2);
    if(date2 != null && date2 != "" && typeof date2 != 'undefined'){
            // Get available rooms and fill the select accordingly
            getAvailableRooms();
        }
});

/* Send request to the api to append the available rooms to the rooms select object */
function getAvailableRooms() {
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
                appendRooms(result);
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

/* Add rooms to the roomSelect object */
function appendRooms(result){
    console.log("append rooms");
    $("#roomSelect").empty();
    console.log("These are the rooms: " + result);
    for(i=0;i<result.length;i++) {
            $("#roomSelect").append('<option value='+result[i].id +'>'+result[i].roomNumber+'</option>');
    }
}

//Function to get the list of rooms from the server. This functionality is not present serverside yet.
function getRooms() {
    console.log("getting rooms...")

    $.ajax({
        url:"http://localhost:8080/api/hotel/room/all",
        type:"get",
        success: function(result) {
            console.log("These are the rooms: " + result);
            for(i=0;i<result.length;i++) {
                    // add room to dictionary
                    rooms[result[i].id] = result[i];
                    $("#roomSelect").append('<option value=' + result[i].id + '>' + result[i].roomNumber + '</option>');
               }
        }
    })

}

$(document).ready(getRooms());

//Variables that have to be read from the input form
var booking = {};

//Function that checks all the submitted fields upon clicking submit.
function submitClick () {
    readInput();
    if(checkInput()) {
        submitInput();
    }
}

/* Function that check all submitted fields and send an update request to the api */
function updateClick() {
    readInput();
    booking.id = updatedBookingId;
    if(checkInput()){
        updateInput();
    }
}

//Typechecks all the input fields to make sure they are of the correct type.

function readInput () {
    booking.endDate=$("#endDate").val();
    booking.guest= guests[$("#guestSelect").val()];
    booking.room = rooms[$("#roomSelect").val()];
    booking.startDate = $("#startDate").val();
    booking.checkIn = $("#checkedIn").val() === "true";
    console.log(booking)
}

function checkInput () {
    var check =true;

    if (! booking.endDate) {
                document.getElementById("endDateText").innerHTML="<font color='red'>This has to be a valid date!</font>";
                check=false;
            }

    if (! booking.startDate) {
            document.getElementById("startDateText").innerHTML="<font color='red'>This has to be a valid date!</font>";
            check=false;
        }
    else {
    document.getElementById("startDateText").innerHTML="";
    }

    console.log("Check is: " +check);
    return check;
}


function submitInput () {
    console.log("Posting data to server.");

    var jsonBooking = JSON.stringify(booking);
    console.log(jsonBooking);
    $.ajax({
        type: "post",
        url: "http://localhost:8080/api/hotel/booking/add",
        data: jsonBooking,
        contentType: "application/json",
        success: function(){
            console.log("Posted data to server.");
            getData();
            // Close modal
            $("#bookingModal").modal("toggle");
            $("#bookingAddedMessage").show();
        }
    });
}

/* Send update request for the booking to the api */
function updateInput(){
    console.log("Posting data to server...");

    var jsonBooking = JSON.stringify(booking);
    console.log(jsonBooking);
    $.ajax({
        type: "put",
        url: "http://localhost:8080/api/hotel/booking/update",
        data: jsonBooking,
        contentType: "application/json",
        success: function(){
            console.log("...posted");
            getData();
            // Close modal
            $('#bookingModal').modal("toggle");
            $('#bookingUpdateMessage').show();
        }
    });
}
