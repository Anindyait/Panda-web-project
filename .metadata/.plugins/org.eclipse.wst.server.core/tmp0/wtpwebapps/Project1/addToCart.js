
       function Add_to_Cart(work)
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
               if (work === 'cart')
               {
                   var params = "pid=" + pid + "&" + "size=" + selected + "&" + "job=add" + "&" + "work=cart";
                   http.send(params);
                   http.onreadystatechange = function() {
                    cart_items();    
                   }

               }
               else
               {
                   var params = "pid=" + pid + "&" + "size=" + selected + "&" + "job=add" + "&" + "work=buy";
                   http.send(params);

                   http.onreadystatechange = function() {
                    if(http.readyState == XMLHttpRequest.DONE){

                        if(http.status == 200)
                        {
                            window.location = "Cart";
                        }
                   }
               }   
               
               }
               console.log(params);
            
               
             
       }
    }
        
        
        function cart_items()
        {
            var count = document.getElementById("cart-item-numbers");
            console.log("hi from header");
            var cartItems = new XMLHttpRequest();
             cartItems.open("POST", "Cart", true);
             cartItems.setRequestHeader("Content-type","application/x-www-form-urlencoded");
             cartItems.send("job=no_of_items");
             
            
             cartItems.onreadystatechange = function() {
                 if (cartItems.readyState == XMLHttpRequest.DONE) {
                     console.log("no of items in cart = "+cartItems.response);
                     count.innerHTML = cartItems.response;
                 }
             }
        }