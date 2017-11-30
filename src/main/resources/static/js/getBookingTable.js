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
    });

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

    $('#DataTableBooking').on('click', 'tbody tr', function(evt){
        // do not trigger if clicked on checkbox
        var cell=(evt.target).closest('td');
        
        if($(cell).index() > 0){
            getObjectAndSetInputFields(this);
        }
    });
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
    $('#bookingNr').val(dataObject.bookingNr);
    $('#guestSelect').val(dataObject.guest).attr("s‌​elected", "selected").selectmenu('refresh');
    $('#roomSelect').selectmenu('refresh').val(dataObject.room).attr("s‌​elected", "selected");
    $('#startDate').val(dataObject.startDate);
    $('#endDate').val(dataObject.endDate);
    $('#checkedIn').val(dataObject.checkedIn).change();

    $('#bookingModal').modal('show');
}

function clearForm(){
    $('#bookingModalContainer > div.container > form').find("input[type=date]").val("");
}