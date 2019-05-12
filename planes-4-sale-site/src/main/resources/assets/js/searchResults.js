function SearchResultsViewModel() {
    var self = this;

    self.planes = ko.observableArray()

    self.currentSelectedPlane = ko.observable();

    self.contactSellerMessage = ko.observable("Hi, I like the look of your plane.  Can we talk?");
    self.contactSellerEmail = ko.observable("youremail@address.com");
    self.contactSellerNumber = ko.observable("");


    self.contactSeller = function(){

        payload = {
            buyerMessage :  self.contactSellerMessage(),
            buyerEmail :  self.contactSellerEmail(),
            buyerNumber :  self.contactSellerNumber()
        }


        const ajaxRequest = $.ajax({
                                type: 'POST',
                                url:  "/api/public/plane/" + self.currentSelectedPlane().id + "/contactseller",
                                contentType: 'application/json; charset=utf-8',
                                dataType: 'json',
                                data: JSON.stringify(payload)
                            });

        ajaxRequest.fail(function(error){
            console.log(error);
        }
        ).always(function(data){
            console.log("DONE");

            $('#contactSellerModal').modal('hide')
        });

    }


    self.showPopup = function (){
        this.popupDisplayed(true);

        self.currentSelectedPlane(this);

        //js shouldn't reference dom - bit of a hack!
        $('#contactSellerModal').modal('show')

    };



    self.popupShouldBeDisplayed = ko.computed(function(){
        return self.currentSelectedPlane();
    });

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