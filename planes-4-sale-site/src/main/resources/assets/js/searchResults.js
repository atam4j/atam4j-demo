function SearchResultsViewModel() {
    var self = this;

    self.planes = ko.observable()

    $.get( "/api/search?manufacturer=boeing", function( data ) {
      console.log(data)
      _.each(data, function(plane){
        plane.imageURL="/plane-photos/" + plane.imageId + ".jpg";
        console.log("WOOOO")
      } )
      console.log("DONE")

      self.planes(data);
    });

}

ko.applyBindings(new SearchResultsViewModel());