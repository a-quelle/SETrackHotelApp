
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

/*
 * Removes a maintenance
 */
function deleteMaintenance(){
    // Gets the table and iterates to find the selected row
    var table = $('#maintenance-table').DataTable();
    $('#maintenance-table > tbody > tr.selected').each(function(i, row){
        dataObject = table.row(row).data();
    });

    // If an object was selected, delete it
    if(dataObject != null){
        // Make a delete request
        $.ajax({
            url:"http://localhost:8080/api/hotel/maintenance/delete",
            type:"delete",
            data: JSON.stringify(dataObject),
            contentType: "application/json",
            success: function(result){
                // Show confirmation!
                $('#deleteConfirmModal').modal('hide');
                $("#maintenanceRemovedMessage").show();
                // Get the bookings again
                getMaintenanceData();
            }
        });
    }
}