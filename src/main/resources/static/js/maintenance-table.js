
function getMaintenanceData() {

    $.ajax({
        url: "http://localhost:8080/api/hotel/maintenance/all",
        type: "get",
        success: function(maintenance) {

             $('#maintenance-table').DataTable().clear();
             $('#maintenance-table').DataTable().rows.add(maintenance);
             $('#maintenance-table').DataTable().columns.adjust().draw();
        }
    });
}

// Wait till the document is loaded before doing anything
$(document).ready(function(){
    $('#maintenance-table').DataTable({
        select:{
            style: 'os',
            selector: 'td:first-child'
        },
        order:[[1,'asc']],
        columns: [
            {"defaultContent": ""},
            {"data": "room.roomNumber"},
            {"data": "startDate"},
            {"data": "endDate"},
            {"data": "reason"},
            {"data": "message"}
        ],
        columnDefs:[{
            orderable:false,
            className:'select-checkbox',
            targets: 0
        }]
    });

    getMaintenanceData();

});