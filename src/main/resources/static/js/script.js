
// Wait till the document is loaded before doing anything
$(document).ready(function(){
    $("#submitButton").click(function(e){
        $("#roomAddedMessage").hide();
        $("#roomNotAddedError").hide();

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

        var valid = true;

        // Check the room number.
        if(roomNumber == null || roomNumber === "" || roomNumber < 1){
            $("#roomNumberError").html("Roomnumber must be a number above 0");
            valid = false;
        }
        else{
            $("#roomNumberError").html("");
        }

        // Check the date
        if(dateAvailable == null || dateAvailable === ""){
            $("#dateError").html("Date must be a valid date, dd/mm/yyyy");
            valid = false;
        } else{
            $("#dateError").html("");
        }

        if(!valid) return;

        var roomString = JSON.stringify(room);

        $.ajax({
            url: "http://localhost:8080/api/hotel/room/add",
            type: "post",
            data: roomString,
            contentType: "application/json",
            success: function(result) {
                if(result == true){
                    $("#roomAddedMessage").show();
                }
                else{
                    $("#roomNotAddedError").show();
                }
            }
        });

    });
});

