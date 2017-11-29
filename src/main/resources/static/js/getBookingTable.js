function getData(){
    console.log("get data")

    $.ajax({
    url: "http://localhost:8080/api/hotel/booking/all",
    type:"get",
    success: function(getBooking){
        console.log("returned from get:" + getBooking)
        $.each(getBooking,function(index, booking){
            console.log("The checkIn parameter is: " +booking.checkIn);
        });

           $("#DataTableBooking").DataTable().clear();
           $("#DataTableBooking").DataTable().rows.add(getBooking);
           $("#DataTableBooking").DataTable().columns.adjust().draw();
        }
    })
}

$(document).ready(function (){
    $("#DataTableBooking").DataTable({
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        order: [[ 1, 'asc' ]],
        columns: [
            {"defaultContent": ""},
            {"data": "guest.lastName"},
            {"data": "room.roomNumber"},
            {"data": "startDate"},
            {"data": "endDate"},
            {"data": "checkIn"}
        ],
        columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }]
    });

    getData();


    //$('#update-modal-content').load(createBooking.html);
});

function getSelectedBooking(){

    // find selected row
    $('#DataTableBooking > tbody > tr.selected').each(function(i, row){

        getObjectAndSetInputFields(row);

    });
}

function getObjectAndSetInputFields(row){
    // get data object
    var table = $('#DataTableBooking').DataTable();
    var dataObject = table.row(row).data();

    console.log(dataObject.id);
    // fill fields
}