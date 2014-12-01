$(document).ready(function() {
    $('#update-team-form').on('submit', function(event) {
        event.preventDefault();
        $.post('/team/' + teamId + '/update', {
            teamName: $('#update-team-name-input').val(),
            addPlayer: $('#update-player-add-input').val(),
            removePlayer: $('#update-player-remove-input').val(),
        });
    });

//    var GameById = function() {
//        // Get the current state of this to prevent issues
//        var self = this;
//        self.updateForm = $('#update-game-form');
//        self.tournamentName = $('#update-game-location-input');
//        
//
//        // Causes new values to be fetched from the server when the input boxes value is changed.
//        self.setInputHandler = function() {
//            self.updateForm.on('submit', self.updateGame);
//        };
//
////        self.getDataUpdateUI = function()  {
////            var id = self.inputBox.val();
////            $.get('/tournament/' + id, "", self.handleData, "json");
////        };
//
////        self.handleData = function(response) {
////            self.tournamentName.val(response.name);
////            self.minPlayers.val(response.minPlayers);
////            self.maxPlayers.val(response.maxPlayers);
////            self.teamsPerGame.val(response.teamsPerGame);
////            self.officialsPerGame.val(response.officialsPerGame);
////        };
//
//        self.updateGame = function(event) {
//            event.preventDefault();
//            $.post('/game/1/update', {
//                location: self.location.val(),
//            });
//        };
//
////        self.handleGoToTournament = function(event) {
////            event.preventDefault();
////            alert(window.location.href);
////            window.location.href = '/tournament/' + self.inputBox.val() + '/view';
////        };
//    };
//
//    var gameById = new GameById();
//    gameById.setInputHandler();

});
