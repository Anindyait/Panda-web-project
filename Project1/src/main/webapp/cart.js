function calcTotal()
{
    var inputs = document.getElementsByClassName("quant");
    var prices = document.getElementsByClassName("price");
    var amt = document.getElementsByClassName("amount");
    var amount = [];

    for (var i = 0; i < inputs.length; i++)
    {
        var inp = parseInt(inputs[i].value);
        var pri = parseInt(prices[i].innerHTML);
        amount[i] = inp * pri;
        console.log(i, inp, pri, amount[i]);
        amt[i].innerHTML = amount[i];

    }   
        
}  

function RemovefromCart(y)
{     
    y.parentElement.parentElement.parentElement.remove();
}