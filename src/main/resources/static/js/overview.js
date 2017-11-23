// Wait till the document is loaded before doing anything
$(document).ready(function(){

    // Asks the api for all the rooms
    $.ajax({
        url: "http://localhost:8080/api/hotel/room/all",
        type: "get",
        success: function(result) {
            console.log("this is the data: " + result);
            // If the result is a succes, loop through every room and display the roomdetails in a table
            $.each(result, function(index, room) {
                console.log(room.roomNumber + ", " + room.roomType + ", " + room.roomSize + " " + room.dateAvailable);
                $('#overviewBody').append("<tr><td>" + room.roomNumber + "</td><td>" + room.roomType + "</td><td>" + room.roomSize + "</td><td>" + room.dateAvailable + "</td></tr>");
            });
        }
    });

});