/* when first opened initially load tiles for dashboard */
$(document).ready(function(){
    changeContent("views/dashboard.html");
});


/* on click switches main content e.i. dashboard to overview and vice versa */
function changeContent(url){
    $('#content').empty();
    $('#content').load(url);
}
/*  Load a modal into a certain id. */
function loadModal(url, id){
    $(id).empty();
    $(id).load(url);
}