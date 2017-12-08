// function to make datatable rows selectable
$(document).ready(function(){
    $('#roomTable').on('click', 'tbody tr', function(evt){

        $('#roomModalContainer > div.container > form').find("#updateButton").show();
        $('#roomModalContainer > div.container > form').find("#submitButton").hide();

        var cell = (evt.target).closest('td');
        // check if the checkbox is not selected
        if($(cell).index() > 0){
            getObjectAndSetInputFields(this);
        }
    })
});

// get data of datatable
function getObjectAndSetInputFields(row) {

    // get object of the row
    var table = $("#roomTable").DataTable();
    // save the id of the current object in the variable
    var dataObject = table.row(row).data();

    console.log("Dataobject is: ");
    console.log(dataObject);

    // populate inputfields in the modal
    updatedRoomId = dataObject.id;
    $("#roomNumberInput").val(dataObject.roomNumber);
    $("#roomTypeInput").val(dataObject.roomType);
    $("#roomSizeInput").val(dataObject.roomSize);

    /// Opens the modal in the overview
    $('#roomModal').modal('show');
}

// Get selected room
function getSelectRoom(){
    $('#roomModalContainer > div.container > form').find("#updateButton").show();
    $('#roomModalContainer > div.container > form').find("#submitButton").hide();
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