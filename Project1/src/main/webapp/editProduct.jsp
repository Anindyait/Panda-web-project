
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

  <title>Edit Products</title>

  
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
    <link rel="icon" href="Pics/panda.png">
    
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <div class = "font">
            <div id = "header"></div>
            <div class = "header-adjustment"></div>
            <br>
            <div class="about">
				<h2>Edit Products</h2>
			</div>
			<div class="form-holder">
				<h2>Select the Product you wish to edit:</h2>
	            <br>
		        <div class="col-sm">
		            <label class="form-label" style="opacity:90%;">Product-id</label>
		            <select id ="product" class="form-select" name = "product" required onchange="getDetails()">
		                <option selected value="" disabled>Choose...</option>
		                 <%= request.getAttribute("pid_list")%>
		            </select>
		        </div>

                <div id="product-edit" hidden>
                    <br>
                    <br>                          
                         <div class="col">
                             <div id="prod-details" class="admin-text">

                             </div>
                        </div>
             </div>

		        <br>
		        
		        <div class="col-sm" style="padding: 20px 1px;">
		            <div class="form-floating mb-3" >
		            	<!--a href="AdminProfile?job=delete product&pid=output"-->
                       
		                <a href="AdminProfile">
		                	<button class="btn bamboo-outline form-submit">Back</button>
		                </a>
		            </div>
	            </div>
		    </div>
        </div>
		<script type="text/javascript">
		function getDetails()
		{
			var pid = document.querySelector('#product').value;
			//document.getElementById("pid").innerHTML = pid;
			
			var http = new XMLHttpRequest();
            http.open("POST", "AdminProfile", true);
            http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            var params = "pid=" + document.querySelector('#product').value + "&job=see one product"; // probably use document.getElementById(...).value
            http.send(params);
            
            http.onreadystatechange = function() 
            {
                if (http.readyState === XMLHttpRequest.DONE) 
                {
                	document.getElementById("prod-details").innerHTML = http.response.toString();
        			document.getElementById("product-edit").hidden = false;
                }
			}
		}
		
		 var old_value=[];

	        function product_edit(edit_button, field_name, text_box_name)
			{
	            field = document.getElementById(field_name);
	            text_box = document.getElementById(text_box_name);
	            submit_button = edit_button.nextSibling.nextSibling;

	            switch(field_name)
	            {
	                case "name": old_value[0] = field.innerHTML;
	                    break;
	                case "price": old_value[1] = field.innerHTML;  
	                    break;
	                case "desc": old_value[2] = field.innerHTML;      
	                    break;
	            }
				
				text_box.value = field.innerHTML;
				field.innerHTML = '';
				text_box.style.display = "block";
				field.style.display = "none";
				edit_button.style.display = "none";
	            submit_button.style.display = "block";
			}


	        function change_product_details(submit_button, parameter)
	        {
	            var edit_button = submit_button.previousSibling.previousSibling;
	            submit_button.style.display = "none";
	            edit_button.style.display = "block";
	            var http = new XMLHttpRequest();
	            http.open("POST", "AdminProfile", true);
	            http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	            
	            switch(parameter)
	            {
	                case "name": console.log(old_value[0]); 
	                    var name_textbox = document.getElementById('name-textbox');
	                    var name_div = name_textbox.previousSibling.previousSibling;
	                    if(name_textbox.value == old_value[0] || name_textbox.value =="")
	                    {
	                        name_div.innerHTML = old_value[0];
	                    }    
	                    else
	                    {
	                        var params = "job=edit product" + "&" + "name=" + name_textbox.value + "&" + "pid=" + document.querySelector('#product').value;
	            	        http.send(params);
	                        name_div.innerHTML = name_textbox.value;
	                    }
	                    name_textbox.style.display = "none";
	                    name_div.style.display = "block";
	                    break;


	                case "price": console.log(old_value[1]); 
	                    var price_textbox = document.getElementById('price-textbox');
	                    var price_div = price_textbox.previousSibling.previousSibling;
	                    
	                    const regex = new RegExp(/^(\d+(?:\.\d{1,3})?)$/);
	                    console.log(regex.test(price_textbox.value)); 
	                    
	                    if(price_textbox.value == old_value[1] || price_textbox.value =="" || regex.test(price_textbox.value) == false)
	                    {
	                        price_div.innerHTML = old_value[1];
	                    }    
	                    else
	                    {
	                        var params = "job=edit product" + "&" + "price=" + price_textbox.value + "&" + "pid=" + document.querySelector('#product').value;

	            	        http.send(params);
	                        price_div.innerHTML = price_textbox.value;
	                    }
	                    price_textbox.style.display = "none";
	                    price_div.style.display = "block";
	                    break;


	                case "desc": console.log(old_value[2]); 
	                    var desc_textbox = document.getElementById('desc-textbox');
	                    var desc_div = desc_textbox.previousSibling.previousSibling;
	                    if(desc_textbox.value == old_value[2] || desc_textbox.value =="")
	                    {
	                        desc_div.innerHTML = old_value[2];
	                    }    
	                    else
	                    {
	                        var params = "job=edit product" + "&" + "desc=" + desc_textbox.value + "&" + "pid=" + document.querySelector('#product').value;
	                        http.send(params);
	                        desc_div.innerHTML = desc_textbox.value;

	                    }
	                    desc_textbox.style.display = "none";
	                    desc_div.style.display = "block";
	                    break;
	            }
	        }
		</script>
		<div id="footer"></div>
</body>
</html>  