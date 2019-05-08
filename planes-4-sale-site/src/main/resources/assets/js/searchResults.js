function SearchResultsViewModel() {
    var self = this;

    self.planes = ko.observableArray()

    self.currentSelectedPlane = ko.observable();



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

        //dirty
        $('#contactSellerModal').modal('show')

    };



    self.popupShouldBeDisplayed = ko.computed(function(){
        return self.currentSelectedPlane();
    });

    $.get( "/api/search?manufacturer=boeing", function( data ) {

      self.planes(_.map(data, function(plane ){
        plane.imageURL="/plane-photos/" + plane.imageId + ".jpg";
        plane.popupDisplayed = ko.observable(false);
        var thePlane = ko.observable(plane);
        return thePlane;
      }
      ));
    });
}

ko.applyBindings(new SearchResultsViewModel());