// Wait till the document is loaded before doing anything
$(document).ready(function(){


    function getData() {
        console.log("getting data...");

        $.ajax({
            url: "http://localhost:8080/api/hotel/room/all",
            type: "get",
            success: function(rooms) {
                console.log(rooms);

                 $('#table').DataTable().clear();
                 $('#table').DataTable().rows.add(rooms);
                 $('#table').DataTable().columns.adjust().draw();
            }
        });
    }


    $('#table').DataTable({
        columns: [
            {"data": "roomNumber"},
            {"data": "roomType"},
            {"data": "roomSize"},
            {"data": "dateAvailable"}
        ]
    });

    getData();

});