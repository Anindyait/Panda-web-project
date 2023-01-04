function calcTotal()
{
    var inputs = document.getElementsByClassName("form-control");
    var prices = document.getElementsByClassName("price");
    let amt = document.getElementsByClassName("amount");
    var amount = [];

    for (var i = 0; i < inputs.length; i++)
    {
        var inp = parseInt(inputs[i].value);
        var pri = parseInt(prices[i].innerHTML);
        amount[i] = inp * pri;
        console.log(amount[i]);
        amt[i].innerHTML = amount[i];

    }   
        
}  

function RemovefromCart()
{     
    var x = document.getElementsByClassName("btn btn-info");
    for (var i = 0 ; i < x.length; ++i)
    {
        x[i].addEventListener('click',function(){this.parentElement.parentElement.parentElement.remove()},false);
    }    
}