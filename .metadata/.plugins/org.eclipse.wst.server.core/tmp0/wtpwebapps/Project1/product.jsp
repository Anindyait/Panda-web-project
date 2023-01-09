<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="Expires" CONTENT="0">  
    <meta http-equiv="Cache-Control" CONTENT="no-cache">  
    <meta http-equiv="Pragma" CONTENT="no-cache">  
   
    <script src="https://code.jquery.com/jquery-3.3.1.js"
			integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous">
	</script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= request.getAttribute("title") %></title>
    <link rel="icon" href="Pics/panda.png">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <script>
        function Add_to_Cart()
        {
            var selected_size = document.getElementsByClassName("btn-check");
            var selected = false;
            var pid = document.getElementsByClassName("Title")[0].dataset.pid;
            var select_size = document.getElementById("select-size");
            for (var i = 0; i < selected_size.length; i++)
            {
                if (selected_size[i].checked)
                {  
                    selected = selected_size[i].id;
                    break;
                }
            }

            if(!selected)
            {
                console.log(selected);
                select_size.style.color = "red";
                select_size.innerHTML = "Please select a size";
            }
            else
            {
                console.log(pid+" "+selected);
                select_size.style.color = "green";
                select_size.innerHTML = "Added to cart";
                console.log("Add_to_Cart");


                var http = new XMLHttpRequest();
                http.open("POST", "Cart", true);
                http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                var params = "pid=" + pid + "&" + "size=" + selected + "&" + "job=add"; 
                http.send(params);
            }    
            
        }
    </script>

    <script>
		$(function () {
			$("#header").load("header.jsp");
			$("#footer").load("footer.html");
		});
	</script>

    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
    
      <script>
      window.onhashchange = function() {
            window.location.reload();
          };
    </script>
   
</head>
<body>

    <!-- <?php $IPATH = $_SERVER["DOCUMENT_ROOT"]."/php/"; include($IPATH, "headernav.html"); ?> -->


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    
  
    <div class="container-fluid">
    <div id="header"></div>
   	<div class="header-adjustment"></div>
   	
        <div class="cardnew" style="margin: 0 auto;">
            <div class="container px-4">
                <div class="row justify-content-center" >
                    <div class="col-lg mx-auto">
                        <div style="width: 85%; margin: 0px auto;" id="ProductCarousel" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#ProductCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#ProductCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                
                            </div>
                            <div class="carousel-inner round" >
                                <div class="carousel-item active" >
                                    <img src="Pics/<%= request.getAttribute("img1") %>.jpg" class="d-block w-100 img-size" alt="...">
                                </div>
                                <div class="carousel-item">
                                    <img src="Pics/<%= request.getAttribute("img2") %>.jpg" class="d-block w-100 img-size" alt="...">
                                </div>
                              
                            </div>
                            
                            <button class="carousel-control-prev" type="button" data-bs-target="#ProductCarousel" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#ProductCarousel" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        
                    </div>
                    <div class="col-lg">


                        <div class="text">
                            
                            <!--Title-->
                                                                         <!-- to be replaced by java -->
                            <div class="Title" style="margin: 0px auto" data-pid="<%= request.getAttribute("pid") %>" ><%= request.getAttribute("title") %></div>
                            
                            <!--Price-->
                            <div class="Price">&#8377;<%= request.getAttribute("price") %><sup>.00</sup></div>

                            <!--size selectors-->
                            <div class="size-selector-container">
                            		

                                <%= request.getAttribute("size_selectors") %>
                            </div>

                            <p id="select-size"></p>


                            <!--Description-->
                            <div class="desc overflow-auto">
                                <!-- to be replaced by java -->
                                <div class="Description"><%= request.getAttribute("desc") %></div>
                            </div>
                            <div class="row justify-content-center button-grid ">
                                <div class="col-4">
                                    <input type="button" value="Buy" class="btn btn-warning Buy" />
                                </div>
                                <div class="col-4">
                                    <input type="button" value="Add to Cart" class="btn btn-outline-primary AddtoCart" onclick="Add_to_Cart()"/>
                                </div>
                            </div>
                        </div>

                     </div>
                </div>
            </div>
        </div>
        <br>
        <div class="cardnew" style="margin: 0 auto;">
            <div class="text">
                <h1>More made-up stuff about this non-existent product!</h1>
                <br>
                <div class="Description opacity-75">*69mg of cocaine.**</div>
                <div class="Description opacity-75">**even more fucking cocaine.</div>
            </div>
        </div>
    </div>
<div id="footer"></div>
</body>
</html>