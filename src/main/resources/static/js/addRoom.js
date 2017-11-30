$(document).ready(function(){

    $('#form').validator().on('submit', function (e) {
      if (e.isDefaultPrevented()) {
        // INVALID FORM, DO NOTHING
        alert("");
      } else {
        // Do something.
        postForm();
      }
    });

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
});