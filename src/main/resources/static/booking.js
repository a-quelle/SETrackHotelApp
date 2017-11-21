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
    },
    {
    }
]

$(document).ready(function(){
    // Zorg dat je de <select>s vult
    for(..????)
})

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
    guest=Number(document.getElementsByName("guestNr")[0].value);
    if(isNaN(guest)) {
        document.getElementById("guestNrText").innerHTML="Guest nr: <font color='red'>This has to be a number!</font>";
    }
    else {
        document.getElementById("guestNrText").innerHTML="Guest nr:</font>";
        alert(guest);
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