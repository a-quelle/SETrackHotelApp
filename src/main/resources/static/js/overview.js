
function getRoomData() {
    console.log("getting data...");

    $.ajax({
        url: "http://localhost:8080/api/hotel/room/all",
        type: "get",
        success: function(rooms) {

             $('#table').DataTable().clear();
             $('#table').DataTable().rows.add(rooms);
             $('#table').DataTable().columns.adjust().draw();
        }
    });
}

// Wait till the document is loaded before doing anything
$(document).ready(function(){
    $('#table').DataTable({
        columns: [
            {"data": "roomNumber"},
            {"data": "roomType"},
            {"data": "roomSize"}
        ]
    });

    getRoomData();

});