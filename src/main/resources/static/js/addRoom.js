$(document).ready(function(){

    // Click function
    $("#submitButton").click(function(e){
        $("#roomAddedMessage").hide();
        $("#roomNotAddedError").hide();

    // Prevent form submit
        e.preventDefault();

        var roomNumber = $("#roomNumber").val();
        var roomType = $("#roomType").val();
        var roomSize = $("#roomSize").val();


        var room = {
            roomNumber: roomNumber,
            roomType: roomType,
            roomSize: roomSize
        };

        var valid = true;

        // Check the room number.
        if(roomNumber == null || roomNumber === "" || roomNumber < 1){
            $("#roomNumberError").html("Roomnumber must be a number above 0");
            valid = false;
        }
        else{
            $("#roomNumberError").html("");
        }

        if(!valid) return;

        var roomString = JSON.stringify(room);

        $.ajax({
            url: "http://localhost:8080/api/hotel/room/add",
            type: "post",
            data: roomString,
            contentType: "application/json",
            success: function(result) {
                // Show result
                $("#roomAddedMessage").show();
                // Toggle modal
                $("#roomModal").modal("toggle");
                // Refresh dataTable
                getRoomData();
            }
        });

    });
});