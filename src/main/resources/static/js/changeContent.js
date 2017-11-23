$(document).ready(function(){
    changeContent("views/dashboard.html");
});

function changeContent(url){
    $('#content').empty();
    $('#content').load(url);
}