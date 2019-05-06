function SearchResultsViewModel() {
    var self = this;

    self.planes = ko.observableArray()

    self.showPopup = function (){
        console.log("THE REG IS " + this.reg);
        console.log("THE ID IS " + this.id);
        this.popupDisplayed(true);
    };

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