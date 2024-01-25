
     /*Заполнение полей формы Input в ордере */
 $("#deliveryName").attr("value", "Vova");
 $("#deliveryStreet").attr("value", "Простая");
 $("#deliveryCity").attr("value", "Ачинск");
 $("#deliveryState").attr("value", "МО");
 $("#deliveryZip").attr("value", "400080");
 $("#ccNumber").attr("value", "378282265245452");
 $("#ccExpiration").attr("value", "11/23");
 $("#ccCVV").attr("value", "123");

     $("#save").click(function () {
      let ingredients = getIngredients();
      let name = $("#name").val();
      let taco = createTacoObject(name, ingredients);
      save(taco);
     })

     function getIngredients() {
      let listOfIngredients = [];
      listOfIngredients.push({
       id: $("#WRAP:checked").val(),
       type: 'WRAP'
      });
      listOfIngredients.push({
       id: $("#PROTEIN:checked").val(),
       type: 'PROTEIN'
      });
      listOfIngredients.push({
       id: $("#VEGGIES:checked").val(),
       type: 'VEGGIES'
      });
      listOfIngredients.push({
       id: $("#CHEESE:checked").val(),
       type: 'CHEESE'
      });
      listOfIngredients.push({
       id: $("#SAUCE:checked").val(),
       type: 'SAUCE'
      });
      console.log(listOfIngredients)
      return listOfIngredients;
     }

     function createTacoObject(name, ingredients) {
      return {
       name: name,
       ingredients: ingredients
      }
     }

     function save(taco) {
      console.log("save")
      $.ajax({
       url: '/orderadd',
       type: 'post',
       data: JSON.stringify(taco),
       headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
       },
       complete: function(data){
        console.log(data.status);
        if (data.status == 200) {
         $("#name").val('');
         $('input[type=checkbox]').prop('checked',false);
         $(".new-taco").removeAttr("hidden");
         $(".item").text(taco.name);
         $(".new-order").removeAttr("hidden");
        }
        else {
         console.log("error");
         alert("Name must be at least 5 characters long. \n You must choose at least 3 ingredient");
        }
       }
      });
     }