
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
	<title>Order</title>
    <link rel="icon" href="Pics/panda.png">
    
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <div class = "font">
            <div id = "header"></div>
            <div class = "header-adjustment"></div>
            <br>
            <div class = "container-fluid">
                <div class = "Order-Container">
                    <div class = "Header-cart">
                        <h3 class = "Heading-cart">All Orders</h3>      
                    </div>
                    <p id="no-items"></p>
                    <%= request.getAttribute("previous_order_list")%>
                </div>  
                <br>
                <br>
            </div>            
        </div>
	<div id="footer"></div>
</body>
</html> 