function SearchResultsViewModel() {
    var self = this;

    self.planes = ko.observableArray()

    self.currentSelectedPlane = ko.observable();

    self.contactSellerMessage = ko.observable("Hi, I like the look of your plane.  Can we talk?");
    self.contactSellerEmail = ko.observable("youremail@address.com");
    self.contactSellerNumber = ko.observable("0000");


    self.contactSeller = function(){

        payload = {
            buyerMessage :  self.contactSellerMessage(),
            buyerEmail :  self.contactSellerEmail(),
            buyerNumber :  self.contactSellerNumber()
        }


        const idiot = $.ajax({
                                type: 'POST',
                                url:  "/api/public/plane/" + self.currentSelectedPlane().id + "/contactseller",
                                contentType: 'application/json; charset=utf-8',
                                dataType: 'json',
                                data: JSON.stringify(payload)
                            });

        idiot.fail(function(error){
            console.log(error);
        }
        ).always(function(data){
            console.log("DONE");

            $('#contactSellerModal').modal('hide')
        });





            /*
            $.post('http://example.com/form.php', {category:'client', type:'premium'}, function(response){
                  alert("success");
                  $("#mypar").html(response.amount);
            });
            */

    }


    self.showPopup = function (){
        console.log("THE REG IS " + this.reg);
        this.popupDisplayed(true);

        if (self.currentSelectedPlane()){
            console.log("The current selected plane was: " + self.currentSelectedPlane().reg);

        }
        else{
            console.log("No plane was selected before this")
        }
        self.currentSelectedPlane(this);
        console.log("Th current selected plane NOW is: " + self.currentSelectedPlane().reg);

        //js shouldn't reference dom - bit of a hack!
        $('#contactSellerModal').modal('show')

    };



    self.popupShouldBeDisplayed = ko.computed(function(){
        return self.currentSelectedPlane();
    });

    //TODO make manufacuturer come from url param
    $.get( "/api/public/search" + window.location.search, function( data ) {

      self.planes(_.map(data, function(plane, counter ){
        plane.imageURL="/plane-photos/" + plane.imageId + ".jpg";
        plane.popupDisplayed = ko.observable(false);
        plane.index= "plane-" + counter;


        var thePlane = ko.observable(plane);
        return thePlane;
      }
      ));
    });
}

ko.applyBindings(new SearchResultsViewModel());