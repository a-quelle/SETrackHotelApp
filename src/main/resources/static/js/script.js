
// Wait till the document is loaded before doing anything
$(document).ready(function(){
    $("#submitButton").click(function(e){
    // Prevent form submit
        e.preventDefault();

        var roomNumber = $("#roomNumber").val();
        var roomType = $("#roomType").val();
        var roomSize = $("#roomSize").val();
        var dateAvailable = $("#dateAvailable").val();

        var room = {
            roomNumber: roomNumber,
            roomType: roomType,
            roomSize: roomSize,
            dateAvailable: dateAvailable
        };

        console.log(room);

        var roomString = JSON.stringify(room);

        $.ajax({
            url: "http://localhost:8080/api/hotel/room/add",
            type: "post",
            data: roomString,
            contentType: "application/json",
            success: function(result) {
                console.log("YES, GELUKT!!! AAAAAAH");
            }
        });

    });
});

