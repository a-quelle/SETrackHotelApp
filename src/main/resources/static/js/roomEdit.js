// get data of datatable
function getObjectAndSetInputFields(row) {

    // get object of the row
    var table = $("#roomTable").DataTable();
    // save the id of the current object in the variable
    var dataObject = table.row(row).data();

    console.log("Dataobject is: ");
    console.log(dataObject);

    // populate inputfields in the modal
    currentId = dataObject.id;
    $("#roomNumberInput").val(dataObject.roomNumber);
    $("#roomTypeInput").val(dataObject.roomType);
    $("#roomSizeInput").val(dataObject.roomSize);

    /// Opens the modal in the overview
    $('#roomModal').modal('show');
}

// Get selected room
function getSelectRoom(){
    console.log("We selected a room and clicked edit.")
    $('#roomTable > tbody > tr.selected').each(function(i,row){
        getObjectAndSetInputFields(row);
    });
}

$(document).ready(function(){

    // get selected room and remove
    function removeSelectedRoom(){

        $('#roomTable > tbody > tr.selected').each(function(i,row){
            getRoomAndRemoveIt(row);
        });
    }
});

// get data of datatable
function getRoomAndRemoveIt(row){
    // get object of the row
    var table = $("#roomTable").DataTable();
    // make a delete request
    var dataObject = table.row(row).data();

    $.ajax({

        url:"http://localhost:8080/api/hotel/rooms/delete",
        type:"delete",
        data: JSON.stringify(dataObject),
        contentType: "application/json",

        success: function(result){
            fillDataBase();
            $("#roomRemovedMessage").show();
        }
    });
}