<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <script src="https://code.jquery.com/jquery-3.3.1.js"
			integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous">
	</script>

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

	
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product List</title>
    <link rel="icon" href="Pics/panda.png">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
    
    <script>
		$(function () {
			$("#header").load("header.html");
			$("#footer").load("footer.html");
		});
	</script>


</head>
<body>



    <div class="font">
    <div class="container-fluid">
        <div id="header"></div>
   	    <div class="header-adjustment"></div>
           <div class="product-category">Products</div>
           <br>
           <br>
        <div class="card-container">
            
        <div class="row justify-content-center">
            
            <!--
            <div class="col list-padding">


            	 to pass to servlet
                <a href="ProductExperiment?title=Name of Product1&desc=Small desc of the product" class="product-link" data-toggle="tooltip" title="Item 1">

                    <div class="product">

                        <img src="Pics/1.webp" class="card-img-top round" alt="...">
                        <div class="card-body" >
                            <h5 class="list-title" style="text-overflow:clip;">Hailey Style Two Piece Hoodie In Brown</h5>
                            <p class="card-text list-price"><span class="rupees">&#8377;</span>200<sup>.00</sup></p>
                        </div>
                    </div>
                </a>
               
            </div>
        -->
            
      

            <%= request.getAttribute("product_cards")%>
    


        </div>
        </div>
    </div>


    </div>
    
<div id="footer"></div>
</body>
</html>
