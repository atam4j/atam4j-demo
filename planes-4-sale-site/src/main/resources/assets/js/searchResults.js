function SearchResultsViewModel() {
    var self = this;

    self.planes = ko.observable()

    $.get( "/api/search?manufacturer=boeing", function( data ) {
      console.log(data)
      self.planes(data);
    });

}

ko.applyBindings(new SearchResultsViewModel());