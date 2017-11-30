//Function to get the list of guests from the server. This functionality is not present serverside yet.
var guests={};

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

//hardcoded list of guests absent the previous functionality.

$(document).ready(getGuests());


//Fill the guest select field of createBooking.html with all the elements in the var guests defined above.

//Function to get the list of rooms from the server. This functionality is not present serverside yet.

var rooms = {};

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

    if(!booking.endDate) {
        document.getElementById("nrOfNightsText").innerHTML="<font color='red'>This has to be a number!</font>";
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
