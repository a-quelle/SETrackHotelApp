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
            {"data": "bookingNr"},
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
});