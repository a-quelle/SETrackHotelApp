

var guests={};
var rooms = {};
var updatedBookingId;
var date1;
var date2;
var submitCheck;

console.log(date1);
console.log(date2);


function changeSubmitCheckToOne(){
    submitCheck = 1;
}

function changeSubmitCheckToZero(){
    submitCheck = 0;
}

$(document).ready(function(){

    $('#bookingForm').validator();
    $('#bookingForm').on('submit', function (e) {
        if (e.isDefaultPrevented()) {
        } else {
            e.preventDefault();
            console.log("submitcheck is "+submitCheck);
            if(submitCheck==1) {
                console.log("submit button");
                submitClick();
            } else {
                console.log("update button");
                updateClick();
            }


        }
     })

     $('#blockRoomForm').validator();
         $('#blockRoomForm').on('submit', function (e) {
             if (e.isDefaultPrevented()) {
             } else {
                 e.preventDefault();
                 submitBlockedRoom();


             }
          })


});


function getGuestsForBooking() {
    console.log("getting guests...")

    $.ajax({
        url:"http://localhost:8080/api/hotel/guests/all",
        type:"get",
        success: function(result) {
            console.log("This is now the guestlist: " + result);
           $("#guestSelect").empty();
            for(i=0;i<result.length;i++) {
                    // add guest to dictionary
                    guests[result[i].id] = result[i];
                    console.log("voeg gast toe");
                    $("#guestSelect").append('<option value=' + result[i].id +'>' + result[i].firstName + ' ' + result[i].lastName+ ' ' + result[i].zipCode+ '</option>');
                    console.log(result[i].firstName);
                }
        }
    })

}

$(document).ready(getGuestsForBooking());

/* update available rooms on a date change */
$("#startDate").change(function() {
    date1 = $("#startDate").val();
    if(date1 != null && date1 != "" && typeof date1 != 'undefined'){
        //If date2 exists, check if it is greater than date 1 and get rooms only if it is.
        if(date2 != null && date2 != "" && typeof date2 != 'undefined'){
                    if ((new Date(date2)).getTime()>(new Date(date1)).getTime()) {
                        getAvailableRoomsForBooking();
                        $("#startDateText").html("");
                        $("#endDateText").html("");
                    } else {
                        $("#startDateText").html("<font color='red'>Start date cannot be after end date.</font>");
                    }
        } else {
            // If date2 not yet set, just get available rooms and fill the select accordingly
            getAvailableRoomsForBooking();
        }
    }
});

/* update available rooms on a date change */
$("#endDate").change(function() {
    date2 = $("#endDate").val();
    if(date2 != null && date2 != "" && typeof date2 != 'undefined'){
            //If date1 exists, check if it is before than date 2 and get rooms only if it is.
            if(date1 != null && date1 != "" && typeof date1 != 'undefined'){
                        if ((new Date(date2)).getTime()>(new Date(date1)).getTime()) {
                            getAvailableRoomsForBooking();
                            $("#startDateText").html("");
                            $("#endDateText").html("");
                        } else {
                            $("#endDateText").html("<font color='red'>End date cannot be before start date</font>");
                        }
            } else {
                // If date1 not yet set, just get available rooms and fill the select accordingly
                getAvailableRoomsForBooking();
            }
        }
});

function getAvailableRoomsForBooking() {
/* Send request to the api to append the available rooms to the rooms select object only if both dates have been filled in.*/
    console.log(date1);
    console.log(date2);

    if(date1 != null && date1 != "" && typeof date1 != 'undefined'){
    if(date2 != null && date2 != "" && typeof date2 != 'undefined'){

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
                    getRoomsForBooking();
                    appendRooms(result);
                }
            });
        }
    } else {console.log("end date not filled in yet")}} else {console.log("start date not filled in yet")}
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

$(document).ready(getAvailableRoomsForBooking());

//Variables that have to be read from the input form
var booking = {};

//Function that checks all the submitted fields upon clicking submit.

function submitClick () {

    readInput();
    booking.id = null;
    submitInput();

}

/* Function that check all submitted fields and send an update request to the api */
function updateClick() {
    readInput();
    booking.id = updatedBookingId;
    updateInput();
}

//Typechecks all the input fields to make sure they are of the correct type.

function readInput () {
    booking.endDate=$("#endDate").val();
    booking.guest= guests[$("#guestSelect").val()];
    booking.room = rooms[$("#roomSelect").val()];
    console.log(booking.room);
    booking.startDate = $("#startDate").val();
    booking.checkIn = $("#checkedIn").val() === "true";
    console.log(booking)
}

$(document).ready(function(){
    getRoomsForBooking();
});

function getRoomsForBooking() {
    console.log("getting rooms...")

    $.ajax({
        url:"http://localhost:8080/api/hotel/room/all",
        type:"get",
        success: function(result) {
           console.log("These are the rooms: " + result);
            for(i=0;i<result.length;i++) {
                   // add room to dictionary
                    rooms[result[i].id] = result[i];
            }
        }
    })
}

// Blocked room object
var blockedRoom = {};

function submitBlockedRoom(){
    getBlockedRoomInput();
    postBlockedRoom();
}

function getBlockedRoomInput(){
    blockedRoom.id = null;
    blockedRoom.endDate = $("#endDate").val();
    blockedRoom.room = rooms[$("#roomSelect").val()];
    console.log(JSON.stringify(blockedRoom.room));
    blockedRoom.reason = $("#reasonInput").val();
    blockedRoom.message = $("#optionalInput").val();
    blockedRoom.startDate = $("#startDate").val();
    console.log("Hahahhahahahahahahhahaha")
}

function postBlockedRoom(){
    var blocked = JSON.stringify(blockedRoom);
        console.log(blocked);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/api/hotel/maintenance/add",
            data: blocked,
            contentType: "application/json",
            success: function(){
                console.log("Posted data to server.");
                //getMaintenanceData();
                // Close modal
                $("#blockRoomModal").modal("toggle");
                $("#bookingAddedMessage").show();
            }
        });
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
