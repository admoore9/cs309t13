var TournamentById = function() {
    // Get the current state of this to prevent issues
    var self = this;
    self.inputBox = $('#tournament-by-id-input');
    self.dataDiv = $('#tournament-by-id-data');
    
    // Causes new values to be fetched from the server when the input boxes value is changed.
    self.setInputHandler = function() {
        self.inputBox.on('input', self.getDataUpdateUI);
    };

    self.getDataUpdateUI = function()  {
        var id = self.inputBox.val();
        $.get('/tournament/' + id, "", self.handleData, "json");
    };

    self.handleData = function(response) {
        var html = 
            "<table>" +
                "<tr>" +
                    "<td>" + response.id + "</td>" +
                    "<td>" + response.name + "</td>" +
                "</tr>" +
            "</table>";
        self.dataDiv.children().remove();
        self.dataDiv.append(html);
    };
};

var t = new TournamentById();
t.setInputHandler();
