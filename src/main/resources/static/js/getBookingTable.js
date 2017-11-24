function getData(){
    console.log("get data")

    $.ajax({
    url: "http://localhost:8080/all",
    type:"get",
    success: function(getBooking){
        console.log("returned from get:" + getBooking)
           $("#DataTableBooking").DataTable().clear();
           $("#DataTableBooking").DataTable().rows.add(getBooking);
           $("#DataTableBooking").DataTable().columns.adjust().draw();
        }
    })
}

$(document).ready(
    function (){
        $("#DataTableBooking").DataTable({
            columns: [
               {"data": "bookingNr"}
               {"data": "guest"}
               {"data": "room"}
               {"data": "startDate"}
               {"data": "nrOfNights"}
               {"data": "checkIn"}
            ]
        })
        }
    }
)