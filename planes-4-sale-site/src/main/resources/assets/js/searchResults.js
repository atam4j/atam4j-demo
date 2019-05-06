function SearchResultsViewModel() {
    var self = this;

    self.planes = ko.observableArray()

    self.currentSelectedPlane = ko.observable();

    self.showPopup = function (){
        console.log("THE REG IS " + this.reg);
        this.popupDisplayed(true);

        console.log("The current selected plane was: " + self.currentSelectedPlane.reg);
        self.currentSelectedPlane = this;
        console.log("Th current selected plane NOW is: " + self.currentSelectedPlane.reg);

    };

    self.popupShouldBeDisplayed = function(){
        console.log("HELP ME");
        return true;
    }

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