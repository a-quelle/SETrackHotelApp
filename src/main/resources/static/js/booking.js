//Function to get the list of guests from the server. This functionality is not present serverside yet.

/*
function getGuests() {
    $("#data").empty();
    console.log("getting data...")

    $.ajax({
        url:"http://localhost:8080/api/books/all",
        type:"get",
        success: function(books) {
            console.log("This is the data: " + books);

            $.each(books,function(index, book){
                console.log(book.id + " " + book.title + " " + book.author);
                $("#data").append("Id: " +book.id + ", title: " +book.title + ", author: " +book.author + "<br>");
            })
        }
    })

}
*/
//hardcoded list of guests absent the previous functionality.

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

//Fill the guest select field of createBooking.html with all the elements in the var guests defined above.

$(document).ready(function(){
    for(i=0;i<guests.length;i++) {
        $("#guestSelect").append('<option value='+i+'>'+guests[i].firstName+' '+guests[i].lastName+'</option>');
    }
})

//Function to get the list of rooms from the server. This functionality is not present serverside yet.

/*
function getGuests() {
    $("#data").empty();
    console.log("getting data...")

    $.ajax({
        url:"http://localhost:8080/api/books/all",
        type:"get",
        success: function(books) {
            console.log("This is the data: " + books);

            $.each(books,function(index, book){
                console.log(book.id + " " + book.title + " " + book.author);
                $("#data").append("Id: " +book.id + ", title: " +book.title + ", author: " +book.author + "<br>");
            })
        }
    })

}
*/
//Hardcoded list of rooms, absent the previous functionality
var rooms = [
    {
        "roomNr":1
    },
    {
        "roomNr":2
    },
    {
        "roomNr":3
    }
]

//Fill the rooms select field of createBooking.html with all the elements in the var guests defined above.

$(document).ready(function(){
    for(i=0;i<rooms.length;i++) {
        $("#roomSelect").append('<option value='+i+'>'+rooms[i].roomNr+'</option>');
    }
})

//Variables that have to be read from the input form

var bookingNr;
var guest;
var room;
var startDate;
var nrOfNights;
var checkIn;

//Function that checks all the submitted fields upon clicking submit.

function submitClick () {
    readInput();
    if(checkInput()) {
        submitInput();
    }
}

//Typechecks all the input fields to make sure they are of the correct type.

function readInput () {
    bookingNr=$("#bookingNr").val();
    console.log(bookingNr);
    nrOfNights=$("#nrOfNights").val();
    console.log(nrOfNights);
    guest= guests[$("#guestSelect").val()];
    console.log(guest);
    room= rooms[$("#roomSelect").val()];
    console.log(room);
    startDate=$("#startDate").val();
    console.log(startDate);
    checkIn=$("#checkedIn").val();
    console.log(checkIn);

}

function checkInput () {
    var check =true;

    if(isNaN(bookingNr)) {
        document.getElementById("bookingNrText").innerHTML="Booking nr: <font color='red'>This has to be a number!</font>";
        check=false;
    }
    else {
        document.getElementById("bookingNrText").innerHTML="Booking nr:</font>";
    }
    if(isNaN(nrOfNights)) {
        document.getElementById("nrOfNightsText").innerHTML="Number of nights: <font color='red'>This has to be a number!</font>";
        check=false;
    }
    else {
        document.getElementById("nrOfNightsText").innerHTML="Number of nights:</font>";
    }

    console.log(check);
    return check;
}

function submitInput () {
    console.log("Made it!");
}
