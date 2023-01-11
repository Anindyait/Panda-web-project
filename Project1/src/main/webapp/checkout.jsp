
<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <script src="https://code.jquery.com/jquery-3.3.1.js"
			integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous">
	</script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
  <script src="https://kit.fontawesome.com/13deb536c6.js" crossorigin="anonymous"></script>

	<script>
		$(document).on("scroll", function() {
			var pageTop = $(document).scrollTop();
			var pageBottom = pageTop + $(window).height();
			var tags = $(".tag");
		  
			for (var i = 0; i < tags.length; i++) {
			  var tag = tags[i];
			  if ($(tag).position().top < pageBottom) {
				$(tag).addClass("visible");
			  } else {
				$(tag).removeClass("visible");
			  }
			}
		  });
	</script>

	<script>
		$(function () {
			$("#header").load("header.jsp");
			$("#footer").load("footer.html");
		});
	</script>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Checkout</title>
    <link rel="icon" href="Pics/panda.png">
    
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <div class = "font">
            <div id = "header"></div>
            <div class = "header-adjustment"></div>
            <br>
                
                
                <div class = "container-fluid">
                        
                        <div class="row">
                            <div class="col-lg">
                            <div class = "Checkout-Container">
                                <div class = "Header-cart">
                                    <h3 class = "Heading-cart">All items</h3>
                                </div>
								<%= request.getAttribute("order_list")%>
                                <br>
                                <br>
                                
                            </div>  
                            <br>
                            </div>
                            
                            <div class="col-lg">
                                <div class="Checkout-Container">
                                    <br>
                                    <div class = "Header-cart">
                                        <div class="row">
                                            <div class="col">
                                                <div class = "checkout-amount" style="font-size:25px">Sub-total:</div>
                                            </div>
                                            <div class="col">
                                                <div class="prices tot" style="font-size:25px">0</div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <div style="text-align: center;">Delivery charges:</div>
                                            </div>
                                            <div class="col">
                                                <div class="prices">+&nbsp&#8377;&nbsp99</div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <div class = "checkout-amount">Total:</div>
                                            </div>
                                            <div class="col">
                                                <div class="prices tot">0</div>
                                            </div>
                                        </div>
                                        
                                    </div>
                                    
                                    <div class="checkout-block">
                                        <h1 style="font-size: 33px;opacity:90%">1. Payment options</h1>
                                        
                                        <div class="radio-buttons">
                                                <div class="form-check">
                                                    <input class="form-check-input payment-radio" type="radio" name="Radio" id="Radio1" value="option1">
                                                    <label class="payment-radio-label" for="Radio1">Cash On Delivery
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input payment-radio" type="radio" name="Radio" id="Radio2" value="option2">
                                                    <label class="payment-radio-label" for="Radio2">UPI / QR Code
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input payment-radio" type="radio" name="Radio" id="Radio3" value="option2">
                                                    <label class="payment-radio-label" for="Radio3">Net Banking
                                                    </label>
                                                </div>
                                                <div id="payment-select"></div>
                                          </div>
                                          
                                    </div>
                                    <div class="checkout-block">
                                          <h1 style="font-size: 33px;opacity:90%;">2. Delivery address</h1>
                                          <div class="delivery-address">
                                            <i class="fa-solid fa-location-dot"></i>
											<%= request.getAttribute("address")%>
                                        </div>
                                    </div>
                                    <div class="checkout-block" style="text-align:center">
                                        <button type="button" value="place_order" class="btn btn-warning Order" id="place_order_button" disabled>Place Order</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>            
        </div>
        <br>
        <br>
	<div id="footer"></div>
    <script>

    window.onload = calcTotal();

    function calcTotal()
    {
        var qt = document.getElementsByClassName("qt");
        var prices = document.getElementsByClassName("price");
        var amt = document.getElementsByClassName("amount");
        var tot_amount = document.getElementsByClassName("tot");
        var amount = [];
        var total = 0;

        for(var i=0; i < qt.length; i++)
        {
            amount[i] = Number(prices[i].innerHTML.slice(2)) * qt[i].innerHTML.slice(2);
            amt[i].innerHTML = "&#8377;&nbsp" + amount[i];

            total = total + amount[i];

        }

        tot_amount[0].innerHTML = "&#8377;&nbsp" + total;
        tot_amount[1].innerHTML = "&#8377;&nbsp" + (total+99);

    }
    
    var placeOrderButton = document.getElementById("place_order_button");
    placeOrderButton.addEventListener("click", checkout);

    var paymentRadios = document.querySelectorAll(".payment-radio");
	document.getElementById("payment-select").innerHTML="Select a payment option";
	document.getElementById("payment-select").style.color = "green";
	
	for (var i = 0; i < paymentRadios.length; i++) 
	{
	    paymentRadios[i].addEventListener("change", enablePlaceOrderButton);
	}

    var payment = "";

 	function enablePlaceOrderButton() 
 	{
	     var isChecked = false;
	     for (var i = 0; i < paymentRadios.length; i++) 
	     {
	         if (paymentRadios[i].checked) 
	         {
	             isChecked = true;
	             payment = document.getElementsByClassName("payment-radio-label")[i].innerHTML;
	             break;
	         }
	     }
	     if (isChecked) 
	     {
	        placeOrderButton.disabled = false;
	     	document.getElementById("payment-select").innerHTML="";
	     } 
	     else 
	     {
	         placeOrderButton.disabled = true;
	     }
 	}
 	
 	function checkout()
 	{
 		window.location = "Order?job=checkout" + "&" + "payment=" + payment;      
 	}
    </script>
<div id="footer"></div>
</body>
</html> 