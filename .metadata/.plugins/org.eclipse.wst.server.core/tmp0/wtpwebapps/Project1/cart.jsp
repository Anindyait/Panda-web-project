
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
  <script type='text/javascript' src='addToCart.js'></script>


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
	<title>Cart</title>
    <link rel="icon" href="Pics/panda.png">
    
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <div class = "font">
            <div id = "header"></div>
            <div class = "header-adjustment"></div>
            <br>
			<div class = "container-fluid">
			
                            <div class = "Cart-Container">
                                <div class = "Header-cart">
                                    <h3 class = "Heading-cart">Panda Trolly</h3>
                                    
                                    <h5 class = "Remove-Action" onclick = "RemovefromCart('all')">Remove All</h5>
                                </div>
                    
                                <p id="no-items"></p>
								<%= request.getAttribute("cart_list")%>
								<div class="row total-checkout">
                                    <div class="col">
                                        <div class="prices" id="tot-amount"></div>
                                    </div>
                                    <div class="col">
                                        <a href="Order">
                                        <button type="sub" class="btn form-submit bamboo" id="checkout">
                                            Checkout
                                            <i class="fa-solid fa-angle-right"></i>
                                        </button>
                                        </a>
                                    </div>
                                </div>
                            </div>  
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
	<div id="footer"></div>
    <script>
    function calcTotal()
    {
        var inputs = document.getElementsByClassName("quant");
        var prices = document.getElementsByClassName("price");
        var amt = document.getElementsByClassName("amount");
        var pid = document.getElementsByClassName("pid");
        var size = document.getElementsByClassName("size");

        var amount = [];
		var tot = 0;
		
        for (var i = 0; i < inputs.length; i++)
        {
            var inp = parseInt(inputs[i].value);
            var pri = parseInt(prices[i].innerHTML.slice(2));
            amount[i] = inp * pri;
            console.log(i, inp, pri, amount[i]);
            amt[i].innerHTML = "&#8377;&nbsp" + amount[i];
            tot += amount[i];
             
            // Preferrably shouldn't make this request if previous quantity isn't the same as the new one
		    var http = new XMLHttpRequest();
            http.open("POST", "Cart", true);
            http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            var params = "pid=" + pid[i].innerHTML + "&" + "size=" + size[i].innerHTML +"&" + "qty=" + inputs[i].value + "&" + "job=quantity"; 
            http.send(params);

            http.onreadystatechange = function()
            {
                cart_items();
            }
        } 

    

        if(tot == 0)
        {
            document.getElementById("no-items").innerHTML = "Wooh! &nbspThis looks empty.";
            document.getElementById("checkout").style.display = "none";
        }
        
        
        document.getElementById("tot-amount").innerHTML = "Total:&nbsp&nbsp &#8377; " + tot;
        console.log(tot);
    }  




    function RemovefromCart(y)
    {     
    	
    	var http = new XMLHttpRequest();
        http.open("POST", "Cart", true);
        http.setRequestHeader("Content-type","application/x-www-form-urlencoded");

        if (y === 'all')
        {
        	
            var params = "qty=all" + "&" + "job=remove" ; 
            
        	console.log("All items deleted");
            $(".Cart-Items").remove();
            calcTotal();
        }
        else
        {
        	
        	var cartItem = y.closest(".Cart-Items");
        	var pid = cartItem.querySelector(".pid").textContent;
        	var size = cartItem.querySelector(".size").textContent;
        	
            var params = "pid=" + pid + "&" + "size=" + size + "&" + "qty=one" + "&" + "job=remove" ; 
            
            //.parentElement().parentElement() added.

            y.parentElement.parentElement.parentElement.parentElement.parentElement.remove();
        	console.log("Item successfully deleted");
            calcTotal();
        }

        http.send(params);

        http.onreadystatechange = function()
        {
            cart_items();
        }
    }
    
    window.addEventListener("load", calcTotal);
    </script>
    <div id="footer"></div>
</body>
</html>    