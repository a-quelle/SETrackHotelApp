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
            {"data": "bookingNr", "className": "id"}, // todo: add invisible column with booking id, for now bookingNr functions as id
            {"data": "guest.lastName"},
            {"data": "room.roomNumber"},
            {"data": "startDate"},
            {"data": "nrOfNights"},
            {"data": "checkIn"}
        ],
        columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }],
    });

    getData();


    //$('#update-modal-content').load(createBooking.html);
});

function getSelectedBooking(){
    var found = false;
    $('#DataTableBooking > tbody > tr.selected').each(function(i, row){
        // get value of the id
        var cell = $(row).children("td.id").html();
        if(cell){
            console.log(cell);
            found = true;
            return cell;
        }

    });
    if(!found)
        console.log("no booking is selected");
}