
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
			$("#header").load("header.html");
			$("#footer").load("footer.html");
		});
	</script>

	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="Pics/panda.png">
    
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <div class = "font">
            <div id = "header"></div>
            <div class = "header-adjustment"></div>
            <br>
    		<div class="about">
				<h2>Delete Products</h2>
			</div>
			<div class="form-holder">
				<h2>Select the Product-id you wish to delete:</h2>
	            <br>
		        <div class="col-sm">
		            <label class="form-label" style="opacity:90%;">Product-id</label>
		            <select id ="product" class="form-select" name = "product" required>
		                <option selected value="" disabled>Choose...</option>
                        <%= request.getAttribute("pid_list")%>
		            </select>
		        </div>
		        <br>
		        <p id="deleted" ></p>
		        <div class="col-sm" style="padding: 20px 1px;">
		            <div class="form-floating mb-3" >
		            	<!--a href="AdminProfile?job=delete product&pid=output"-->
		                <button onclick="sendPID()" class="btn btn-danger form-submit">Delete</button>
		                <a href="AdminProfile">
		                	<button class="btn bamboo form-submit">Back</button>
		                </a>
		            </div>
	            </div>
		    </div>
        </div>
		<script type="text/javascript">
		function sendPID() {
			
		    var http = new XMLHttpRequest();
            http.open("POST", "AdminProfile", true);
            http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            var params = "pid=" + document.querySelector('#product').value + "&job=delete product"; // probably use document.getElementById(...).value
            http.send(params);
            
            http.onreadystatechange = function() {
                if (http.readyState === XMLHttpRequest.DONE) 
                {
                    var added = document.getElementById("product");
                    added.innerHTML = http.response;
                    
                    var message = document.getElementById("deleted");
                    message.hidden = false;
                   	res = Number(http.status);

					if (res == 200) 
					{
						message.style.color = "green";
						message.innerHTML = "Successfully deleted !";
						console.log(res);

					}
					else
					{
						message.style.color = "red";
						message.innerHTML = "Sorry, product cannot be deleted";
						console.log(res);
					}
					
					setTimeout(function() {
                        message.hidden = true;
                    }, 2000);
                    
                }
            }
		}
		</script>
		<div id="footer"></div>
</body>
</html>  