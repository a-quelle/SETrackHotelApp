var guests = [
    {
        "guestNr":1320123,
        "firstName":"Koen",
        "lastName":"Griffioen"
    },
    {
        "guestNr":9876,
        "firstName":"Poen",
        "lastName":"GriffiKoen"
    }
]

//Fill the guest select field with all the elements in the var guests defined above.

$(document).ready(function(){
    for(i=0;i<guests.length;i++) {
        $("#guestSelect").append('<option value="guests[i]">'+guests[i].firstName+' '+guests[i].lastName+'</option>');
    }
})

//Function that checks all the submitted fields upon clicking submit.

function submitClick () {
    var bookingNr;
    var guest;
    var room;
    var startDate;
    var nrOfNights;
    var checkIn;
    bookingNr=Number(document.getElementsByName("bookingNr")[0].value);
    if(isNaN(bookingNr)) {
        document.getElementById("bookingNrText").innerHTML="Booking nr: <font color='red'>This has to be a number!</font>";
    }
    else {
        document.getElementById("bookingNrText").innerHTML="Booking nr:</font>";
        alert(bookingNr);
    }

    room=Number(document.getElementsByName("roomNr")[0].value);
    if(isNaN(room)) {
        document.getElementById("roomNrText").innerHTML="Room nr: <font color='red'>This has to be a number!</font>";
    }
    else {
        document.getElementById("roomNrText").innerHTML="Room nr:</font>";
        alert(room);
    }
    nrOfNights=Number(document.getElementsByName("nrOfNights")[0].value);
    if(isNaN(nrOfNights)) {
            document.getElementById("nrOfNightsText").innerHTML="Number of nights: <font color='red'>This has to be a number!</font>";
    }
    else {
            document.getElementById("nrOfNightsText").innerHTML="Number of nights:</font>";
            alert(nrOfNights);
    }

    var booking = {
        "bookingNr":bookingNr,

    }

    return false;
}