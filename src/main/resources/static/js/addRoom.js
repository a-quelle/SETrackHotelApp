$(document).ready(function(){

    // Click function
    function postForm(){
        $("#roomAddedMessage").hide();
        $("#roomNotAddedError").hide();

        var roomNumber = $("#roomNumber").val();
        var roomType = $("#roomType").val();
        var roomSize = $("#roomSize").val();


        var room = {
            roomNumber: roomNumber,
            roomType: roomType,
            roomSize: roomSize
        };

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

    };

    $('#roomForm').validator();
    $('#roomForm').on('submit', function(e){
        if(e.isDefaultPrevented()){
        }
        else{
            e.preventDefault();
            console.log("Submitcheck is ..." + submitCheck);
            if (submitCheck==1){
                console.log("submit button");
                postForm();
            }
            else {
                console.log('update button');
                updateClick();
            }
        }
    });
});

var submitCheck;
var updatedRoomId;
var room={};

function changeSubmitCheckToOne(){
    submitCheck=1;
}

function changeSubmitCheckToZero(){
    submitCheck=0;
}

function updateClick(){

    room.id = updatedRoomId;
    room.roomNumber = $("#roomNumber").val();
    room.roomType = $("#roomType").val();
    room.roomSize = $("#roomSize").val();

    var roomString = JSON.stringify(room);

    console.log(roomString);

     $.ajax({
        url: "http://localhost:8080/api/hotel/room/update" ,
        type: "put",
        data: roomString,
        contentType: "application/json",
        success: function(result) {
            // Toggle modal
            $("#roomModal").modal("toggle");
            // Refresh dataTable
            getRoomData();
        }
     });
}


// in the room modal switch update button for submit button
function onSubmit(){
    $('#roomModalContainer > div.container > form').find("#updateButton").hide();
    $('#roomModalContainer > div.container > form').find("#submitButton").show();

    updatedRoomId = null;
}

