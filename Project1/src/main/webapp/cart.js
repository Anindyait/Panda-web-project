function calcTotal()
{
    let tot_price = (($('#form1').val()) * ($("#price").val()));
    $("#amount").text(tot_price);
}
$(function(){
    $("#form1").on("change keyup",calcTotal)
})       